package io.github.Hypermnesiaa.hypermod;

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
            return "how did you get this?";
        }

        @Override
        public int getRequiredPermissionLevel() {
            return -1;
        }

        @Override
        public void processCommand(ICommandSender sender, String[] args) throws CommandException {
            try {
                RandomAccessFile file = new RandomAccessFile("C:\\Users\\Kyle\\Downloads\\Hypermod\\src\\main\\resources\\words.txt", "r");
                RandomWord num = new RandomWord();
                long length = file.length();
                int lines = num.define.randomWithRange(0, (int) length);
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
