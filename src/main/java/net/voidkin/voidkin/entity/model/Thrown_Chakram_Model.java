package net.voidkin.voidkin.entity.model;// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.EntityModel;
import net.minecraft.resources.ResourceLocation;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.projectiles.Thrown_Chakram;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class Thrown_Chakram_Model extends EntityModel<Thrown_Chakram> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "thrown_chakram_model"), "main");
	private final ModelPart bone;
	private final ModelPart bone2;
	private final ModelPart bone3;
	private final ModelPart bb_main;

	public Thrown_Chakram_Model(ModelPart root) {
		this.bone = root.getChild("bone");
		this.bone2 = root.getChild("bone2");
		this.bone3 = root.getChild("bone3");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 6).addBox(-1.5F, -0.5F, -7.0F, 3.0F, 1.0F, 14.0F, new CubeDeformation(0.1F)), PartPose.offset(-7.5F, 20.0F, 0.0F));

		PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(35, 22).addBox(-1.0F, -0.5F, -7.0F, 3.0F, 1.0F, 14.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(7.5F, 0.0F, -8.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r2 = bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(35, 6).addBox(-1.0F, -0.5F, -7.0F, 3.0F, 1.0F, 14.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(7.5F, 0.0F, 8.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r3 = bone.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 22).addBox(-1.5F, -0.5F, -7.0F, 3.0F, 1.0F, 14.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(15.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition bone2 = partdefinition.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(9, 46).addBox(-1.0F, -1.0F, 12.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F))
		.texOffs(47, 38).addBox(-1.0F, -1.0F, -14.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, 20.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r4 = bone2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(34, 41).addBox(-2.0F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -13.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r5 = bone2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(34, 38).addBox(8.0F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition bone3 = partdefinition.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(18, 49).addBox(-1.0F, -1.0F, 12.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F))
		.texOffs(27, 49).addBox(-1.0F, -1.0F, -14.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, 20.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r6 = bone3.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(13, 43).addBox(-2.0F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -13.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r7 = bone3.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 43).addBox(8.0F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 38).addBox(-3.0F, -5.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(26, 44).addBox(-12.0F, -5.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F))
		.texOffs(35, 44).addBox(10.0F, -5.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F))
		.texOffs(44, 44).addBox(-1.0F, -5.0F, 10.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F))
		.texOffs(0, 46).addBox(-1.0F, -5.0F, -12.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r8 = bb_main.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 3).addBox(-13.0F, -0.5F, -0.5F, 26.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r9 = bb_main.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -0.5F, -0.5F, 26.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -3.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r10 = bb_main.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(17, 38).addBox(-3.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Thrown_Chakram entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		bone2.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		bone3.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}