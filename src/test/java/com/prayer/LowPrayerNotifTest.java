package com.prayer;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class LowPrayerNotifTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(LowPrayerNotifPlugin.class);
		RuneLite.main(args);
	}
}