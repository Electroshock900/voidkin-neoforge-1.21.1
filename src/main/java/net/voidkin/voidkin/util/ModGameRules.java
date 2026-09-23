package net.voidkin.voidkin.util;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlagSet;

import net.minecraft.world.level.GameRules;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.google.common.base.Suppliers;
import net.minecraft.world.level.GameRules;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.network.PacketDistributor;
//import twilightforest.network.EnforceProgressionStatusPacket;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class ModGameRules {
    public static final Set<Supplier<GameRules.Key<?>>> GAME_RULES = new HashSet<>();

    /*public static final Supplier<GameRules.Key<GameRules.BooleanValue>> ENFORCED_PROGRESSION_RULE = register("tfEnforcedProgression",
            GameRules.Category.UPDATES,
            GameRules.BooleanValue.create(true, (server, value) ->
                    // sends a packet to every player online when this changes
                    PacketDistributor.sendToAllPlayers(new EnforceProgressionStatusPacket(value.get()))
            )
    );*/

    public static final Supplier<GameRules.Key<GameRules.IntegerValue>> RULE_PLAYERS_XION_PORTAL_DEFAULT_DELAY = register("playersXionPortalDefaultDelay",
            GameRules.Category.PLAYER,
            GameRules.IntegerValue.create(20)
    );

    public static final Supplier<GameRules.Key<GameRules.IntegerValue>> RULE_PLAYERS_XION_PORTAL_CREATIVE_DELAY = register("playersXionPortalCreativeDelay",
            GameRules.Category.PLAYER,
            GameRules.IntegerValue.create(1)
    );
    public static final Supplier<GameRules.Key<GameRules.IntegerValue>> RULE_PLAYERS_VOID_PORTAL_DEFAULT_DELAY = register("playersVoidPortalDefaultDelay",
            GameRules.Category.PLAYER,
            GameRules.IntegerValue.create(20)
    );

    public static final Supplier<GameRules.Key<GameRules.IntegerValue>> RULE_PLAYERS_VOID_PORTAL_CREATIVE_DELAY = register("playersVoidPortalCreativeDelay",
            GameRules.Category.PLAYER,
            GameRules.IntegerValue.create(1)
    );

    @SuppressWarnings("unchecked")
    private static <T extends GameRules.Value<T>> Supplier<GameRules.Key<T>> register(String name, GameRules.Category category, GameRules.Type<T> type) {
        Supplier<GameRules.Key<T>> supplier = Suppliers.memoize(() -> GameRules.register(name, category, type));
        GAME_RULES.add((Supplier<GameRules.Key<?>>) (Object) supplier);
        return supplier;
    }

    public static void register(FMLCommonSetupEvent event) {
        // Register our gamerules out of parallel-modloading
        event.enqueueWork(() -> GAME_RULES.forEach(Supplier::get));
    }
}
