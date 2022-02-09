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

public class PowerUps {

    private String latestPowerUp = "";

    public String getLatestPowerUp() {
        return latestPowerUp;
    }

    public void setLatestPowerUp(String powerUp) {
        this.latestPowerUp = powerUp;
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.ClientTickEvent event) throws IllegalAccessException {
        if (ConfigHandler.zombiesEnabled) {
            checkSubTitle();
        }
    }

    @SubscribeEvent
    public void onWorldChange(WorldEvent.Load event) throws NullPointerException {
        setLatestPowerUp("");
    }

    @SubscribeEvent
    public void onPlayerDisconnect(PlayerEvent.PlayerLoggedOutEvent event) {
        setLatestPowerUp("");
    }

    public boolean antiPowerUpSpam(String content) {
        if (getLatestPowerUp().equals(content)) {
            return false;
        } else {
            setLatestPowerUp(content);
            return true;
        }
    }

    public void checkSubTitle() throws IllegalAccessException {
        String subtitle = (String) ReflectionHelper.findField(GuiIngame.class, "displayedSubTitle", "field_175200_y").get(Minecraft.getMinecraft().ingameGUI);
        if (antiPowerUpSpam(subtitle)) {
            if (subtitle.startsWith(EnumChatFormatting.BLUE + "Max Ammo")) {
                if (Minecraft.getMinecraft().getCurrentServerData().serverIP.contains("hypixel.net")) {
                    Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation(Hypermod.MODID, "maxammo"), 1.0F));
                }
            }
            if (subtitle.startsWith(EnumChatFormatting.GOLD + "Double Gold")) {
                if (Minecraft.getMinecraft().getCurrentServerData().serverIP.contains("hypixel.net")) {
                    Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation(Hypermod.MODID, "doublegold"), 1.0F));
                }
            }
            if (subtitle.startsWith(EnumChatFormatting.RED + "Insta Kill")) {
                if (Minecraft.getMinecraft().getCurrentServerData().serverIP.contains("hypixel.net")) {
                    Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation(Hypermod.MODID, "instakill"), 1.0F));
                }
            }
            if (subtitle.startsWith(EnumChatFormatting.BLUE + "Carpenter")) {
                if (Minecraft.getMinecraft().getCurrentServerData().serverIP.contains("hypixel.net")) {
                    Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation(Hypermod.MODID, "carpenter"), 1.0F));
                }
            }
            if (subtitle.startsWith(EnumChatFormatting.DARK_PURPLE + "Shopping Spree")) {
                if (Minecraft.getMinecraft().getCurrentServerData().serverIP.contains("hypixel.net")) {
                    Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation(Hypermod.MODID, "shoppingspree"), 1.0F));
                }
            }
            if (subtitle.startsWith(EnumChatFormatting.GOLD + "Bonus Gold")) {
                if (Minecraft.getMinecraft().getCurrentServerData().serverIP.contains("hypixel.net")) {
                    Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation(Hypermod.MODID, "bonusgold"), 1.0F));
                }
            }
        }
    }
}