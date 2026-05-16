package com.ieya.lib_in_a_bottle.blocks;

import com.ieya.lib_in_a_bottle.LibInABottle;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

import static com.ieya.lib_in_a_bottle.LibInABottle.BLOCKS;

public class ModBlocks {
    public static void init() {}

    public static final DeferredBlock<Block> LIB_BOTTLE = BLOCKS.registerSimpleBlock("library_in_a_bottle", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
}