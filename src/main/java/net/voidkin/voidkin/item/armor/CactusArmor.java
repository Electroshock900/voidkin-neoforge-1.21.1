package net.voidkin.voidkin.item.armor;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import net.voidkin.voidkin.enchantments.ModEnchantments;
import net.voidkin.voidkin.util.ModArmorMaterials;

import java.util.Map;

public class CactusArmor extends ModArmorItem {
    public CactusArmor(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(ModArmorMaterials.CACTUS, pType, new Properties());
    }


    @Override
    public ItemEnchantments getAllEnchantments(ItemStack stack, HolderLookup.RegistryLookup<Enchantment> lookup) {
        stack.enchant(lookup.get(Enchantments.THORNS).get(),5);
        stack.enchant(lookup.get(Enchantments.PROJECTILE_PROTECTION).get(),5);
        return super.getAllEnchantments(stack, lookup);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if(entity instanceof Player player && !level.isClientSide() && hasFullSuitOfArmorOn(player)) {
            if (hasPlayerCorrectArmorOn(ModArmorMaterials.CACTUS, player)){
                //player.getInventory().getArmor(slotId).enchant(ModEnchantments.CACTUS_KIN);
            }
        }
    }

    public boolean hasPlayerCorrectArmorOn(Holder<ArmorMaterial> mapArmorMaterial, Player player) {
        for(ItemStack armorStack : player.getArmorSlots()) {
            if(!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem) player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmor(1).getItem());
        ArmorItem chestplate = ((ArmorItem) player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmor(3).getItem());

        return boots.getMaterial() == mapArmorMaterial && leggings.getMaterial() == mapArmorMaterial
                && chestplate.getMaterial() == mapArmorMaterial && helmet.getMaterial() == mapArmorMaterial;
    }
}
