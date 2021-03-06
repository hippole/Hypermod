package io.github.hippole.hypermod;

import io.github.hippole.hypermod.commands.*;
import io.github.hippole.hypermod.commands.hypixel.*;
import io.github.hippole.hypermod.gui.RedScreen;
import io.github.hippole.hypermod.random.zombies.PowerUps;
import io.github.hippole.hypermod.random.zombies.Round;
import io.github.hippole.hypermod.utils.ConfigHandler;
import io.github.hippole.hypermod.utils.PlayerJoinServerHandler;
import io.github.hippole.hypermod.utils.RenderGuiHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Mod(modid = Hypermod.MODID, version = Hypermod.VERSION, clientSideOnly = true)
public class Hypermod extends CommandBase {

    public static int GEXP = 0;

    public static boolean sbaLoaded;

    public static final String MODID = "hypermod";
    public static final String VERSION = "1.10.1";

    private final RandomWord randomWord = new RandomWord();
    private final Coordinates coordinates = new Coordinates();
    private final Fancy fancy = new Fancy();
    private final HypixelStatus hypixelStatus = new HypixelStatus();
    private final ApiKey apiKey = new ApiKey();
    private final GEXP gexp = new GEXP(this);
    private final Reque reque = new Reque();
    private final TestSound testSound = new TestSound();
    private final EatMinecraft eatMinecraft = new EatMinecraft();
    private final Wordle wordle = new Wordle();

    List<CommandBase> commands = Arrays.asList(randomWord,coordinates,fancy,hypixelStatus,apiKey,gexp,reque/*testsound*/,eatMinecraft,wordle);
    List<String> commandDescriptions = Arrays.asList(randomWord.getCommandDescription(),coordinates.getCommandDescription(),fancy.getCommandDescription()
            ,hypixelStatus.getCommandDescription(),apiKey.getCommandDescription(),gexp.getCommandDescription(),reque.getCommandDescription()
            /*,testsound.getCommandDescription*/,eatMinecraft.getCommandDescription(),wordle.getCommandDescription());
    List<String> commandAliases = Arrays.asList(randomWord.getCommandAlias(),coordinates.getCommandAlias(),fancy.getCommandAlias()
            ,hypixelStatus.getCommandAlias(),apiKey.getCommandAlias(),gexp.getCommandAlias(),reque.getCommandAlias()
            /*,testsound.getCommandAlias*/,eatMinecraft.getCommandAlias(),wordle.getCommandAlias());

    @EventHandler
    public void init(FMLInitializationEvent event) {
        sbaLoaded = Loader.isModLoaded("skyblockaddons");
        ConfigHandler.init(new File("./config/Hypermod.cfg"));
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new GEXP(this));
        MinecraftForge.EVENT_BUS.register(new RenderGuiHandler());
        MinecraftForge.EVENT_BUS.register(new Fancy());
        MinecraftForge.EVENT_BUS.register(new Round());
        MinecraftForge.EVENT_BUS.register(new PowerUps());
        MinecraftForge.EVENT_BUS.register(new RedScreen());
        MinecraftForge.EVENT_BUS.register(new EatMinecraft());
        MinecraftForge.EVENT_BUS.register(new PlayerJoinServerHandler());
        try {
            ClientCommandHandler.instance.registerCommand(randomWord);
            ClientCommandHandler.instance.registerCommand(coordinates);
            ClientCommandHandler.instance.registerCommand(fancy);
            ClientCommandHandler.instance.registerCommand(hypixelStatus);
            ClientCommandHandler.instance.registerCommand(apiKey);
            ClientCommandHandler.instance.registerCommand(gexp);
            ClientCommandHandler.instance.registerCommand(reque);
            ClientCommandHandler.instance.registerCommand(testSound);
            ClientCommandHandler.instance.registerCommand(eatMinecraft);
            ClientCommandHandler.instance.registerCommand(wordle);
            ClientCommandHandler.instance.registerCommand(this);
        } catch (Exception e) {
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
                        EnumChatFormatting.LIGHT_PURPLE + "Source Code:" + EnumChatFormatting.AQUA + " https://github.com/hippole/Hypermod (Currently private)"));
            }
            if (args[0].equalsIgnoreCase("list")) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
                        EnumChatFormatting.GOLD + "=======================\n" +
                            EnumChatFormatting.LIGHT_PURPLE + "List of all Hypermod commands"));
                        for (int i = 0; i < commandDescriptions.size(); i++) {
                            String commandName = commands.get(i).getCommandName();
                            String commandAlias = commandAliases.get(i);
                            if (!commandAlias.isEmpty()) {
                                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "/" + commandName + " | " + commandAlias));
                            } else {
                                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "/" + commandName));
                            }
                        }
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "======================="));
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
