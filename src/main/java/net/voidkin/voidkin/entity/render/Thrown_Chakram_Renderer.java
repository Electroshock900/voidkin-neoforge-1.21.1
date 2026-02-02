package net.voidkin.voidkin.entity.render;

import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.entity.projectiles.Thrown_Chakram;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class Thrown_Chakram_Renderer extends EntityRenderer<Thrown_Chakram> {//, Thrown_Chakram_Model<Thrown_Chakram>> {
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Voidkin.MODID, "textures/entity/chakram");

    public Thrown_Chakram_Renderer(EntityRendererProvider.Context manager) {
        super(manager);
        //super(new Thrown_Chakram_Model<Thrown_Chakram>(Thrown_Chakram_Model.createBodyLayer().bakeRoot()),0.4F);
    }

    @Override
    public void render(Thrown_Chakram p_114485_, float p_114486_, float p_114487_, PoseStack p_114488_, MultiBufferSource p_114489_, int p_114490_) {
        super.render(p_114485_, p_114486_, p_114487_, p_114488_, p_114489_, p_114490_);
    }

    public ResourceLocation getTextureLocation(Thrown_Chakram arrow) {
        return TEXTURE;
    }
}
