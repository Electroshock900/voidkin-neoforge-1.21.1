package net.voidkin.voidkin.entity.model;// Made with Blockbench 5.0.7
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
import net.minecraft.world.entity.Entity;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.animations.ModAnimationDefinitions;
import net.voidkin.voidkin.entity.custom.HornedOwlEntity;
import net.voidkin.voidkin.entity.custom.OwlEntity;

public class HornedOwlModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "horned_owl_model"), "main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart face_layer;
	private final ModelPart mouth;
	private final ModelPart bone2;
	private final ModelPart wing_l;
	private final ModelPart flap_l;
	private final ModelPart wing_r;
	private final ModelPart flap_r;
	private final ModelPart leg_r;
	private final ModelPart foot_r;
	private final ModelPart leg_l;
	private final ModelPart foot_l;

	public HornedOwlModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.face_layer = this.head.getChild("face_layer");
		this.mouth = this.head.getChild("mouth");
		this.bone2 = this.mouth.getChild("bone2");
		this.wing_l = this.body.getChild("wing_l");
		this.flap_l = this.wing_l.getChild("flap_l");
		this.wing_r = this.body.getChild("wing_r");
		this.flap_r = this.wing_r.getChild("flap_r");
		this.leg_r = this.body.getChild("leg_r");
		this.foot_r = this.leg_r.getChild("foot_r");
		this.leg_l = this.body.getChild("leg_l");
		this.foot_l = this.leg_l.getChild("foot_l");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -8.0F, -4.5F, 10.0F, 11.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(36, 28).addBox(-3.5F, 3.0F, 2.5F, 6.0F, 0.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(42, 41).addBox(-5.5F, -8.0F, -5.5F, 10.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 18.0F, 1.5F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 18).addBox(-5.5F, -7.0F, -4.0F, 10.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(36, 13).addBox(1.5F, -9.0F, -5.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(36, 13).addBox(-7.5F, -9.0F, -5.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(27, 0).addBox(-6.5F, -7.0F, -5.0F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(36, 11).addBox(-3.5F, -6.0F, -5.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 3).addBox(-1.5F, -5.0F, -5.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, -0.5F));

		PartDefinition face_layer = head.addOrReplaceChild("face_layer", CubeListBuilder.create().texOffs(0, 43).addBox(-5.5F, -7.0F, -4.0F, 10.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -1.0F, -2.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -2.5F));

		PartDefinition bone2 = mouth.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 18).addBox(-1.5F, 0.5F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, -1.5F));

		PartDefinition wing_l = body.addOrReplaceChild("wing_l", CubeListBuilder.create().texOffs(35, 3).addBox(0.0F, -1.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, -7.0F, -2.5F));

		PartDefinition flap_l = wing_l.addOrReplaceChild("flap_l", CubeListBuilder.create().texOffs(27, 11).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 3.0F, 0.0F));

		PartDefinition wing_r = body.addOrReplaceChild("wing_r", CubeListBuilder.create().texOffs(20, 35).addBox(-1.0F, -1.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.5F, -7.0F, -2.5F));

		PartDefinition flap_r = wing_r.addOrReplaceChild("flap_r", CubeListBuilder.create().texOffs(27, 25).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 3.0F, 0.0F));

		PartDefinition leg_r = body.addOrReplaceChild("leg_r", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, -1.2F, -1.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 3.0F, -0.5F));

		PartDefinition foot_r = leg_r.addOrReplaceChild("foot_r", CubeListBuilder.create().texOffs(22, 2).addBox(-2.5F, 0.0F, -3.0F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 3.0F, 0.0F));

		PartDefinition leg_l = body.addOrReplaceChild("leg_l", CubeListBuilder.create().texOffs(12, 32).addBox(-1.5F, -1.2F, -1.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 3.0F, -0.5F));

		PartDefinition foot_l = leg_l.addOrReplaceChild("foot_l", CubeListBuilder.create().texOffs(28, 2).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

        this.animateWalk(ModAnimationDefinitions.OWL_FLY, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(((HornedOwlEntity) entity).idleAnimationState, ModAnimationDefinitions.OWL_IDLE, ageInTicks, 1f);
    }
    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, int pColor) {
        body.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pColor);
	}

    @Override
    public ModelPart root() {
        return body;
    }

}