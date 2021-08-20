package io.github.Hypermnesiaa.hypermod.GUI;

import io.github.Hypermnesiaa.hypermod.Utils.ConfigHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class RandomHippo extends Gui {

    public RandomHippo (Minecraft mc) {
        if (ConfigHandler.randomHippoInBottomLeftCornerEnabled) {
            drawCenteredString(mc.fontRendererObj, "hippo", 14, 516, Integer.parseInt("AAAAAA", 16));
            //Note: gotta make this work on all resolutions
            //Note2: idfk how to do that someone please help me
        }
    }
}
