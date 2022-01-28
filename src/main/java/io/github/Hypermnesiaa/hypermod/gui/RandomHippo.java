package io.github.Hypermnesiaa.hypermod.gui;

import io.github.Hypermnesiaa.hypermod.utils.ConfigHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class RandomHippo extends Gui {

    public RandomHippo (Minecraft mc) {
        if (ConfigHandler.randomHippoInBottomLeftCornerEnabled) {
            drawCenteredString(mc.fontRendererObj, "hippo", 14, 516, Integer.parseInt("AAAAAA", 16));
            //Note: gotta make this work on all resolutions
            //Note2: idfk how to do that someone please help me
        }
    }
}
