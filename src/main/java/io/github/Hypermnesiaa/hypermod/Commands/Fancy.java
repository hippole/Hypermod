package io.github.Hypermnesiaa.hypermod.Commands;

import io.github.Hypermnesiaa.hypermod.GUI.FancyGui;
import io.github.Hypermnesiaa.hypermod.Hypermod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class Fancy extends CommandBase {

    @Override
    public String getCommandName() {
        return "fancy";
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
        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation(Hypermod.MODID, "fancy"), 1.0F));
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "F" + EnumChatFormatting.GOLD + "a" +
                EnumChatFormatting.GREEN + "n" + EnumChatFormatting.BLUE + "c" + EnumChatFormatting.DARK_BLUE + "Y" + EnumChatFormatting.DARK_PURPLE + "!"));
        //maybe ill revisit this later and finish the gui
    }

}