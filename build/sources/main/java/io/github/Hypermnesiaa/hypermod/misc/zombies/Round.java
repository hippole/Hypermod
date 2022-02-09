package io.github.Hypermnesiaa.hypermod.misc.zombies;

import io.github.Hypermnesiaa.hypermod.Hypermod;
import io.github.Hypermnesiaa.hypermod.utils.ConfigHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.util.ArrayList;

public class Round {

    private boolean gameHasStarted = false;
    private ArrayList<String> executedContents = new ArrayList<String>();

    @SubscribeEvent
    public void onPlayerTick(TickEvent.ClientTickEvent event) throws IllegalAccessException {
        if (ConfigHandler.zombiesEnabled) {
            checkTitle();
        }
    }

    public boolean getGameStatus() {
        return gameHasStarted;
    }

    public void setGameStatus(boolean status) {
        this.gameHasStarted = status;
    }

    public ArrayList<String> getExecutedContents() {
        return executedContents;
    }

    public void addExecutedContents(String content) {
        this.executedContents.add(content);
    }

    public void clearExecutedContents() {
        this.executedContents.clear();
    }

    @SubscribeEvent
    public void onWorldChange(WorldEvent.Load event) throws NullPointerException {
        setGameStatus(false);
        clearExecutedContents();
    }

    @SubscribeEvent
    public void onPlayerDisconnect(PlayerEvent.PlayerLoggedOutEvent event) {
        setGameStatus(false);
        clearExecutedContents();
    }

    public boolean alreadyExecuted(String content) {
        if (getExecutedContents().contains(content)) {
            return false;
        } else {
            // If true, then it has not been executed yet and will play sound.
            addExecutedContents(content);
            return true;
        }
    }

    public void checkTitle() throws IllegalAccessException,NullPointerException {
        String title = (String) ReflectionHelper.findField(GuiIngame.class, "displayedTitle", "field_175201_x").get(Minecraft.getMinecraft().ingameGUI);
        String subtitle = (String) ReflectionHelper.findField(GuiIngame.class, "displayedSubTitle", "field_175200_y").get(Minecraft.getMinecraft().ingameGUI);
        if (alreadyExecuted(title)) {
                if (title.startsWith(EnumChatFormatting.RED + "Round 1") && !getGameStatus()) {
                    if (Minecraft.getMinecraft().getCurrentServerData().serverIP.contains("hypixel.net")) {
                        Minecraft.getMinecraft().getSoundHandler().stopSounds();
                        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation(Hypermod.MODID, "gamestart"), 1.0F));
                        setGameStatus(true);
                    }
                } else if (title.startsWith(EnumChatFormatting.GREEN + "You Win!")) {
                    if (Minecraft.getMinecraft().getCurrentServerData().serverIP.contains("hypixel.net")) {
                        Minecraft.getMinecraft().getSoundHandler().stopSounds();
                        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation(Hypermod.MODID, "gamewin1"), 1.0F));
                    }
                } else if (subtitle.startsWith(EnumChatFormatting.GRAY + "You made it to Round")) {
                    if (Minecraft.getMinecraft().getCurrentServerData().serverIP.contains("hypixel.net")) {
                        Minecraft.getMinecraft().getSoundHandler().stopSounds();
                        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation(Hypermod.MODID, "gameend1"), 1.0F));
                    }
                } else if (title.startsWith(EnumChatFormatting.RED + "Round")) {
                    if (Minecraft.getMinecraft().getCurrentServerData().serverIP.contains("hypixel.net")) {
                        Minecraft.getMinecraft().getSoundHandler().stopSounds();
                        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation(Hypermod.MODID, "roundchange"), 1.0F));
                    }
                }
        }
    }
}