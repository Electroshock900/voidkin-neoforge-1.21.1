package net.voidkin.voidkin.item.armor;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.item.armor.model.AbyssArmorModel;
import net.voidkin.voidkin.item.armor.model.provider.ArmorModelProvider;
import net.voidkin.voidkin.item.armor.model.provider.SimpleModelProvider;
import net.voidkin.voidkin.util.ModArmorMaterials;
import net.voidkin.voidkin.util.ModMaterials;

import javax.annotation.Nullable;

public class AbyssArmorItem extends AbstractArmorItem{
    private static final ResourceLocation TEXTURE_LOCATION = makeCustomTextureLocation(Voidkin.MODID, "abyss_armor");
    public AbyssArmorItem(Type pType) {
        super(ModArmorMaterials.DARK, pType, new Properties().rarity(Rarity.RARE));
    }

    @Override
    public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean inner) {
        return TEXTURE_LOCATION;
    }
}
