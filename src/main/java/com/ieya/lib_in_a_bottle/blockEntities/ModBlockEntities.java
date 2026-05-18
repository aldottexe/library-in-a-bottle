package com.ieya.lib_in_a_bottle.blockEntities;

import com.ieya.lib_in_a_bottle.blocks.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

import static com.ieya.lib_in_a_bottle.LibInABottleServer.BLOCK_ENTITIES_TYPES;

public class ModBlockEntities {
    public static void init() {}

    public static final Supplier<BlockEntityType<LibInABottleEntity>> LIB_IN_A_BOTTLE_ENTITY = BLOCK_ENTITIES_TYPES.register(
            "library_in_a_bottle",
            // The block entity type.
            () -> BlockEntityType.Builder.of(
                    LibInABottleEntity::new,
                    ModBlocks.LIB_BOTTLE.get()
            ).build(null)
    );
}