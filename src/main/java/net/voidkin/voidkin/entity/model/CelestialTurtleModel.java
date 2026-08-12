package net.voidkin.voidkin.entity.model;// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.animations.ModAnimationDefinitions;
import net.voidkin.voidkin.entity.custom.CelestialTurtle;
import net.voidkin.voidkin.entity.custom.MantaRayEntity;

public class CelestialTurtleModel extends HierarchicalModel<CelestialTurtle> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "celestialturtlemodel"), "main");
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart egg_belly;
	private final ModelPart right_arm;
	private final ModelPart left_arm;
	private final ModelPart right_leg;
	private final ModelPart left_leg;

	public CelestialTurtleModel(ModelPart root) {
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.egg_belly = root.getChild("egg_belly");
		this.right_arm = root.getChild("right_arm");
		this.left_arm = root.getChild("left_arm");
		this.right_leg = root.getChild("right_leg");
		this.left_leg = root.getChild("left_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(125, 53).addBox(-7.0F, -5.0F, -15.0F, 14.0F, 10.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, -20.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-21.0F, -10.75F, -20.0F, 42.0F, 12.0F, 40.0F, new CubeDeformation(0.0F))
		.texOffs(0, 53).addBox(-14.0F, 1.25F, -20.0F, 28.0F, 7.0F, 34.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.75F, 0.0F));

		PartDefinition egg_belly = partdefinition.addOrReplaceChild("egg_belly", CubeListBuilder.create().texOffs(0, 95).addBox(-12.0F, -2.0F, -20.0F, 24.0F, 2.0F, 34.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 26.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(109, 95).addBox(-28.0F, -1.0F, -5.0F, 28.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-14.0F, 20.0F, -15.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(109, 111).addBox(0.0F, -1.0F, -5.0F, 28.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(14.0F, 20.0F, -15.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(109, 127).addBox(-4.0F, -1.0F, 0.0F, 9.0F, 3.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 20.0F, 14.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 132).addBox(-5.0F, -1.0F, 0.0F, 9.0F, 3.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 20.0F, 14.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(CelestialTurtle entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        //this.animateWalk(ModAnimationDefinitions.MANTA_RAY_MODEL_SWIM, limbSwing, limbSwingAmount, 2f, 2.5f);
        //this.animate(((CelestialTurtle) entity).idleAnimationState, ModAnimationDefinitions.MANTA_RAY_MODEL_SWIM, ageInTicks, 1f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		egg_belly.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

    @Override
    public ModelPart root() {
        return body;
    }

}