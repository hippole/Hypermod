package io.github.Hypermnesiaa.hypermod.GUI;

import io.github.Hypermnesiaa.hypermod.Utils.ConfigHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class RandomHippo extends Gui {

    String text = "hippo";

    public RandomHippo (Minecraft mc) {
        if (ConfigHandler.randomHippoInBottomLeftCornerEnabled) {
            ScaledResolution scaled = new ScaledResolution(mc);
            drawCenteredString(mc.fontRendererObj, text, 14, 374, Integer.parseInt("AAAAAA", 16));
            //Note: gotta make this work on all resolutions
        }
    }
}
