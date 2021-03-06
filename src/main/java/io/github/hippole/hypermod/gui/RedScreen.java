package io.github.hippole.hypermod.gui;

import io.github.hippole.hypermod.Hypermod;
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
        playerHealth = (int) Minecraft.getMinecraft().thePlayer.getHealth();
        if (event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            return;
        }
        if (playerHealth <= 5) {
            if (!Hypermod.sbaLoaded) {
                float finalWidth = Minecraft.getMinecraft().displayWidth;
                float finalHeight = Minecraft.getMinecraft().displayHeight;

                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Hypermod.MODID, "textures/RedScreen.png"));

                Gui.drawModalRectWithCustomSizedTexture(0, 0, finalWidth, finalHeight, (int) finalWidth, (int) finalHeight, finalWidth, finalHeight);
            }
        }
    }
}
