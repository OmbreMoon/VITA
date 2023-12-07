package net.ombremoon.vita.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.ombremoon.vita.VitaMod;

public class TimerOverlay {
    private static final ResourceLocation TIMER_HUD = VitaMod.resourceLocation("textures/gui/timer_bar.png");

    public static final IGuiOverlay HUD_TIMER = ((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TIMER_HUD);
        int x = screenWidth / 2;
        int y = screenHeight / 90;

        Player player = Minecraft.getInstance().player;
        if (player != null && !player.isCreative()) {
            guiGraphics.blit(TIMER_HUD, x - 80, y, 0, 0, 145, 6, 145, 12);

            renderTimer(guiGraphics, x, y);
        }
    });

    private static void renderTimer(GuiGraphics guiGraphics, int x, int y) {
        guiGraphics.blit(TIMER_HUD, x - 80, y, 0, 6, TimerSync.getScaledTimer(), 6, 145, 12);
    }
}
