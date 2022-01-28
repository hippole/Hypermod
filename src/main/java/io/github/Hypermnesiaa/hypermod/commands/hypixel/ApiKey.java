package io.github.Hypermnesiaa.hypermod.commands.hypixel;

import io.github.Hypermnesiaa.hypermod.utils.ConfigHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class ApiKey extends CommandBase {

    public String getCommandDescription() {
        return "Returns your current apikey";
    }

    @Override
    public String getCommandName() {
        return "apikey";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (ConfigHandler.hypixelApiKey.equalsIgnoreCase("")) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "[ERROR] There is no Api-Key Provided in \"Hypermod.cfg\", Add it before executing this command again."));
            return;
        }
        StringSelection selection = new StringSelection(ConfigHandler.hypixelApiKey);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE + ConfigHandler.hypixelApiKey + " " +
                EnumChatFormatting.GRAY + "Api-Key set to clipboard."));
    }


}