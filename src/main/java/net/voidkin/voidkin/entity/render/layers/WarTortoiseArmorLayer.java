package net.voidkin.voidkin.entity.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.custom.WarTortoise;
import net.voidkin.voidkin.entity.model.War_Tortoise_Model;
import net.voidkin.voidkin.item.armor.WarTortoiseArmor;
import net.voidkin.voidkin.item.ModItems;

import java.util.Map;

public class WarTortoiseArmorLayer extends RenderLayer<WarTortoise, War_Tortoise_Model<WarTortoise>> {
    private final War_Tortoise_Model<WarTortoise> model;

    public WarTortoiseArmorLayer(RenderLayerParent<WarTortoise, War_Tortoise_Model<WarTortoise>> renderer, EntityModelSet models) {
        super(renderer);
        model = new War_Tortoise_Model<>(models.bakeLayer(War_Tortoise_Model.ARMOR_LAYER_LOCATION));
    }

    private Map<Item, ResourceLocation> ARMOR_MAP = Map.of(
            ModItems.WAR_TORTOISE_ARMOR.get(), ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/wartortoise/armor/iron_war_tortoise_armor.png")
            //ModItems.GOLD_WARTORTOISE_ARMOR.get(), ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/wartortoise/armor/gold_wartortoise.png"),
            //ModItems.DIAMOND_WARTORTOISE_ARMOR.get(), ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/wartortoise/armor/diamond_wartortoise.png"),
            //ModItems.NETHERITE_WAR_TORTOISE_ARMOR.get(), ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/wartortoise/armor/netherite_war_tortoise_armor.png")
            //ModItems.AZURITE_WARTORTOISE_ARMOR.get(), ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/wartortoise/armor/azurite_wartortoise.png")
    );



    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight,
                       WarTortoise livingEntity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        if (livingEntity.isWearingBodyArmor()) {
            ItemStack itemstack = livingEntity.getBodyArmorItem();
            if (itemstack.getItem() instanceof WarTortoiseArmor armorItem) {
                this.getParentModel().copyPropertiesTo(this.model);
                this.model.prepareMobModel(livingEntity, limbSwing, limbSwingAmount, partialTick);
                this.model.setupAnim(livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(ARMOR_MAP.get(armorItem)));
                this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
                //this.maybeRenderColoredLayer(poseStack, bufferSource, packedLight, livingEntity, armorItem);
            }
        }
    }
}
