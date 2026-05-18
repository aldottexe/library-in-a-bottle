package com.ieya.lib_in_a_bottle.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

import static com.ieya.lib_in_a_bottle.LibInABottleServer.BLOCKS;

public class ModBlocks {
    public static void init() {}

    public static final DeferredBlock<Block> LIB_BOTTLE = BLOCKS.register("library_in_a_bottle", () -> new LibInABottleBlock(
            BlockBehaviour.Properties.of().strength(.4f).noOcclusion()
    ));
}