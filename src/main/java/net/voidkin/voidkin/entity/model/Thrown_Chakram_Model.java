package net.voidkin.voidkin.entity.model;// Made with Blockbench 4.7.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.projectiles.Thrown_Chakram;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class Thrown_Chakram_Model<T extends Thrown_Chakram> extends HierarchicalModel<Thrown_Chakram> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "thrown_chakram"), "main");
	private final ModelPart group2;

	public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Voidkin.MODID,"textures/entity/projectile/chakram.png");
	public Thrown_Chakram_Model(ModelPart root) {
		this.group2 = root.getChild("group2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition group2 = partdefinition.addOrReplaceChild("group2", CubeListBuilder.create().texOffs(0, 8).addBox(-12.5F, -1.0F, 4.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-5.5F, -1.0F, 4.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(11, 0).addBox(-11.5F, -1.0F, 3.5F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(11, 3).addBox(-11.5F, -1.0F, 10.5F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition group3 = group2.addOrReplaceChild("group3", CubeListBuilder.create().texOffs(11, 8).addBox(-8.5F, -1.0F, 5.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(-0.25F))
		.texOffs(0, 16).addBox(-11.0F, -1.0F, 7.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition group4 = group2.addOrReplaceChild("group4", CubeListBuilder.create().texOffs(15, 15).addBox(-9.0F, -1.5F, 6.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(-0.65F))
		.texOffs(0, 18).addBox(-9.5F, -1.5F, 7.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(-0.65F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition group5 = group2.addOrReplaceChild("group5", CubeListBuilder.create().texOffs(22, 15).addBox(-15.0F, -1.0F, 7.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F))
		.texOffs(19, 25).addBox(-14.5F, -1.0F, 7.25F, 1.5F, 1.0F, 1.5F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition group6 = group2.addOrReplaceChild("group6", CubeListBuilder.create().texOffs(7, 23).addBox(-4.0F, -1.0F, 7.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F))
		.texOffs(25, 21).addBox(-3.0F, -1.0F, 7.25F, 1.5F, 1.0F, 1.5F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition group7 = group2.addOrReplaceChild("group7", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition spike_r1 = group7.addOrReplaceChild("spike_r1", CubeListBuilder.create().texOffs(0, 24).addBox(-0.75F, -1.0F, -7.0F, 1.5F, 1.0F, 1.5F, new CubeDeformation(-0.25F))
		.texOffs(10, 18).addBox(-0.5F, -1.0F, -7.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 8.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition group8 = group2.addOrReplaceChild("group8", CubeListBuilder.create().texOffs(20, 21).addBox(-8.5F, -1.0F, 12.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.25F))
		.texOffs(6, 25).addBox(-8.75F, -1.0F, 13.0F, 1.5F, 1.0F, 1.5F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition group9 = group2.addOrReplaceChild("group9", CubeListBuilder.create().texOffs(15, 20).addBox(-8.5F, -1.0F, 1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.25F))
		.texOffs(13, 24).addBox(-8.75F, -1.0F, 1.5F, 1.5F, 1.0F, 1.5F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition group10 = group2.addOrReplaceChild("group10", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition spike_r2 = group10.addOrReplaceChild("spike_r2", CubeListBuilder.create().texOffs(0, 22).addBox(-7.5F, -1.0F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F))
		.texOffs(23, 18).addBox(-7.0F, -1.0F, -0.75F, 1.5F, 1.0F, 1.5F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 8.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition group11 = group2.addOrReplaceChild("group11", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition spike_r3 = group11.addOrReplaceChild("spike_r3", CubeListBuilder.create().texOffs(24, 6).addBox(-0.75F, -1.0F, 5.5F, 1.5F, 1.0F, 1.5F, new CubeDeformation(-0.25F))
		.texOffs(19, 6).addBox(-0.5F, -1.0F, 4.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 8.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition group = group2.addOrReplaceChild("group", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition spike_r4 = group.addOrReplaceChild("spike_r4", CubeListBuilder.create().texOffs(24, 10).addBox(-0.75F, -1.0F, 5.5F, 1.5F, 1.0F, 1.5F, new CubeDeformation(-0.25F))
		.texOffs(19, 10).addBox(-0.5F, -1.0F, 4.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 8.0F, 0.0F, 0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}



	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		group2.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart root() {
		return group2;
	}

	/**
	@Override
	public ResourceLocation getModelResource(Cactus_Buddy animatable) {
		return ResourceLocation.fromNamespaceAndPath(DeathMod.MODID,"geo/chakram.geo.json");

	}

	@Override
	public ResourceLocation getTextureResource(Cactus_Buddy animatable) {
		return ResourceLocation.fromNamespaceAndPath(DeathMod.MODID,"textures/entity/chakram.png");
	}

	@Override
	public ResourceLocation getAnimationResource(Thrown_Chakram animatable) {
		return null;
	}
**/
	@Override
	public void setupAnim(Thrown_Chakram p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {

	}


}