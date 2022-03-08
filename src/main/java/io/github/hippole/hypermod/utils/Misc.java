package io.github.hippole.hypermod.utils;

import io.github.hippole.hypermod.Hypermod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

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

    public static void playCustomSound(String sound) {
        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation(Hypermod.MODID, sound), 1.0F));
    }

}