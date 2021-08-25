package io.github.Hypermnesiaa.hypermod.Utils;

import io.github.Hypermnesiaa.hypermod.GUI.RandomHippo;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderGuiHandler {

@SubscribeEvent
	public void onRenderGui(RenderGameOverlayEvent.Post event) {
		if (event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE) return;
		new RandomHippo(Minecraft.getMinecraft());
	}
}