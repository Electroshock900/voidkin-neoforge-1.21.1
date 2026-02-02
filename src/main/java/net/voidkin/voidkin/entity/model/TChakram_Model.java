package net.voidkin.voidkin.entity.model;// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.voidkin.voidkin.Voidkin;

public class TChakram_Model<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "TChakram_Model"), "main");
	private final ModelPart chakram;
	private final ModelPart grip;
	private final ModelPart handle;
	private final ModelPart spike_bottom;
	private final ModelPart spike_top;
	private final ModelPart spike_bottom_left;
	private final ModelPart spike_right;
	private final ModelPart spike_left;
	private final ModelPart spike_top_left;
	private final ModelPart spike_top_right;
	private final ModelPart spike_bottom_right;

	public TChakram_Model(ModelPart root) {
		this.chakram = root.getChild("chakram");
		this.grip = this.chakram.getChild("grip");
		this.handle = this.chakram.getChild("handle");
		this.spike_bottom = this.chakram.getChild("spike_bottom");
		this.spike_top = this.chakram.getChild("spike_top");
		this.spike_bottom_left = this.chakram.getChild("spike_bottom_left");
		this.spike_right = this.chakram.getChild("spike_right");
		this.spike_left = this.chakram.getChild("spike_left");
		this.spike_top_left = this.chakram.getChild("spike_top_left");
		this.spike_top_right = this.chakram.getChild("spike_top_right");
		this.spike_bottom_right = this.chakram.getChild("spike_bottom_right");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition chakram = partdefinition.addOrReplaceChild("chakram", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, 2.5F, 4.5F, 1.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 9).addBox(-9.0F, -4.5F, 4.5F, 1.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(16, 7).addBox(-9.0F, -3.5F, 3.5F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(16, 16).addBox(-9.0F, -3.5F, 10.5F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 24.0F, -8.0F));

		PartDefinition grip = chakram.addOrReplaceChild("grip", CubeListBuilder.create().texOffs(16, 0).addBox(-8.75F, -0.25F, 5.25F, 0.5F, 0.5F, 5.5F, new CubeDeformation(0.0F))
		.texOffs(22, 11).addBox(-8.75F, -2.75F, 7.75F, 0.5F, 5.5F, 0.5F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition handle = chakram.addOrReplaceChild("handle", CubeListBuilder.create().texOffs(22, 18).addBox(-8.85F, -0.35F, 7.15F, 0.7F, 0.7F, 1.7F, new CubeDeformation(0.0F))
		.texOffs(22, 21).addBox(-8.85F, -0.85F, 7.65F, 0.7F, 1.7F, 0.7F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition spike_bottom = chakram.addOrReplaceChild("spike_bottom", CubeListBuilder.create().texOffs(22, 24).addBox(-8.75F, 4.25F, 7.75F, 0.5F, 2.5F, 0.5F, new CubeDeformation(0.0F))
		.texOffs(16, 25).addBox(-8.75F, 5.25F, 7.5F, 0.5F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition spike_top = chakram.addOrReplaceChild("spike_top", CubeListBuilder.create().texOffs(0, 26).addBox(-8.75F, -6.75F, 7.75F, 0.5F, 2.5F, 0.5F, new CubeDeformation(0.0F))
		.texOffs(4, 26).addBox(-8.75F, -6.25F, 7.5F, 0.5F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition spike_bottom_left = chakram.addOrReplaceChild("spike_bottom_left", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition spike_r1 = spike_bottom_left.addOrReplaceChild("spike_r1", CubeListBuilder.create().texOffs(8, 26).addBox(-0.75F, -0.5F, -6.75F, 0.5F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 18).addBox(-0.75F, -0.25F, -7.25F, 0.5F, 0.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 8.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition spike_right = chakram.addOrReplaceChild("spike_right", CubeListBuilder.create().texOffs(8, 18).addBox(-8.75F, -0.25F, 12.25F, 0.5F, 0.5F, 2.5F, new CubeDeformation(0.0F))
		.texOffs(26, 11).addBox(-8.75F, -0.5F, 13.25F, 0.5F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition spike_left = chakram.addOrReplaceChild("spike_left", CubeListBuilder.create().texOffs(0, 22).addBox(-8.75F, -0.25F, 1.25F, 0.5F, 0.5F, 2.5F, new CubeDeformation(0.0F))
		.texOffs(12, 26).addBox(-8.75F, -0.5F, 1.75F, 0.5F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition spike_top_left = chakram.addOrReplaceChild("spike_top_left", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition spike_r2 = spike_top_left.addOrReplaceChild("spike_r2", CubeListBuilder.create().texOffs(22, 7).addBox(-0.75F, -0.25F, -7.25F, 0.5F, 0.5F, 2.5F, new CubeDeformation(0.0F))
		.texOffs(26, 13).addBox(-0.75F, -0.5F, -6.75F, 0.5F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 8.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition spike_top_right = chakram.addOrReplaceChild("spike_top_right", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition spike_r3 = spike_top_right.addOrReplaceChild("spike_r3", CubeListBuilder.create().texOffs(26, 15).addBox(-0.75F, -0.5F, 5.75F, 0.5F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 22).addBox(-0.75F, -0.25F, 4.75F, 0.5F, 0.5F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 8.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition spike_bottom_right = chakram.addOrReplaceChild("spike_bottom_right", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition spike_r4 = spike_bottom_right.addOrReplaceChild("spike_r4", CubeListBuilder.create().texOffs(26, 25).addBox(-0.75F, 5.75F, -0.5F, 0.5F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(26, 21).addBox(-0.75F, 4.75F, -0.25F, 0.5F, 2.5F, 0.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 8.0F, 0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		chakram.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}