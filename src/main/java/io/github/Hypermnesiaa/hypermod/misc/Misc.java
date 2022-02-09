package io.github.Hypermnesiaa.hypermod.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Misc {

    public static int eatMinecraftTicks;
    public static boolean timeWindow;

    public static int randomWithRange (int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    public static void raiseError(String error) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "[ERROR] " + error));
    }

}