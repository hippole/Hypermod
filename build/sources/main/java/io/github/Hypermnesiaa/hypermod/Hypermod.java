package io.github.Hypermnesiaa.hypermod;

import io.github.Hypermnesiaa.hypermod.Commands.Fancy;
import io.github.Hypermnesiaa.hypermod.Commands.Hypixel.*;
import io.github.Hypermnesiaa.hypermod.Commands.RandomWord;
import io.github.Hypermnesiaa.hypermod.Utils.ConfigHandler;
import io.github.Hypermnesiaa.hypermod.Utils.Define;
import io.github.Hypermnesiaa.hypermod.Utils.RenderGuiHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.io.File;

@Mod(modid = Hypermod.MODID, version = Hypermod.VERSION, clientSideOnly = true)
public class Hypermod extends CommandBase {

    public static int GEXP = 0;

    public static final String MODID = "hypermod";
    public static final String VERSION = "1.8.1-BETA";

    private final RandomWord randomWord = new RandomWord();
    private final Coordinates coordinates = new Coordinates();
    private final Fancy fancy = new Fancy();
    private final HypixelStatus hypixelStatus = new HypixelStatus();
    private final ApiKey apiKey = new ApiKey();
    private final GEXP gexp = new GEXP(this);
    private final Reque reque = new Reque();

    @EventHandler
    public void init (FMLInitializationEvent event) {
        ConfigHandler.init(new File("./config/Hypermod.cfg"));
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new GEXP(this));
        MinecraftForge.EVENT_BUS.register(new RenderGuiHandler());
        MinecraftForge.EVENT_BUS.register(new Fancy());
        try {
            ClientCommandHandler.instance.registerCommand(randomWord);
            ClientCommandHandler.instance.registerCommand(coordinates);
            ClientCommandHandler.instance.registerCommand(fancy);
            ClientCommandHandler.instance.registerCommand(hypixelStatus);
            ClientCommandHandler.instance.registerCommand(apiKey);
            ClientCommandHandler.instance.registerCommand(gexp);
            ClientCommandHandler.instance.registerCommand(reque);
            ClientCommandHandler.instance.registerCommand(this);
        } catch (Exception e) {
            System.out.println("[ERROR] Hypermod ran into a problem. Stacktrace:");
            e.printStackTrace();
        }
    }

    @Override
    public String getCommandName() {
        return "hypermod";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/hypermod <Command/Feature>";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }

    public void addNewCommandHelp(String Name, String Usage, String Description, Boolean ProblematicCommand) {
        if (ProblematicCommand) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "[WARNING] This command may not function as intended!\n" +
                    EnumChatFormatting.RED + "Command Name: " + Name + "\n" +
                    EnumChatFormatting.GOLD + "Command Usage: " + Usage + "\n" +
                    EnumChatFormatting.GREEN + "Command Description: " + Description));
        } else {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Command Name: " + Name + "\n" +
                    EnumChatFormatting.GOLD + "Command Usage: " + Usage + "\n" +
                    EnumChatFormatting.GREEN + "Command Description: " + Description));
        }
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        Define define = new Define();
        if (args.length == 0) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Hypermod " + VERSION + "\n" +
                EnumChatFormatting.GREEN + "Made by Hypermnesia. (but most people call me hippo)\n" +
                EnumChatFormatting.DARK_GREEN + "To get started use /Hypermod <Command Name>, You can find a whole list of commands in the description of Hypermod! (look in your mod options)\n" +
                EnumChatFormatting.AQUA + "Here's the source code: https://github.com/Hypermnesiaa/Hypermod ,anyway have fun bye."));
        }
        else if (args[0].equalsIgnoreCase("<Command>")) {
            define.simpleSendMessage("green", "Megamind moment");
        }
        else if (args[0].equalsIgnoreCase("randomWord")) {
            addNewCommandHelp("randomWord","/randomword","Selects a random word from a file (Specify file path in \"Hypermod.cfg\")",false);
        }
        else if (args[0].equalsIgnoreCase("coordinates")) {
            addNewCommandHelp("coordinates","/coordinates | /co","Sends your current coordinates in chat.",false);
        }
        else if (args[0].equalsIgnoreCase("hypixelStatus")) {
            addNewCommandHelp("hypixelstatus","/hypixelstatus <IGN>","Gets the target player's status on Hypixel.",false);
        }
        else if (args[0].equalsIgnoreCase("apiKey")) {
            addNewCommandHelp("apikey","/apikey","Copies your api-key to your clipboard. (Specify api-key in \"Hypermod.cfg\"",false);
        }
        else if (args[0].equalsIgnoreCase("gexp")) {
            addNewCommandHelp("gexp","/gexp","Displays your current amount of gexp earned in this current session.",true);
        }
        else if (args[0].equalsIgnoreCase("reque")) {
            addNewCommandHelp("reque","/reque <*Optional:GAMEMODE> | /rq","Instantly sends you into a minigame of your choice (Specify default minigame in \"Hypermod.cfg\"",false);
        }
        else if (args[0].equalsIgnoreCase("fancy")) {
            addNewCommandHelp("fancy","/fancy","Fancy!.",false);
        } else {
            define.simpleSendMessage("red","[ERROR] Not a valid command/feature! Take a look at Hypermod in your mod options to find commands.");
        }

}

}
