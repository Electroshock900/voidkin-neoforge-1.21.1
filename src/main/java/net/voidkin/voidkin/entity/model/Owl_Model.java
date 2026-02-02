package net.voidkin.voidkin.entity.model;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.animations.ModAnimationDefinitions;
import net.voidkin.voidkin.entity.custom.OwlEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class Owl_Model<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "owl_model"), "main");
	private final ModelPart Body;
	private final ModelPart head;

	public Owl_Model(ModelPart root) {
		this.Body = root.getChild("Body");
		this.head = Body.getChild("Head");

	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -5.0F, -3.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(43, 4).addBox(-3.0F, -5.0F, -3.5F, 6.0F, 3.0F, 0.5F, new CubeDeformation(0.0F))
		.texOffs(43, 7).addBox(-2.0F, -2.0F, -3.25F, 4.0F, 1.0F, 0.25F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 2.0F));

		PartDefinition Left_Wing = Body.addOrReplaceChild("Left_Wing", CubeListBuilder.create().texOffs(25, 0).addBox(0.0F, -1.25F, -2.0F, 1.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -2.0F, 0.0F));

		PartDefinition Right_Wing = Body.addOrReplaceChild("Right_Wing", CubeListBuilder.create().texOffs(21, 23).addBox(-1.0F, -1.0F, -2.0F, 1.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -2.25F, 0.0F));

		PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 15).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(35, 0).addBox(-3.75F, 0.0F, -3.75F, 7.5F, 2.0F, 7.5F, new CubeDeformation(0.0F))
		.texOffs(19, 0).addBox(-1.0F, -2.0F, -4.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

		PartDefinition bone = Head.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(5, 20).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(5, 20).addBox(-4.0F, -2.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(5, 20).addBox(1.0F, -2.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -3.0F));

		PartDefinition Right_Leg = Body.addOrReplaceChild("Right_Leg", CubeListBuilder.create().texOffs(11, 27).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 3.0F, 0.0F));

		PartDefinition Right_Foot = Right_Leg.addOrReplaceChild("Right_Foot", CubeListBuilder.create().texOffs(0, 27).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 0.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition Left_Leg = Body.addOrReplaceChild("Left_Leg", CubeListBuilder.create().texOffs(27, 12).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 3.0F, 0.0F));

		PartDefinition Left_Foot = Left_Leg.addOrReplaceChild("Left_Foot", CubeListBuilder.create().texOffs(19, 15).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 0.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}


	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(ModAnimationDefinitions.OWL_FLY, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((OwlEntity) entity).idleAnimationState, ModAnimationDefinitions.OWL_IDLE, ageInTicks, 1f);
		//this.animate(((MantaRayEntity) entity).attackAnimationState, ModAnimationDefinitions.CACTUS_BUDDY_ATTACK, ageInTicks, 1f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}
	@Override
	public ModelPart root() {
		return Body;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}