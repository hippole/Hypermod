package io.github.Hypermnesiaa.hypermod.gui;

import io.github.Hypermnesiaa.hypermod.Hypermod;
import io.github.Hypermnesiaa.hypermod.utils.ConfigHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RedScreen {

    int playerHealth;

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void renderOverlay(RenderGameOverlayEvent.Post event) {
        if (ConfigHandler.zombiesEnabled) {
            playerHealth = (int) Minecraft.getMinecraft().thePlayer.getHealth();
            if (event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE) {
                return;
            }
            if (playerHealth <= 5) {
                float finalWidth = Minecraft.getMinecraft().displayWidth;
                float finalHeight = Minecraft.getMinecraft().displayHeight;

                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Hypermod.MODID, "textures/RedScreen.png"));

                Gui.drawModalRectWithCustomSizedTexture(0, 0, finalWidth, finalHeight, (int) finalWidth, (int) finalHeight, finalWidth, finalHeight);
            }
        }
    }
}
