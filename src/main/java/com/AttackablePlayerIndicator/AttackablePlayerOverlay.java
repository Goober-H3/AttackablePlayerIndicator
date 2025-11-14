package com.AttackablePlayerIndicator;

import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;import net.runelite.api.Player;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;

import java.awt.*;
import javax.inject.Inject;

public class AttackablePlayerOverlay extends Overlay
{
    private final Client client;
    private final AttackablePlayerConfig config;

    @Inject
    public AttackablePlayerOverlay(Client client, AttackablePlayerConfig config) {
        this.client = client;
        this.config = config;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        Player localPlayer = client.getLocalPlayer();
        if (localPlayer == null)
        {
            return null;
        }

        int myLevel = localPlayer.getCombatLevel();
        int range = config.combatRange().getRange();

        int minAttackable = myLevel - range;
        int maxAttackable = myLevel + range;

        for (Player player : client.getPlayers())
        {
            if (player == null || player == localPlayer)
            {
                continue;
            }

            int theirLevel = player.getCombatLevel();
            if (theirLevel >= minAttackable && theirLevel <= maxAttackable) {
                OverlayUtil.renderActorOverlay(graphics, player, "", Color.RED);
            }
        }

        return null;
    }
}
