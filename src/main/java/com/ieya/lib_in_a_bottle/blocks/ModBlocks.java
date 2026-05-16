package com.ieya.lib_in_a_bottle.blocks;

import com.ieya.lib_in_a_bottle.LibInABottle;

public class ModBlocks {
    public static final init() {}

    public static final DeferredBlock<Block> LIB_BOTTLE = BLOCKS.registerSimpleBlock("library_in_a_bottle", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
}