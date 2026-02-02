package net.voidkin.voidkin.entity.model;// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.animation.WarTortoiseHybridAnimation;// Made with Blockbench 4.11.2
import net.voidkin.voidkin.entity.custom.WarTortoiseHybrid;

public class War_Tortoise_Hybrid_Model<T extends WarTortoiseHybrid> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "war_tortoise_hybrid"), "main");
	public static final ModelLayerLocation ARMOR_LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "war_tortoise_hybrid_armor"), "main");
	private final ModelPart tortoise_hybrid;
	private final ModelPart shell;
	private final ModelPart head;
	private final ModelPart right_back;
	private final ModelPart left_back;
	private final ModelPart right_front;
	private final ModelPart left_front;
	private final ModelPart left_front_fin;
	private final ModelPart right_front_fin;
	private final ModelPart left_back_fin1;
	private final ModelPart left_back_fin2;
	private final ModelPart right_back_fin1;
	private final ModelPart right_back_fin2;
	private final ModelPart tail_base;
	private final ModelPart tail_tip;
	private final ModelPart accessories;
	private final ModelPart left_cannon;
	private final ModelPart right_cannon;

	public War_Tortoise_Hybrid_Model(ModelPart root) {
		this.tortoise_hybrid = root.getChild("tortoise_hybrid");
		this.shell = this.tortoise_hybrid.getChild("shell");
		this.head = this.shell.getChild("head");
		this.right_back = this.shell.getChild("right_back");
		this.left_back = this.shell.getChild("left_back");
		this.right_front = this.shell.getChild("right_front");
		this.left_front = this.shell.getChild("left_front");
		this.left_front_fin = this.shell.getChild("left_front_fin");
		this.right_front_fin = this.shell.getChild("right_front_fin");
		this.left_back_fin1 = this.shell.getChild("left_back_fin1");
		this.left_back_fin2 = this.left_back_fin1.getChild("left_back_fin2");
		this.right_back_fin1 = this.shell.getChild("right_back_fin1");
		this.right_back_fin2 = this.right_back_fin1.getChild("right_back_fin2");
		this.tail_base = this.shell.getChild("tail_base");
		this.tail_tip = this.tail_base.getChild("tail_tip");
		this.accessories = this.shell.getChild("accessories");
		this.left_cannon = this.accessories.getChild("left_cannon");
		this.right_cannon = this.accessories.getChild("right_cannon");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition tortoise_hybrid = partdefinition.addOrReplaceChild("tortoise_hybrid", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition shell = tortoise_hybrid.addOrReplaceChild("shell", CubeListBuilder.create().texOffs(0, 49).addBox(-4.0F, -2.75F, -6.75F, 7.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(43, 34).addBox(-5.0F, -6.75F, -1.0F, 9.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 34).addBox(-6.0F, -5.75F, -3.0F, 11.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(62, 0).addBox(-9.0F, -1.75F, -8.0F, 17.0F, 0.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-7.0F, -4.75F, -6.0F, 13.0F, 6.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(0, 19).addBox(-6.0F, -0.75F, -5.0F, 11.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

		PartDefinition head = shell.addOrReplaceChild("head", CubeListBuilder.create().texOffs(49, 19).addBox(-3.0F, -3.5F, -8.25F, 6.0F, 4.0F, 6.0F, new CubeDeformation(-0.1F))
		.texOffs(36, 25).addBox(-2.0F, -3.75F, -2.75F, 4.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(-0.5F, 2.0F, -5.0F));

		PartDefinition right_back = shell.addOrReplaceChild("right_back", CubeListBuilder.create().texOffs(38, 63).addBox(-2.0F, 0.0F, -1.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.5F)), PartPose.offset(-6.0F, 0.0F, 7.0F));

		PartDefinition left_back = shell.addOrReplaceChild("left_back", CubeListBuilder.create().texOffs(51, 63).addBox(-1.5F, -0.5F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.5F)), PartPose.offset(5.5F, 0.5F, 7.5F));

		PartDefinition right_front = shell.addOrReplaceChild("right_front", CubeListBuilder.create().texOffs(38, 54).addBox(-1.0F, -0.5F, -4.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.5F)), PartPose.offset(-7.0F, 0.5F, -3.0F));

		PartDefinition left_front = shell.addOrReplaceChild("left_front", CubeListBuilder.create().texOffs(51, 54).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.5F)), PartPose.offset(6.0F, 1.0F, -6.0F));

		PartDefinition left_front_fin = shell.addOrReplaceChild("left_front_fin", CubeListBuilder.create().texOffs(43, 46).addBox(-4.3827F, -1.0F, -2.0761F, 10.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, 1.0F, -4.0F, 0.0F, -1.1781F, 0.0F));

		PartDefinition right_front_fin = shell.addOrReplaceChild("right_front_fin", CubeListBuilder.create().texOffs(43, 46).mirror().addBox(-5.6173F, -1.0F, -2.0761F, 10.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-9.0F, 1.0F, -4.0F, 0.0F, 1.1781F, 0.0F));

		PartDefinition left_back_fin1 = shell.addOrReplaceChild("left_back_fin1", CubeListBuilder.create().texOffs(19, 49).mirror().addBox(-1.0F, -0.5F, -1.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, 0.5F, 7.0F));

		PartDefinition left_back_fin2 = left_back_fin1.addOrReplaceChild("left_back_fin2", CubeListBuilder.create().texOffs(0, 56).mirror().addBox(-1.0F, -1.0F, 0.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 0.5F, 3.0F));

		PartDefinition right_back_fin1 = shell.addOrReplaceChild("right_back_fin1", CubeListBuilder.create().texOffs(19, 49).addBox(-4.0F, -0.5F, -1.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 0.5F, 7.0F));

		PartDefinition right_back_fin2 = right_back_fin1.addOrReplaceChild("right_back_fin2", CubeListBuilder.create().texOffs(0, 56).addBox(-2.0F, -1.0F, 0.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 0.5F, 3.0F));

		PartDefinition tail_base = shell.addOrReplaceChild("tail_base", CubeListBuilder.create().texOffs(57, 20).addBox(-1.0F, -0.5F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offset(-0.5F, -0.25F, 8.0F));

		PartDefinition tail_tip = tail_base.addOrReplaceChild("tail_tip", CubeListBuilder.create().texOffs(34, 49).addBox(-1.0F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.0F, 2.0F));

		PartDefinition accessories = shell.addOrReplaceChild("accessories", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, -5.0F));

		PartDefinition cube_r1 = accessories.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(15, 56).addBox(-3.0F, -1.0F, -3.0F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.6F, -1.4F, -0.6981F, 0.0F, 0.0F));

		PartDefinition cube_r2 = accessories.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(47, 30).addBox(-4.0F, -1.0F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.25F, -1.1781F, 0.0F, 0.0F));

		PartDefinition left_cannon = accessories.addOrReplaceChild("left_cannon", CubeListBuilder.create().texOffs(15, 61).mirror().addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 63).mirror().addBox(-1.0F, -1.0F, -5.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, 2.0F, -3.0F));

		PartDefinition right_cannon = accessories.addOrReplaceChild("right_cannon", CubeListBuilder.create().texOffs(15, 61).addBox(-1.5F, -1.5F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 63).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 2.0F, -2.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(WarTortoiseHybrid entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(WarTortoiseHybridAnimation.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(entity.idleAnimationState, WarTortoiseHybridAnimation.idle_standing, ageInTicks, 1f);
		this.animate(entity.withdrawAnimationState, WarTortoiseHybridAnimation.retreat_standing, ageInTicks, 1f);
		this.animate(entity.hidingAnimationState, WarTortoiseHybridAnimation.withdrawn_standing, ageInTicks, 1f);
		this.animate(entity.emergeAnimationState, WarTortoiseHybridAnimation.lay_down, ageInTicks, 1f);

		this.animate(entity.idleSwimAnimationState, WarTortoiseHybridAnimation.idle_swimming, ageInTicks, 1f);
		this.animate(entity.withdrawSwimAnimationState, WarTortoiseHybridAnimation.retreat_swimming, ageInTicks, 1f);
		this.animate(entity.hidingSwimAnimationState, WarTortoiseHybridAnimation.withdrawn_swimming, ageInTicks, 1f);
		this.animate(entity.emergeSwimAnimationState, WarTortoiseHybridAnimation.lay_down, ageInTicks, 1f);


		//this.animate(((WarTortoise) entity).attackAnimationState, ModAnimationDefinitions.CACTUS_BUDDY_ATTACK, ageInTicks, 1f);
		this.left_cannon.visible = entity.hasArmorOn();
		this.right_cannon.visible = entity.hasArmorOn();

		this.accessories.visible = entity.isTame();

		this.left_back_fin1.visible = entity.isSwimming();
		this.right_back_fin1.visible = entity.isSwimming();
		this.left_front_fin.visible = entity.isSwimming();
		this.right_front_fin.visible = entity.isSwimming();

		this.left_back.visible = !entity.isSwimming();
		this.right_back.visible = !entity.isSwimming();
		this.left_front.visible = !entity.isSwimming();
		this.right_front.visible = !entity.isSwimming();

		//this.tier3.visible= entity.hasTier3Chest();
		//this.animate(((WarTortoise) entity).attackAnimationState, ModAnimationDefinitions.CACTUS_BUDDY_ATTACK, ageInTicks, 1f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		tortoise_hybrid.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart root() {
		return tortoise_hybrid;
	}

}