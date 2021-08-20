package io.github.Hypermnesiaa.hypermod.Commands;

import io.github.Hypermnesiaa.hypermod.Hypermod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class TheMoon extends CommandBase {

    @Override
    public String getCommandName() {
        return "themoon";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/hypermod " + getCommandName();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation(Hypermod.MODID, "themoon.ogg"), 1.0F));
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "[ERROR] bruh idfk how to make sounds play"));
        //SoundEvent soundEvent = new SoundEvent(new ResourceLocation("hypermod","theMoon"));
        //i dont know right now im too tired to get big brain ideas cya later idiots
        // https://forums.minecraftforge.net/topic/54260-189-custom-sounds/ claims i dont need to register, ill keep that in mind.
    }
}