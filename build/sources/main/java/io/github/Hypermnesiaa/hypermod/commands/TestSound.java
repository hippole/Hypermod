package io.github.Hypermnesiaa.hypermod.commands;

import io.github.Hypermnesiaa.hypermod.Hypermod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

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
        if (args[0].isEmpty()) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "No sound was provided."));
            return;
        }
        try {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Played " + args[0].toLowerCase()));
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation(Hypermod.MODID, args[0].toLowerCase()), 1.0F));
        } catch (Exception e) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Not a valid sound! (" + args[0].toLowerCase() + ")"));
        }
    }

}