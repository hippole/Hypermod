package io.github.Hypermnesiaa.hypermod;

import io.github.Hypermnesiaa.hypermod.commands.Fancy;
import io.github.Hypermnesiaa.hypermod.commands.TestSound;
import io.github.Hypermnesiaa.hypermod.commands.hypixel.*;
import io.github.Hypermnesiaa.hypermod.commands.RandomWord;
import io.github.Hypermnesiaa.hypermod.commands.hypixel.zombies.PowerUps;
import io.github.Hypermnesiaa.hypermod.commands.hypixel.zombies.Round;
import io.github.Hypermnesiaa.hypermod.gui.RedScreen;
import io.github.Hypermnesiaa.hypermod.utils.ConfigHandler;
import io.github.Hypermnesiaa.hypermod.utils.RenderGuiHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Mod(modid = Hypermod.MODID, version = Hypermod.VERSION, clientSideOnly = true)
public class Hypermod extends CommandBase {

    public static int GEXP = 0;

    public static final String MODID = "hypermod";
    public static final String VERSION = "1.9";

    private final RandomWord randomWord = new RandomWord();
    private final Coordinates coordinates = new Coordinates();
    private final Fancy fancy = new Fancy();
    private final HypixelStatus hypixelStatus = new HypixelStatus();
    private final ApiKey apiKey = new ApiKey();
    private final GEXP gexp = new GEXP(this);
    private final Reque reque = new Reque();
    private final TestSound testSound = new TestSound();

    List<CommandBase> commands = Arrays.asList(randomWord,coordinates,fancy,hypixelStatus,apiKey,gexp,reque/*testsound*/);
    List<String> commandDescriptions = Arrays.asList(randomWord.getCommandDescription(),coordinates.getCommandDescription(),fancy.getCommandDescription()
            ,hypixelStatus.getCommandDescription(),apiKey.getCommandDescription(),gexp.getCommandDescription(),reque.getCommandDescription/*,testsound.getCommandDescription*/());

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ConfigHandler.init(new File("./config/Hypermod.cfg"));
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new GEXP(this));
        MinecraftForge.EVENT_BUS.register(new RenderGuiHandler());
        MinecraftForge.EVENT_BUS.register(new Fancy());
        MinecraftForge.EVENT_BUS.register(new Round());
        MinecraftForge.EVENT_BUS.register(new PowerUps());
        MinecraftForge.EVENT_BUS.register(new RedScreen());
        try {
            ClientCommandHandler.instance.registerCommand(randomWord);
            ClientCommandHandler.instance.registerCommand(coordinates);
            ClientCommandHandler.instance.registerCommand(fancy);
            ClientCommandHandler.instance.registerCommand(hypixelStatus);
            ClientCommandHandler.instance.registerCommand(apiKey);
            ClientCommandHandler.instance.registerCommand(gexp);
            ClientCommandHandler.instance.registerCommand(reque);
            ClientCommandHandler.instance.registerCommand(testSound);
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
        return "/hypermod <Command>";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        try {
            if (args.length == 0) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Hypermod " + VERSION + "\n" +
                        EnumChatFormatting.GREEN + "Made by Hypermnesia. (but most people call me hippo)\n" +
                        EnumChatFormatting.DARK_GREEN + "To get started use /Hypermod <Command Name>, You can find a whole list of commands with /Hypermod list\n" +
                        EnumChatFormatting.AQUA + "Here's the source code: https://github.com/Hypermnesiaa/Hypermod ,anyway have fun bye."));
            }
            if (args[0].equalsIgnoreCase("list")) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
                        EnumChatFormatting.GOLD + "=======================\n" +
                            EnumChatFormatting.AQUA + "/randomWord\n" +
                            EnumChatFormatting.AQUA + "/coordinates | /co\n" +
                            EnumChatFormatting.AQUA + "/fancy\n" +
                            EnumChatFormatting.AQUA + "/hypixelstatus | /hs\n" +
                            EnumChatFormatting.AQUA + "/apikey\n" +
                            EnumChatFormatting.AQUA + "/gexp\n" +
                            EnumChatFormatting.AQUA + "/reque | rq\n" +
                            EnumChatFormatting.GOLD + "======================="));
            }
            for (int i = 0; i < commandDescriptions.size(); i++) {
                String commandDescription = commandDescriptions.get(i);
                String commandName = commands.get(i).getCommandName();
                String commandUsage = commands.get(i).getCommandUsage(sender);
                if (args[0].equalsIgnoreCase(commandName)) {
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
                        EnumChatFormatting.GOLD + "Command Name: " + commandName + "\n" +
                            EnumChatFormatting.GREEN + "Command Usage: " + commandUsage + "\n" +
                            EnumChatFormatting.GREEN + "Command Description: " + commandDescription));
                }
            }

        } catch (ArrayIndexOutOfBoundsException e) {

        }
    }

}
