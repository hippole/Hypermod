package io.github.Hypermnesiaa.hypermod.commands.hypixel;

import io.github.Hypermnesiaa.hypermod.misc.Misc;
import io.github.Hypermnesiaa.hypermod.utils.ConfigHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class HypixelStatus extends CommandBase {

    public String getCommandDescription() {
        return "Checks the specified player's current status on hypixel";
    }

    public String getCommandAlias() {
        return "/hs";
    }

    @Override
    public String getCommandName() {
        return "hypixelstatus";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName() + " player_username";
    }

    @Override
    public List getCommandAliases() {
        List list = new ArrayList<String>();
        list.add("hs");
        return list;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }

    @Override
    public void processCommand(ICommandSender sender, final String[] args) throws CommandException {
        if (args.length == 0) {
            Misc.raiseError("No username provided.");
        }
        if (ConfigHandler.hypixelApiKey.equalsIgnoreCase("")) {
            Misc.raiseError("There is no Api-Key Provided in \"Hypermod.cfg\", Add it before executing this command again.");
            return;
        }
        Thread thread = new Thread() {
            public void run() {
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
                        connection.setRequestProperty("Api-Key", ConfigHandler.hypixelApiKey);
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
                            Misc.raiseError("Could not find any information on " + name + "\n" + EnumChatFormatting.RED + "Possible reasons:\n" +
                                    EnumChatFormatting.RED + "1. They are offline\n" +
                                    EnumChatFormatting.RED + "2. They are afk in limbo.\n" +
                                    EnumChatFormatting.RED + "3. They have their api off.\n" +
                                    EnumChatFormatting.RED + "4. You spelled their name wrong dumbass.");
                        }
                    } catch (JSONException exception) {
                        Misc.raiseError("Invalid Username");
                    } catch (IOException exception) {
                        Misc.raiseError("Invalid Username");
                    }

                } finally {
                    return;
                }
            }
        };
        thread.start();
    }


}
