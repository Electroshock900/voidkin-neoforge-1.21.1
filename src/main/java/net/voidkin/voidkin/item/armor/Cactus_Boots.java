package net.voidkin.voidkin.item.armor;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.voidkin.voidkin.util.ModArmorMaterials;

import java.util.Map;

public class Cactus_Boots extends ArmorItem {
    public Cactus_Boots(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(ModArmorMaterials.CACTUS, Type.BOOTS, new Properties());
    }

/*
    @Override
    public Map<Enchantment, Integer> getAllEnchantments(ItemStack stack) {
        stack.enchant(Enchantments.THORNS,13);
        stack.enchant(Enchantments.PROJECTILE_PROTECTION,5);
        return super.getAllEnchantments(this.getDefaultInstance());
    }*/
}
