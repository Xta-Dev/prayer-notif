package com.prayer;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Skill;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Prayer Overhead Text"
)
public class LowPrayerNotifPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private LowPrayerNotifConfig config;

	@Inject
	private Overhead overhead;

	@Override
	protected void startUp() throws Exception
	{
		log.debug("Plugin started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.debug("Plugin stopped!");
	}

	@Subscribe
	public void onGameTick(GameTick event){
		if (!isClientReady()) {
			return;
		}

		if (prayerTotalBelowThreshold()) {
			overhead.show(
					config.getPrayerWarningText(),
					new String[0],
					config.getPrayerOverlayColour(),
					1
			);
		}
	}

	@Provides
	LowPrayerNotifConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(LowPrayerNotifConfig.class);
	}

	public boolean prayerTotalBelowThreshold()  {
		return isClientReady() && client.getBoostedSkillLevel(Skill.PRAYER) < config.getPrayerThreshold();
	}

	public boolean isClientReady() {
		return client.getGameState() == GameState.LOGGED_IN && client.getLocalPlayer() != null;
	}
}
