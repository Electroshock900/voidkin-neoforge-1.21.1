package net.voidkin.voidkin.menu;

import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.voidkin.voidkin.Voidkin;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.voidkin.voidkin.menu.screens.custom.WarTortoiseHybridMenu;
import net.voidkin.voidkin.menu.screens.custom.WarTortoiseMenu;
import net.voidkin.voidkin.menu.screens.custom.WarTurtleMenu;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, Voidkin.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<PolisherMenu>> POLISHING_MENU =
            registerMenuType("polisher_menu", PolisherMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<CrystallizerMenu>> CRYSTALLIZER_MENU =
            registerMenuType("crystallizer_menu", CrystallizerMenu::new);
    /*public static final DeferredHolder<MenuType<?>, MenuType<DomMenu>> DOM_MENU =
            registerMenuType("dom_menu", DomMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<SubMenu>> SUB_MENU =
            registerMenuType("sub_menu", SubMenu::new);*/
/**
    /**public static final DeferredHolder<MenuType<?>, MenuType<VoidAltarMenu>> VOID_ALTAR_MENU =
            registerMenuType("void_altar_menu", VoidAltarMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<VoidPedestalMenu>> VOID_PEDESTAL_MENU =
            registerMenuType("void_pedestal_menu", VoidPedestalMenu::new);
**/
    public static final DeferredHolder<MenuType<?>, MenuType<PedestalMenu>> PEDESTAL_MENU =
            registerMenuType("pedestal_menu", PedestalMenu::new);


    public static final DeferredHolder<MenuType<?>, MenuType<WarTurtleMenu>> WAR_TURTLE_MENU =
            registerMenuType("war_turtle", WarTurtleMenu::create);
    public static final DeferredHolder<MenuType<?>, MenuType<WarTortoiseMenu>> WAR_TORTOISE_MENU =
            registerMenuType("war_tortoise", WarTortoiseMenu::create);
    public static final DeferredHolder<MenuType<?>, MenuType<WarTortoiseHybridMenu>> WAR_TORTOISE_HYBRID_MENU =
            registerMenuType("war_tortoise_hybrid", WarTortoiseHybridMenu::create);

/*    public static final DeferredHolder<MenuType<?>, MenuType<AltarBMenu>> ALTAR_B_MENU =
            registerMenuType("altar_b_menu", AltarBMenu::new);*/




    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}

