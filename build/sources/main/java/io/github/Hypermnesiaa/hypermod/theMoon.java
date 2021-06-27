package io.github.Hypermnesiaa.hypermod;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.sound.SoundEvent;

public class theMoon extends CommandBase {

    @Override
    public String getCommandName() {
        return "themoon";
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
        Minecraft.getMinecraft().thePlayer.playSound("theMoon.ogg", 50, 0);
        ResourceLocation location = new ResourceLocation("hypermod", "theMoon");
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Unfortunately this command doesn't work right now."));
        //SoundEvent soundEvent = new SoundEvent(new ResourceLocation("hypermod","theMoon"));
        //i dont know right now im too tired to get big brain ideas cya later idiots
    }
}