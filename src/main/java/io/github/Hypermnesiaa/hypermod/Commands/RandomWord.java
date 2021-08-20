package io.github.Hypermnesiaa.hypermod.Commands;

import io.github.Hypermnesiaa.hypermod.Utils.ConfigHandler;
import io.github.Hypermnesiaa.hypermod.Utils.Define;
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

    Define define = new Define();

        @Override
        public String getCommandName() {
            return "randomword";
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
            if (ConfigHandler.randomWordFilePath.equalsIgnoreCase("")) {
                define.simpleSendMessage("red", "[ERROR] There is no file path specified in \"Hypermod.cfg\". Add the file path to your list of words!");
                return;
            }
            try {
                RandomAccessFile file = new RandomAccessFile(ConfigHandler.randomWordFilePath, "r");
                RandomWord num = new RandomWord();
                long length = file.length();
                int lines = num.define.randomWithRange(0, (int) length);
                file.seek(lines);
                file.readLine();
                file.readLine();
                String word = file.readLine();
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Your random word is " + EnumChatFormatting.YELLOW + word));
            } catch (FileNotFoundException e) {
                define.simpleSendMessage("red","[ERROR] That file does not exist! Check \"Hypermod.cfg\" and edit your file path!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


}
