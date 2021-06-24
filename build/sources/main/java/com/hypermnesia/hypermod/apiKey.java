package com.hypermnesia.hypermod;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class apiKey extends CommandBase {

    @Override
    public String getCommandName() {
        return "apikey";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "how did you get this?";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        StringSelection selection = new StringSelection("dd949837-60e1-4d30-bbe0-4d2f073588fe");
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE + "dd949837-60e1-4d30-bbe0-4d2f073588fe " +
                EnumChatFormatting.GRAY + "Api-Key set to clipboard."));
    }


}