package io.github.Hypermnesiaa.hypermod.commands.hypixel;

import io.github.Hypermnesiaa.hypermod.utils.ConfigHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.List;

public class Reque extends CommandBase {

    public String getCommandDescription() {
        return "Sends you into another Hypixel mini game";
    }

    @Override
    public String getCommandName() {
        return "reque";
    }

    @Override
    public List getCommandAliases() {
	    List list = new ArrayList<String>();
	    list.add("rq");
	    return list;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName() + " [OPTIONAL value]";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        try {
            if (args.length != 0) {
                ConfigHandler.requeValue = args[0];
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Reque Value Sucessfully set to " + ConfigHandler.requeValue + " for current session.\n" +
                        EnumChatFormatting.GRAY + "Edit Hypermod.cfg to change this value permanently."));
                return;

            }
            else if (Minecraft.getMinecraft().getCurrentServerData().serverIP.contains("hypixel.net")) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Sending to " + ConfigHandler.requeValue + "..."));
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/play " + ConfigHandler.requeValue);
            } else {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Server is not Hypixel! Canceling action."));
                System.err.println("Server is not Hypixel! Canceling action.");
                return;
           }
        } catch (NullPointerException e) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED  + "You are in a singleplayer world! Canceling action."));
            System.err.println("Player is in a singleplayer world! Canceling action.");
        }
    }


}