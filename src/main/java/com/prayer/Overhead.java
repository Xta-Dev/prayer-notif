/*
 * Copyright (c) 2024, Adam Keenan <http://github.com/adamk33n3r>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.prayer;

import net.runelite.api.Client;
import net.runelite.api.Constants;
import net.runelite.api.Player;
import net.runelite.client.util.ColorUtil;

import javax.inject.Inject;
import java.awt.Color;

public class Overhead {
    @Inject
    private transient Client client;

    public void show(String template, String[] triggerValues, Color textColor, int displayTimeSeconds) {
        String message = processTriggerValues(template, triggerValues);

        Player localPlayer = client.getLocalPlayer();
        if (localPlayer == null) {
            return;
        }

        if (message == null || message.trim().isEmpty()) {
            localPlayer.setOverheadText(null);
            localPlayer.setOverheadCycle(0);
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (textColor != null) {
            sb.append("<col=")
                    .append(ColorUtil.colorToHexCode(textColor))
                    .append(">")
                    .append(message)
                    .append("</col>");
        } else {
            sb.append(message);
        }

        localPlayer.setOverheadText(sb.toString());
        localPlayer.setOverheadCycle(displayTimeSeconds * 1000 / Constants.CLIENT_TICK_LENGTH);
    }

    private static String processTriggerValues(String message, String[] triggerValues) {
        if (triggerValues == null) {
            return message;
        }

        for (int i = 0; i < triggerValues.length; i++) {
            message = message.replace("{" + i + "}", triggerValues[i]);
        }

        return message;
    }
}