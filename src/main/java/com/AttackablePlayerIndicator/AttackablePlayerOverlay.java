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
    private final com.AttackablePlayerIndicator.AttackablePlayerConfig config;

    @Inject
    public AttackablePlayerOverlay(Client client, com.AttackablePlayerIndicator.AttackablePlayerConfig config) {
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
                //OverlayUtil.renderActorOverlay(graphics, player, "", Color.RED);
                //renderPlayerOutline(graphics, player, Color.RED);
                renderTileOutline(graphics, player);
            }
        }

        return null;
    }
    private void renderTileOutline(Graphics2D graphics, Player player)
    {
        Polygon tilePoly = player.getCanvasTilePoly();
        if (tilePoly != null)
        {
            // Muted red color (darker, less saturated)
            Color mutedRed = new Color(180, 50, 50); // Or try: new Color(150, 70, 70)

            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setColor(mutedRed);
            graphics.setStroke(new BasicStroke(1.0f)); // Thin 1-pixel line
            graphics.draw(tilePoly);
        }
    }
    private void renderPlayerOutline(Graphics2D graphics, Player player, Color color)
    {
        Shape hull = player.getConvexHull();
        if (hull != null)
        {

            // Enable anti-aliasing for smooth, crisp edges
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            graphics.setColor(color);
            graphics.setStroke(new BasicStroke(1.0f)); // Thinnest stroke
            graphics.draw(hull);
        }
    }
}
