package io.github.Hypermnesiaa.hypermod.commands;

import io.github.Hypermnesiaa.hypermod.misc.Misc;
import io.github.Hypermnesiaa.hypermod.misc.exceptions.HippoAteMinecraftException;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.crash.CrashReport;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class EatMinecraft extends CommandBase {

    int confirm;

    public String getCommandDescription() {
        return "feed hippo";
    }

    public String getCommandAlias() {
        return "";
    }

    @Override
    public String getCommandName() {
        return "eatminecraft";
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
        try {
            if (args.length == 0) {
                Misc.playCustomSound("car");
                Misc.eatMinecraftTicks = 0;
                Misc.timeWindow = true;
                confirm = Misc.randomWithRange(1000, 9999);
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Are you sure? Type the command in again with the first argument being " +
                        EnumChatFormatting.LIGHT_PURPLE + confirm + EnumChatFormatting.RED + " within the next 10 seconds to eat minecraft."));
            }
            if (args[0].equals(String.valueOf(confirm))) {
                if (Misc.timeWindow) {
                    Misc.playCustomSound("boom");
                    for (int i = 0; i < 21; i++) {
                        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "nom nom nom"));
                    }
                    Minecraft.getMinecraft().crashed(CrashReport.makeCrashReport(new HippoAteMinecraftException(), "being digested in hippo's stomach"));
                } else {
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "The code has expired! Run the command again to get a new code"));
                }
            } else {
                Misc.raiseError("Wrong Code");
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.ClientTickEvent event) {
        if (Misc.timeWindow) {
            Misc.eatMinecraftTicks++;
            if (Misc.eatMinecraftTicks % 400 == 0) {
                Misc.playCustomSound("car");
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Code expired, run the command again to get a new code."));
                Misc.timeWindow = false;
                Misc.eatMinecraftTicks = 0;
            }
        }
    }
}