package net.voidkin.voidkin.entity.custom.cheesehorse;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.voidkin.voidkin.entity.model.CheeseHorseModel;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractCheeseHorseRenderer<T extends AbstractCheeseHorse, M extends CheeseHorseModel<T>> extends MobRenderer<T, M> {
    private final float scale;

    public AbstractCheeseHorseRenderer(EntityRendererProvider.Context context, M model, float scale) {
        super(context, model, 0.75F);
        this.scale = scale;
    }

    protected void scale(T livingEntity, PoseStack poseStack, float partialTickTime) {
        poseStack.scale(this.scale, this.scale, this.scale);
        super.scale(livingEntity, poseStack, partialTickTime);
    }
}
