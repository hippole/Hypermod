package io.github.Hypermnesiaa.hypermod.commands;

import io.github.Hypermnesiaa.hypermod.misc.Misc;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class TestSound extends CommandBase {

    public String getCommandDescription() {
        return "[DEBUG] Plays a sound of choice";
    }

    @Override
    public String getCommandName() {
        return "testsound";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName() + " sound";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            Misc.raiseError("No sound was provided");
            return;
        }
        try {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Played " + args[0].toLowerCase()));
            Misc.playCustomSound(args[0]);
        } catch (Exception e) {
            Misc.raiseError("Not a valid sound! (" + args[0].toLowerCase() + ")");
        }
    }

}