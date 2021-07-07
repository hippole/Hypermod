package io.github.Hypermnesiaa.hypermod;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class HypixelStatus extends CommandBase {


    @Override
    public String getCommandName() {
        return "hypixelstatus";
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
            try {
                URLConnection username = new URL("https://api.mojang.com/users/profiles/minecraft/" + args[0]).openConnection();
                BufferedReader inu = new BufferedReader(new InputStreamReader(username.getInputStream()));
                String inputLineusername;
                StringBuilder stringbuilderusername = new StringBuilder();
                while ((inputLineusername = inu.readLine()) != null) {
                    stringbuilderusername.append(inputLineusername);
                }
                JSONObject jsonusername = new JSONObject(stringbuilderusername.toString());
                String nameToUUID = jsonusername.getString("id");
                String name = jsonusername.getString("name");
                URLConnection connection = new URL("https://api.hypixel.net/status?uuid=" + nameToUUID).openConnection();
                connection.setRequestProperty("Api-Key", "dd949837-60e1-4d30-bbe0-4d2f073588fe");
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder stringbuilder = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    stringbuilder.append(inputLine);

                }
                JSONObject json = new JSONObject(stringbuilder.toString());
                String UUID = json.getString("uuid");
                JSONObject session = json.getJSONObject("session");
                if (session.getBoolean("online") == true) {
                    String gameType = session.getString("gameType");
                    String mode = session.getString("mode");
                    try {
                        String map = session.getString("map");
                        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "-----------------------\n" + EnumChatFormatting.GREEN + name + EnumChatFormatting.DARK_GREEN + "'s status on hypixel.\n" +
                                EnumChatFormatting.DARK_AQUA + "uuid: " + EnumChatFormatting.AQUA + UUID + "\n" + EnumChatFormatting.DARK_AQUA + "gameType: " + EnumChatFormatting.AQUA + gameType + "\n" +
                                EnumChatFormatting.DARK_AQUA + "mode: " + EnumChatFormatting.AQUA + mode + "\n" + EnumChatFormatting.DARK_AQUA + "map: " + EnumChatFormatting.AQUA + map + "\n" + EnumChatFormatting.GOLD + "-----------------------"));

                    } catch (JSONException exception) {
                        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "-----------------------\n" + EnumChatFormatting.GREEN + name + EnumChatFormatting.DARK_GREEN + "'s status on hypixel.\n" +
                                EnumChatFormatting.DARK_AQUA + "uuid: " + EnumChatFormatting.AQUA + UUID + "\n" + EnumChatFormatting.DARK_AQUA + "gameType: " + EnumChatFormatting.AQUA + gameType + "\n" +
                                EnumChatFormatting.DARK_AQUA + "mode: " + EnumChatFormatting.AQUA + mode + "\n" + EnumChatFormatting.GOLD + "-----------------------"));

                    }
                } else {
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Could not find any information on " + name + "\n" + EnumChatFormatting.RED + "Possible reasons:"
                            + EnumChatFormatting.RED + "1. They are offline\n" + EnumChatFormatting.RED + "2. They are afk in limbo.\n" + EnumChatFormatting.RED + "3. They have their api off." +
                            EnumChatFormatting.RED + "4. You spelled their name wrong dumbass."));
                }
            } catch (JSONException exception) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid Username."));
            } catch (IOException exception) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid Username."));
            }

        } finally {
            return;
        }
    }


}
