package com.tsunamicloud.tsunami.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.tsunamicloud.tsunami.screen.handler.UIBlockScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class UIBlockScreen extends HandledScreen<UIBlockScreenHandler> {

    //注册screen在Client中
    private static final Identifier TEXTURE = new Identifier("tsunami", "textures/gui/ui_block.png");

    /**
     * x：目标x坐标-1
     * y；目标y坐标-1
     * u：替代x坐标-1
     * v：替代y坐标-1
     * w：替代的宽度
     * h：替代的高度
     */


    public UIBlockScreen(UIBlockScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.passEvents = false;
        //背景图片的高度
        this.backgroundHeight = 167;
        //玩家物品栏放置的y坐标
        this.playerInventoryTitleY = this.backgroundHeight - 94;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f );
        RenderSystem.setShaderTexture(0, TEXTURE);
        int i = this.x;
        int j = this.y;
        this.drawTexture(matrices, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
        //获取tick：基于tick来绘图
        int tick = (int)(this.handler.getTick() / 20);
        this.drawTexture(matrices, i+72, j+24, 176, 2,tick * 10, 2);//同时按测算的时间更改UIBlockEntity中的转化时间
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
