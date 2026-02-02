package net.voidkin.voidkin.util;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.voidkin.voidkin.Voidkin;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.Map;
import java.util.stream.Stream;

public record ModWoodTypes(String name, BlockSetType setType, SoundType soundType, SoundType hangingSignSoundType, SoundEvent fenceGateClose, SoundEvent fenceGateOpen
) {
private static final Map<String, WoodType> TYPES = new Object2ObjectArrayMap<>();
public static final Codec<WoodType> CODEC = Codec.stringResolver(WoodType::name, TYPES::get);
    public static final WoodType DARK = register(new WoodType(Voidkin.MODID + ":dark", BlockSetType.DARK_OAK));
    public static final WoodType BLOOD = register(new WoodType(Voidkin.MODID + ":blood", BlockSetType.DARK_OAK));
    public static final WoodType VOID = register(new WoodType(Voidkin.MODID + ":void", BlockSetType.DARK_OAK));

    public ModWoodTypes(String p_273766_, BlockSetType p_273104_) {
        this(p_273766_, p_273104_, SoundType.WOOD, SoundType.HANGING_SIGN, SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN);
    }

    public static WoodType register(WoodType woodType) {
        TYPES.put(woodType.name(), woodType);
        return woodType;
    }
    public static Stream<WoodType> values() {
        return TYPES.values().stream();
    }
}