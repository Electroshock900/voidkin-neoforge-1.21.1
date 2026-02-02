package net.voidkin.voidkin.entity.model;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.animations.ModAnimationDefinitions;
import net.voidkin.voidkin.entity.custom.Mini_Skull_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class Mini_Skull_Entity_Model<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "mini_skull"), "main");
	private final ModelPart bone3;

	public Mini_Skull_Entity_Model(ModelPart root) {
		this.bone3 = root.getChild("bone3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone3 = partdefinition.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(0.0F, 9.0F, 0.0F));

		PartDefinition skull = bone3.addOrReplaceChild("skull", CubeListBuilder.create().texOffs(0, 41).addBox(-4.0F, 6.0F, -5.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(7, 35).addBox(-3.0F, 2.0F, -6.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(34, 29).addBox(1.0F, 2.0F, -6.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(32, 0).addBox(-3.0F, 4.0F, -6.5F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 29).addBox(1.0F, 2.0F, -6.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(20, 23).addBox(-4.0F, 4.0F, -6.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(10, 29).addBox(-4.0F, 6.0F, -6.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-6.0F, -6.0F, -2.0F, 12.0F, 12.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 20).addBox(-4.0F, -6.0F, -6.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(7, 38).addBox(-6.0F, -6.0F, -5.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(35, 32).addBox(4.0F, -6.0F, -5.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(24, 35).addBox(4.0F, 2.0F, -5.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 35).addBox(-6.0F, 2.0F, -5.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(-4.0F, 2.0F, -6.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(40, 3).addBox(5.0F, -3.0F, -4.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 3).addBox(-0.25F, 2.5F, -6.0F, 0.5F, 1.5F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(2, 0).addBox(-1.0F, 2.0F, -6.5F, 2.0F, 0.5F, 2.5F, new CubeDeformation(0.0F))
		.texOffs(14, 31).addBox(-1.0F, -3.0F, -5.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(17, 39).addBox(-6.0F, -3.0F, -4.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(40, 38).addBox(3.0F, 6.0F, -5.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition jaw = bone3.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 27).addBox(-4.0F, -1.0F, -6.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 20).addBox(-4.0F, 0.0F, -6.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(34, 38).addBox(3.0F, -4.0F, -2.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(40, 21).addBox(3.0F, -1.0F, -5.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(40, 10).addBox(3.0F, 0.0F, -5.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-4.0F, -4.0F, -2.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(37, 17).addBox(-4.0F, -1.0F, -5.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(32, 3).addBox(-4.0F, 0.0F, -5.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.animate(((Mini_Skull_Entity) entity).idleAnimationState, ModAnimationDefinitions.SKULL_IDLE, ageInTicks, 1f);
		this.animate(((Mini_Skull_Entity) entity).attackAnimationState, ModAnimationDefinitions.SKULL_ATTACK, ageInTicks, 1f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		bone3.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart root() {
		return bone3;
	}

}