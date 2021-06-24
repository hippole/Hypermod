package com.hypermnesia.hypermod;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class randomWord extends CommandBase {

    int randomWithRange(int min, int max){

        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

        @Override
        public String getCommandName() {
            return "randomword";
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
            try {
                RandomAccessFile file = new RandomAccessFile("/Users/capslock/Downloads/words.txt", "r");
                randomWord num = new randomWord();
                long length = file.length();
                int lines = num.randomWithRange(0, (int) length);
                file.seek(lines);
                file.readLine();
                file.readLine();
                String word = file.readLine();
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Your random word is " + EnumChatFormatting.YELLOW + word));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


}
