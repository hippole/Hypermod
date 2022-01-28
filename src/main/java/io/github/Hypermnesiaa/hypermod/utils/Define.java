package io.github.Hypermnesiaa.hypermod.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Define {

    public int randomWithRange (int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    /*public void simpleSendMessage(String color, String text) {
    trash
        if (color.equalsIgnoreCase("aqua")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + text));
        }
        if (color.equalsIgnoreCase("red")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + text));
        }
        if (color.equalsIgnoreCase("gold")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + text));
        }
        if (color.equalsIgnoreCase("dark_aqua")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + text));
        }
        if (color.equalsIgnoreCase("black")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.BLACK + text));
        }
        if (color.equalsIgnoreCase("blue")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + text));
        }
        if (color.equalsIgnoreCase("bold")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD + text));
        }
        if (color.equalsIgnoreCase("dark_blue")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_BLUE + text));
        }
        if (color.equalsIgnoreCase("dark_gray")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_GRAY + text));
        }
        if (color.equalsIgnoreCase("dark_green")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_GREEN + text));
        }
        if (color.equalsIgnoreCase("dark_purple")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + text));
        }
        if (color.equalsIgnoreCase("dark_red")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + text));
        }
        if (color.equalsIgnoreCase("gray")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + text));
        }
        if (color.equalsIgnoreCase("green")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + text));
        }
        if (color.equalsIgnoreCase("italic")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + text));
        }
        if (color.equalsIgnoreCase("light_purple")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE + text));
        }
        if (color.equalsIgnoreCase("obfuscated")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.OBFUSCATED + text));
        }
        if (color.equalsIgnoreCase("reset")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RESET + text));
        }
        if (color.equalsIgnoreCase("strikethrough")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.STRIKETHROUGH + text));
        }
        if (color.equalsIgnoreCase("underline")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.UNDERLINE + text));
        }
        if (color.equalsIgnoreCase("white")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + text));
        }
        if (color.equalsIgnoreCase("yellow")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + text));
        }
    }*/
}