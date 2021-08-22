package io.github.Hypermnesiaa.hypermod.GUI;

import io.github.Hypermnesiaa.hypermod.Hypermod;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class FancyGui extends GuiScreen {

    //obsolete

    private GuiButton ButtonClose;

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(ButtonClose = new GuiButton(0, this.width / 2 - 100, this.height - (this.height / 4) + 10, "Close"));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button == ButtonClose) {
            mc.thePlayer.closeScreen();
        }
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Hypermod.MODID + ":" + "textures/gui/fancy.png"));
        this.drawTexturedModalRect( width / 2, (height / 2) - 4, 0, 0, width, height);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }
}