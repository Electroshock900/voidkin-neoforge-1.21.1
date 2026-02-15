package net.voidkin.voidkin.menu.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.block.blockentity.VoidAltarBlockEntity;
import net.voidkin.voidkin.menu.VoidAltarMenu;

import java.util.List;


public class VoidAltarScreen extends AbstractContainerScreen<VoidAltarMenu> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/gui/altar_gui.png");

    public VoidAltarScreen(VoidAltarMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
        ItemStack stack = menu.getItemFromPedestals();
        if(!stack.isEmpty()) {
            guiGraphics.renderItem(stack, 0, 0);
        }
        renderItemStacksfromPedestals(guiGraphics,x,y);
        //renderProgressArrow(guiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(TEXTURE, x + 195, y + 30, 182, 0, 8, menu.getScaledProgress());
        }
    }

    private void renderItemStacksfromPedestals(GuiGraphics guiGraphics, int x, int y){
        VoidAltarBlockEntity altar = ((VoidAltarBlockEntity) menu.blockEntity);
        List<ItemStack> stacks = menu.getNeighborStacks(altar.getLevel(), altar.getBlockPos());
        int i = 0;
        for (ItemStack stack : stacks) {
            if (!stack.isEmpty()) {
                int x2 = leftPos + 26 + (i % 1) * 27;
                int y2 = topPos + 6 + (i / 3) * 28;
                guiGraphics.renderItem(stack, x2, y2);
            }
            i++;
        }
        /*for(Vector2i offset : AltarPedestalBlockEntity.offsets) {
            //altar.offsets.forEach(offset2 ->{
            if (isPositionEmpty(pLevel, pPos.offset(offset.x, 0, offset.y))) {
                placePedestal(pLevel, pPos.offset(offset.x, 0, offset.y));
                pStack.shrink(1);
                pLevel.playSound(pPlayer, pPos.offset(offset.x, 0, offset.y), SoundEvents.STONE_PLACE, SoundSource.BLOCKS, 1f, -1f);
            }

        }*/
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}