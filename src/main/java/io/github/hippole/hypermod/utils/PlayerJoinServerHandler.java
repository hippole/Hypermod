package io.github.hippole.hypermod.utils;

import io.github.hippole.hypermod.Hypermod;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerJoinServerHandler {

    boolean runOnce = true;

    @SubscribeEvent
    public void onPlayerJoinServer(EntityJoinWorldEvent event) {
        try {
            if (Hypermod.sbaLoaded) {
                if (runOnce) {
                    runOnce = false;
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW +
                            "[Warning] The mod \"SkyblockAddons\" is not compatible with the RedScreen part of Hypermod. " +
                            "The feature will be disabled to prevent bugs."));
                }
            }
        } catch (NullPointerException e) {
            runOnce = true;
        }
    }
}