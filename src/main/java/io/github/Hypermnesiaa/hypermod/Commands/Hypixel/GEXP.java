package io.github.Hypermnesiaa.hypermod.Commands.Hypixel;

import io.github.Hypermnesiaa.hypermod.Hypermod;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GEXP extends CommandBase {

    int GEXP;
    Hypermod hypermod;

    public GEXP (Hypermod mod) {
        hypermod = mod;
        GEXP = mod.GEXP;
    }

    @SubscribeEvent
    public void ClientChatReceivedEvent(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();
        try {
            if (Minecraft.getMinecraft().getCurrentServerData().serverIP.contains("hypixel.net")) {
                if (message.contains("You earned ") && message.contains(" GEXP from playing")) {
                    try {
                        int GEXPEarned = Integer.parseInt(message.split(" ")[2]);
                        int XP = hypermod.GEXP + GEXPEarned;
                        hypermod.GEXP = XP;
                    } catch (NumberFormatException e) {
                        return;
                    }
                }
                if (message.contains("Reward Summary")) {
                    String msg = message.split(" ")[56];
                    String str = msg.substring(2);
                    try {
                        int GEXPEarned = Integer.parseInt(str);
                        int XP = hypermod.GEXP + GEXPEarned;
                        hypermod.GEXP = XP;
                    } catch (NumberFormatException e) {
                        return;
                    }
                }
            } else {
                System.err.println("Server is not Hypixel! Canceling Action.");
            }
        } catch (NullPointerException e) {
            System.err.println("Player is in a singleplayer world! Canceling action.");
        }
    }

    @Override
    public String getCommandName() {
        return "gexp";
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
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "[WARNING] Command may not function as intended.\n" +
                EnumChatFormatting.GREEN + "GEXP earned in current session: " + EnumChatFormatting.DARK_GREEN + hypermod.GEXP));
    }
}