package net.voidkin.voidkin.entity.model;

import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.animations.ModAnimationDefinitions;
import net.voidkin.voidkin.entity.custom.MantaRayEntity;
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

public class Manta_Ray_Model<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "manta_ray_model"), "main");
	private final ModelPart Manta_Ray;
	private final ModelPart Body;

	public Manta_Ray_Model(ModelPart root) {
		this.Manta_Ray = root.getChild("Manta_Ray");
		this.Body = Manta_Ray.getChild("main_body").getChild("bone2");
	}


	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Manta_Ray = partdefinition.addOrReplaceChild("Manta_Ray", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 18.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition Main_body = Manta_Ray.addOrReplaceChild("main_body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bone2 = Main_body.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 0).addBox(-14.0F, -13.0F, -21.0F, 27.0F, 6.0F, 35.0F, new CubeDeformation(0.0F))
		.texOffs(109, 126).addBox(5.0F, -11.0F, 14.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(109, 126).addBox(-11.0F, -11.0F, 14.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 0.0F));

		PartDefinition wing_r = bone2.addOrReplaceChild("wing_r", CubeListBuilder.create().texOffs(0, 42).addBox(1.0F, -3.0F, -17.0F, 24.0F, 6.0F, 31.0F, new CubeDeformation(0.0F)), PartPose.offset(12.0F, -9.0F, -2.0F));

		PartDefinition wing_tip_r = wing_r.addOrReplaceChild("wing_tip_r", CubeListBuilder.create().texOffs(80, 80).addBox(0.0F, -1.0F, -14.0F, 18.0F, 2.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offset(25.0F, 0.0F, 0.0F));

		PartDefinition wing_l = bone2.addOrReplaceChild("wing_l", CubeListBuilder.create().texOffs(0, 80).addBox(-21.2504F, -3.694F, -17.0F, 24.0F, 6.0F, 31.0F, new CubeDeformation(0.0F)), PartPose.offset(-15.0F, -9.0F, -2.0F));

		PartDefinition wing_tip_l = wing_l.addOrReplaceChild("wing_tip_l", CubeListBuilder.create().texOffs(80, 42).addBox(-18.0F, -1.0F, -14.0F, 18.0F, 2.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offset(-21.2504F, -0.694F, 0.0F));

		PartDefinition waist = bone2.addOrReplaceChild("waist", CubeListBuilder.create().texOffs(0, 118).addBox(-9.0F, -2.0F, -8.0F, 19.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, -21.0F));

		PartDefinition tail = waist.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(101, 11).addBox(-5.0F, -1.0F, -10.0F, 10.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -7.0F));

		PartDefinition tail_tip = tail.addOrReplaceChild("tail_tip", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.0F, -13.0F, 5.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -9.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(ModAnimationDefinitions.MANTA_RAY_MODEL_SWIM, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((MantaRayEntity) entity).idleAnimationState, ModAnimationDefinitions.MANTA_RAY_MODEL_SWIM, ageInTicks, 1f);
		//this.animate(((MantaRayEntity) entity).attackAnimationState, ModAnimationDefinitions.CACTUS_BUDDY_ATTACK, ageInTicks, 1f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		//this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		//this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}
	@Override
	public ModelPart root() {
		return Body;
	}
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		Manta_Ray.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}