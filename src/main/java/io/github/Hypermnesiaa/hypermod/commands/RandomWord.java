package io.github.Hypermnesiaa.hypermod.commands;

import io.github.Hypermnesiaa.hypermod.misc.Misc;
import io.github.Hypermnesiaa.hypermod.utils.ConfigHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class RandomWord extends CommandBase {

    Misc rt = new Misc();

    public String getCommandDescription() {
        return "Returns a random word from a file.";
    }

    public String getCommandAlias() {
        return "";
    }

    @Override
    public String getCommandName() {
        return "randomword";
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
        if (ConfigHandler.randomWordFilePath.equalsIgnoreCase("")) {
            Misc.raiseError("There is no file path specified in \"Hypermod.cfg\". Add the file path the config file then try again.");
            return;
        }
        try {
            RandomAccessFile file = new RandomAccessFile(ConfigHandler.randomWordFilePath, "r");
            RandomWord num = new RandomWord();
            long length = file.length();
            int lines = num.rt.randomWithRange(0, (int) length);
            file.seek(lines);
            file.readLine();
            file.readLine();
            String word = file.readLine();
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Your random word is " + EnumChatFormatting.YELLOW + word));
        } catch (FileNotFoundException e) {
            Misc.raiseError("The specified file path does not exist! Specify a valid filepath in \"Hypermod.cfg\".");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
