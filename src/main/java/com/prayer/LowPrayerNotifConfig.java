package com.prayer;

import net.runelite.client.config.*;

import java.awt.*;

@ConfigGroup("health-notifications")
public interface LowPrayerNotifConfig extends Config
{
	/* Prayer Settings */
	@ConfigSection(
			name = "Prayer Settings",
			description = "Prayer settings",
			position = 1,
			closedByDefault = false
	)
	String prayerSettings = "prayerSettings";

	@ConfigItem(
			keyName = "getPrayerWarningText",
			name = "Low Prayer Warning Text",
			description = "Set the text that is displayed when low prayer",
			position = 2,
			section = prayerSettings
	)
	default String getPrayerWarningText() {
		return "Low Prayer";
	}

	@ConfigItem(
			keyName = "getPrayerThreshold",
			name = "Prayer Threshold",
			description = "Set prayer threshold",
			position = 3,
			section = prayerSettings
	)
	default int getPrayerThreshold() {
		return 1;
	}

	@Alpha
	@ConfigItem(
			keyName = "prayerTextColour",
			name = "Text Colour",
			description = "Set the text colour",
			position = 4,
			section = prayerSettings
	)
	default Color getPrayerOverlayColour() {
		return new Color(0.0f, 0.0f, 1.0f, 0.25f);
	}
}
