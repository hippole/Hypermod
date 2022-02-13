package io.github.Hypermnesiaa.hypermod.commands;

import io.github.Hypermnesiaa.hypermod.misc.Misc;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Fancy extends CommandBase {

    private static long titaniumNotifMillis = 0; // this is also part of neu

    public String getCommandDescription() {
        return "fancy";
    }

    public String getCommandAlias() {
        return "";
    }


    @Override
    public String getCommandName() {
        return "fancy";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        Thread thread = new Thread() {
            public void run() {
                Misc.playCustomSound("fancy");
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "F" + EnumChatFormatting.GOLD + "a" +
                        EnumChatFormatting.GREEN + "n" + EnumChatFormatting.BLUE + "c" + EnumChatFormatting.DARK_BLUE + "y" + EnumChatFormatting.DARK_PURPLE + "!"));
                titaniumNotifMillis = System.currentTimeMillis();
            }
        };
        thread.start();
    }

    // everything here and below was taken straight out of not enough updates: https://github.com/Moulberry/NotEnoughUpdates/blob/master/src/main/java/io/github/moulberry/notenoughupdates/miscfeatures/MiningStuff.java
    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if(titaniumNotifMillis <= 0) return;

        int delta = (int)(System.currentTimeMillis() - titaniumNotifMillis);
        int notifLen = 5000;
        int fadeLen = 500;
        if(delta > 0 && delta < notifLen && event.type == RenderGameOverlayEvent.ElementType.ALL) {
            ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
            int width = scaledResolution.getScaledWidth();
            int height = scaledResolution.getScaledHeight();

            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);

            GlStateManager.pushMatrix();
            GlStateManager.translate((float)(width / 2), (float)(height / 2), 0.0F);
            GlStateManager.scale(4.0F, 4.0F, 4.0F);

            int colour1 = 0xcc;
            int colour2 = 0xff;

            double factor = (Math.sin(delta*2*Math.PI/1000)+1)/2;
            int colour = (int)(colour1*factor + colour2*(1-factor));

            int alpha = 255;
            if(delta < fadeLen) {
                alpha = delta*255/fadeLen;
            } else if(delta > notifLen-fadeLen) {
                alpha = (notifLen-delta)*255/fadeLen;
            }

            if(alpha > 10) {
                this.drawStringCenteredScaledMaxWidth("Fancy!", Minecraft.getMinecraft().fontRendererObj,
                        0, 0, true, width/4-20, colour | (colour << 8) | (colour << 16) | (alpha << 24));
            }


            GlStateManager.popMatrix();
        }
    }

    public static void drawStringCenteredScaledMaxWidth(String str, FontRenderer fr, float x, float y, boolean shadow, int len, int colour) {
        int strLen = fr.getStringWidth(str);
        float factor = len/(float)strLen;
        factor = Math.min(1, factor);
        int newLen = Math.min(strLen, len);

        float fontHeight = 8*factor;

        drawStringScaled(str, fr, x-newLen/2, y-fontHeight/2, shadow, colour, factor);
    }
    public static void drawStringScaled(String str, FontRenderer fr, float x, float y, boolean shadow, int colour, float factor) {
        GlStateManager.scale(factor, factor, 1);
        fr.drawString(str, x/factor, y/factor, colour, shadow);
        GlStateManager.scale(1/factor, 1/factor, 1);
    }
}