package com.AttackablePlayerIndicator;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
        name = "Attackable Player Indicator"
)
public class AttackablePlayer extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private AttackablePlayerConfig config;

    @Inject
    private AttackablePlayerOverlay overlay;

    @Inject
    private OverlayManager overlayManager;

    @Override
    protected void startUp() throws Exception
    {
        overlayManager.add(overlay);
        log.debug("Attackable Player Indicator started!");
    }

    @Override
    protected void shutDown() throws Exception
    {
        overlayManager.remove(overlay);
        log.debug("Attackable Player Indicator stopped!");
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged gameStateChanged)
    {

    }

    @Provides
    AttackablePlayerConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(AttackablePlayerConfig.class);
    }
}
