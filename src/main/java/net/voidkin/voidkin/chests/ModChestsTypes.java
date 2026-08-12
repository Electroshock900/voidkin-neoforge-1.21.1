package net.voidkin.voidkin.chests;

import net.voidkin.voidkin.Voidkin;

import net.voidkin.voidkin.chests.regular.entity.*;
import net.voidkin.voidkin.chests.trapped.entity.*;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public enum ModChestsTypes implements StringRepresentable {

    IRON(54, 9, 184, 222,  Voidkin.prefix("textures/gui/iron_container.png"), 256, 256),
    GOLD(81, 9, 184, 276,  Voidkin.prefix("textures/gui/gold_container.png"), 256, 276),
    DIAMOND(108, 12, 238, 276,  Voidkin.prefix("textures/gui/diamond_container.png"), 256, 276),
    COPPER(45, 9, 184, 204,  Voidkin.prefix("textures/gui/copper_container.png"), 256, 256),
    CRYSTAL(108, 12, 238, 276,  Voidkin.prefix("textures/gui/diamond_container.png"), 256, 276),
    OBSIDIAN(108, 12, 238, 276,  Voidkin.prefix("textures/gui/diamond_container.png"), 256, 276),
    DIRT(1, 1, 184, 184,  Voidkin.prefix("textures/gui/dirt_container.png"), 256, 256),
    WOOD(0, 0, 0, 0, null, 0, 0);

    private final String name;
    public final int size;
    public final int rowLength;
    public final int xSize;
    public final int ySize;
    public final ResourceLocation guiTexture;
    public final int textureXSize;
    public final int textureYSize;

    ModChestsTypes(int size, int rowLength, int xSize, int ySize, ResourceLocation guiTexture, int textureXSize, int textureYSize) {
        this(null, size, rowLength, xSize, ySize, guiTexture, textureXSize, textureYSize);
    }

    ModChestsTypes(@Nullable String name, int size, int rowLength, int xSize, int ySize, ResourceLocation guiTexture, int textureXSize, int textureYSize) {
        this.name = name == null ? Voidkin.toEnglishName(this.name()) : name;
        this.size = size;
        this.rowLength = rowLength;
        this.xSize = xSize;
        this.ySize = ySize;
        this.guiTexture = guiTexture;
        this.textureXSize = textureXSize;
        this.textureYSize = textureYSize;
    }

    public String getId() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    public String getEnglishName() {
        return this.name;
    }

    @Override
    public String getSerializedName() {
        return this.getEnglishName();
    }

    public int getRowCount() {
        return this.size / this.rowLength;
    }

    public boolean isTransparent() {
        return this == CRYSTAL;
    }

    public static List<Block> get(ModChestsTypes type) {
        return switch (type) {
            case IRON -> Arrays.asList( ModChestsBlocks.IRON_CHEST.get(),  ModChestsBlocks.TRAPPED_IRON_CHEST.get());
            /**
            case GOLD -> Arrays.asList( ModChestsBlocks.GOLD_CHEST.get(),  ModChestsBlocks.TRAPPED_GOLD_CHEST.get());
            case DIAMOND -> Arrays.asList( ModChestsBlocks.DIAMOND_CHEST.get(),  ModChestsBlocks.TRAPPED_DIAMOND_CHEST.get());
            case COPPER -> Arrays.asList( ModChestsBlocks.COPPER_CHEST.get(),  ModChestsBlocks.TRAPPED_COPPER_CHEST.get());
            case CRYSTAL -> Arrays.asList( ModChestsBlocks.CRYSTAL_CHEST.get(),  ModChestsBlocks.TRAPPED_CRYSTAL_CHEST.get());
            **/
            case OBSIDIAN -> Arrays.asList( ModChestsBlocks.OBSIDIAN_CHEST.get(),  ModChestsBlocks.TRAPPED_OBSIDIAN_CHEST.get());
            //case DIRT -> Arrays.asList( ModChestsBlocks.DIRT_CHEST.get(),  ModChestsBlocks.TRAPPED_DIRT_CHEST.get());
            default -> List.of(Blocks.CHEST);
        };
    }

    @Nullable
    public AbstractModChestBlockEntity makeEntity(BlockPos blockPos, BlockState blockState, boolean trapped) {
        if (trapped) {
            return switch (this) {
                case IRON -> new TrappedIronChestBlockEntity(blockPos, blockState);
                /**case GOLD -> new TrappedGoldChestBlockEntity(blockPos, blockState);
                case DIAMOND -> new TrappedDiamondChestBlockEntity(blockPos, blockState);
                case COPPER -> new TrappedCopperChestBlockEntity(blockPos, blockState);
                case CRYSTAL -> new TrappedCrystalChestBlockEntity(blockPos, blockState);**/
                case OBSIDIAN -> new TrappedObsidianChestBlockEntity(blockPos, blockState);
                //case DIRT -> new TrappedDirtChestBlockEntity(blockPos, blockState);
                default -> null;
            };
        } else {
            return switch (this) {
                case IRON -> new IronChestBlockEntity(blockPos, blockState);
                /**case GOLD -> new GoldChestBlockEntity(blockPos, blockState);
                case DIAMOND -> new DiamondChestBlockEntity(blockPos, blockState);
                case COPPER -> new CopperChestBlockEntity(blockPos, blockState);
                case CRYSTAL -> new CrystalChestBlockEntity(blockPos, blockState);**/
                case OBSIDIAN -> new ObsidianChestBlockEntity(blockPos, blockState);
                //case DIRT -> new DirtChestBlockEntity(blockPos, blockState);
                default -> null;
            };
        }
    }
}