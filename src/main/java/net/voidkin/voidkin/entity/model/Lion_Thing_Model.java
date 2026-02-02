package net.voidkin.voidkin.entity.model;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.animations.ModAnimationDefinitions;
import net.voidkin.voidkin.entity.custom.Lion_Thing;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class Lion_Thing_Model<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "thing"), "main");
	private final ModelPart lion_thing;
	private final ModelPart head;

	public Lion_Thing_Model(ModelPart root) {

		this.lion_thing = root.getChild("lion_thing");
		this.head = lion_thing.getChild("torso").getChild("chest")
				.getChild("neck").getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition lion_thing = partdefinition.addOrReplaceChild("lion_thing", CubeListBuilder.create(), PartPose.offset(0.0F, 19.0F, 0.0F));

		PartDefinition torso = lion_thing.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(22, 7).addBox(-3.0F, -5.0F, -1.0F, 6.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 23).addBox(-3.25F, -2.0F, -1.25F, 6.5F, 2.25F, 4.5F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg_r = torso.addOrReplaceChild("leg_r", CubeListBuilder.create().texOffs(38, 25).addBox(-1.75F, -1.0F, -0.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.25F, 0.0F, 0.0F));

		PartDefinition leg_l = torso.addOrReplaceChild("leg_l", CubeListBuilder.create().texOffs(38, 0).addBox(-1.75F, -1.0F, -0.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.75F, 0.0F, 0.0F));

		PartDefinition chest = torso.addOrReplaceChild("chest", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -6.0F, -2.0F, 8.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

		PartDefinition shoulder_r = chest.addOrReplaceChild("shoulder_r", CubeListBuilder.create().texOffs(0, 30).addBox(-4.5F, -2.5F, -1.5F, 4.5F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -3.0F, 0.0F));

		PartDefinition arm_r = shoulder_r.addOrReplaceChild("arm_r", CubeListBuilder.create().texOffs(15, 37).addBox(-2.0F, -0.5F, -1.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 2.5F, 0.0F));

		PartDefinition shoulder_l = chest.addOrReplaceChild("shoulder_l", CubeListBuilder.create().texOffs(20, 28).addBox(1.0F, -2.5F, -1.5F, 4.5F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -3.0F, 0.0F));

		PartDefinition arm_l = shoulder_l.addOrReplaceChild("arm_l", CubeListBuilder.create().texOffs(35, 34).addBox(-2.0F, -0.5F, -1.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 2.5F, 0.0F));

		PartDefinition neck = chest.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(21, 0).addBox(-2.0F, -1.0F, -1.5F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 0.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(19, 18).addBox(-2.5F, -5.0F, -2.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 11).addBox(-3.5F, -6.0F, -1.0F, 7.0F, 7.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(33, 0).addBox(-1.5F, -2.0F, -3.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition horn_l = head.addOrReplaceChild("horn_l", CubeListBuilder.create().texOffs(0, 11).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -4.0F, -1.5F, 2.3562F, 0.0F, -1.5708F));

		PartDefinition cube_r1 = horn_l.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(14, 30).addBox(-0.35F, -1.5858F, -0.25F, 0.6F, 1.8F, 0.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -2.75F, 0.0F, 0.0F, 0.0F, 1.7017F));

		PartDefinition cube_r2 = horn_l.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 23).addBox(-0.45F, -2.0F, -0.35F, 0.8F, 1.8F, 0.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -1.5F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition horn_r = head.addOrReplaceChild("horn_r", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -4.0F, -1.5F, 0.7854F, 0.0F, -1.5708F));

		PartDefinition cube_r3 = horn_r.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 30).addBox(-0.35F, -1.5858F, -0.25F, 0.6F, 1.8F, 0.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -2.75F, 0.0F, 0.0F, 0.0F, 1.7017F));

		PartDefinition cube_r4 = horn_r.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(21, 0).addBox(-0.45F, -2.0F, -0.35F, 0.8F, 1.8F, 0.7F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -1.5F, 0.0F, 0.0F, 0.0F, 0.7854F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(ModAnimationDefinitions.THING_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((Lion_Thing) entity).idleAnimationState, ModAnimationDefinitions.THING_IDLE, ageInTicks, 1f);
		this.animate(((Lion_Thing) entity).attackAnimationState, ModAnimationDefinitions.THING_ATTACK, ageInTicks, 1f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		lion_thing.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart root() {
		return lion_thing;
	}
}