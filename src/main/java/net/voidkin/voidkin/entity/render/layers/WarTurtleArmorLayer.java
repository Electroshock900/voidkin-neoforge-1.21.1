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
import net.voidkin.voidkin.entity.custom.WarTurtle;
import net.voidkin.voidkin.entity.model.War_Turtle_Model;
import net.voidkin.voidkin.item.armor.WarTurtleArmor;
import net.voidkin.voidkin.item.ModItems;

import java.util.Map;

public class WarTurtleArmorLayer extends RenderLayer<WarTurtle, War_Turtle_Model<WarTurtle>> {
    private final War_Turtle_Model<WarTurtle> model;

    public WarTurtleArmorLayer(RenderLayerParent<WarTurtle, War_Turtle_Model<WarTurtle>> renderer, EntityModelSet models) {
        super(renderer);
        model = new War_Turtle_Model<>(models.bakeLayer(War_Turtle_Model.LAYER_LOCATION));
    }

    private Map<Item, ResourceLocation> ARMOR_MAP = Map.of(
            ModItems.WAR_TURTLE_ARMOR.get(), ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/warturtle/armor/iron_war_turtle_armor.png"),
            //ModItems.GOLD_WARTURTLE_ARMOR.get(), ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/warturtle/armor/gold_warturtle.png"),
            //ModItems.DIAMOND_WARTURTLE_ARMOR.get(), ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/warturtle/armor/diamond_warturtle.png"),
            ModItems.NETHERITE_WAR_TURTLE_ARMOR.get(), ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/warturtle/armor/netherite_war_turtle_armor.png")
            //ModItems.AZURITE_WARTURTLE_ARMOR.get(), ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/warturtle/armor/azurite_warturtle.png")
    );



    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight,
                       WarTurtle livingEntity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        if (livingEntity.isWearingBodyArmor()) {
            ItemStack itemstack = livingEntity.getBodyArmorItem();
            if (itemstack.getItem() instanceof WarTurtleArmor armorItem) {
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
