package net.voidkin.voidkin.menu.screens.custom;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.voidkin.voidkin.entity.custom.cheesehorse.AbstractCheeseHorse;

@OnlyIn(Dist.CLIENT)
public class AbstractCheeseHorseScreen extends AbstractContainerScreen<AbstractCheeseHorseMenu> {
    private static final ResourceLocation CHEST_SLOTS_SPRITE = ResourceLocation.withDefaultNamespace("container/horse/chest_slots");
    private static final ResourceLocation SADDLE_SLOT_SPRITE = ResourceLocation.withDefaultNamespace("container/horse/saddle_slot");
    private static final ResourceLocation LLAMA_ARMOR_SLOT_SPRITE = ResourceLocation.withDefaultNamespace("container/horse/llama_armor_slot");
    private static final ResourceLocation ARMOR_SLOT_SPRITE = ResourceLocation.withDefaultNamespace("container/horse/armor_slot");
    private static final ResourceLocation HORSE_INVENTORY_LOCATION = ResourceLocation.withDefaultNamespace("textures/gui/container/horse.png");
    private final AbstractCheeseHorse horse;
    private final int inventoryColumns;
    private float xMouse;
    private float yMouse;
    /*public AbstractCheeseHorseScreen(AbstractCheeseHorseMenu menu, Inventory inventory, AbstractCheeseHorse horse, int inventoryColumns) {
        super(menu, inventory, horse.getDisplayName());
        this.horse = menu.cheeseHorse;
        this.inventoryColumns = inventoryColumns;
    }*/

    public AbstractCheeseHorseScreen(AbstractCheeseHorseMenu pMenu, Inventory pPlayerInventory, Component title) {
        super(pMenu, pPlayerInventory, pMenu.cheeseHorse.getDisplayName());
        this.horse = pMenu.cheeseHorse;
        this.inventoryColumns = 4;
    }
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(HORSE_INVENTORY_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight);
        if (this.inventoryColumns > 0) {
            guiGraphics.blitSprite(CHEST_SLOTS_SPRITE, 90, 54, 0, 0, i + 79, j + 17, 4 * 18, 54);
            //guiGraphics.blitSprite(CHEST_SLOTS_SPRITE, 175, 165, 0, 0, i + 79, j + 17, 4 * 18, 54);
        }

        if (this.horse.isSaddleable()) {
            guiGraphics.blitSprite(SADDLE_SLOT_SPRITE, i + 7, j + 35 - 18, 18, 18);
        }

        if (this.horse.canUseSlot(EquipmentSlot.BODY)) {
                guiGraphics.blitSprite(ARMOR_SLOT_SPRITE, i + 7, j + 35, 18, 18);
        }

        InventoryScreen.renderEntityInInventoryFollowsMouse(guiGraphics, i + 26, j + 18, i + 78, j + 70, 17, 0.25F, this.xMouse, this.yMouse, this.horse);
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.xMouse = (float)mouseX;
        this.yMouse = (float)mouseY;
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
