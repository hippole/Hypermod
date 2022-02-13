package io.github.Hypermnesiaa.hypermod.commands.hypixel;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import java.util.ArrayList;
import java.util.List;

public class Coordinates extends CommandBase {

    public String getCommandDescription() {
        return "Sends your current coordinates in chat";
    }

    public String getCommandAlias() {
        return "/co";
    }

    @Override
    public String getCommandName() {
        return "coordinates";
    }

    @Override
    public List getCommandAliases() {
	    List list = new ArrayList<String>();
	    list.add("co");
	    return list;
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
        int coordinatesX = Minecraft.getMinecraft().thePlayer.getPosition().getX();
        int coordinatesY = Minecraft.getMinecraft().thePlayer.getPosition().getY();
        int coordinatesZ = Minecraft.getMinecraft().thePlayer.getPosition().getZ();
        Minecraft.getMinecraft().thePlayer.sendChatMessage("Coordinates!: X:" + coordinatesX + " Y:" + coordinatesY + " Z:" + coordinatesZ);
    }


}