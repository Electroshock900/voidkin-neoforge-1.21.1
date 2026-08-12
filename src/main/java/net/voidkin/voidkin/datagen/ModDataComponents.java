package net.voidkin.voidkin.datagen;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.voidkin.voidkin.Voidkin;
import net.voidkin.voidkin.item.component.BackpackContent;
import net.voidkin.voidkin.item.component.QuiverContent;
import net.voidkin.voidkin.item.component.VoidGobletContext;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

import static net.minecraft.core.component.DataComponentType.builder;

public interface ModDataComponents {
    DeferredRegister<DataComponentType<?>> REGISTRY = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, Voidkin.MOD_ID);

    Supplier<DataComponentType<BackpackContent>> BACKPACK_CONTENT = REGISTRY.register("backpack_content",
            () -> DataComponentType.<BackpackContent>builder()
                    .persistent(BackpackContent.CODEC)
                    .networkSynchronized(BackpackContent.STREAM_CODEC)
                    .build()
            );
    Supplier<DataComponentType<QuiverContent>> QUIVER_CONTENT = REGISTRY.register("quiver_content",
            () -> DataComponentType.<QuiverContent>builder()
                    .persistent(QuiverContent.CODEC)
                    .networkSynchronized(QuiverContent.STREAM_CODEC)
                    .build()
            );
    //public static final DataComponentType<VoidGobletContext> POTION_CONTENTS =
    //        REGISTRY.register("void_goblet_contents", () -> DataComponentType.<VoidGobletContext>builder().persistent(VoidGobletContext.CODEC).networkSynchronized(VoidGobletContext.STREAM_CODEC).cacheEncoding());


}
