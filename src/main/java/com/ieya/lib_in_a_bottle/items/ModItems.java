package com.ieya.lib_in_a_bottle.items;

import com.ieya.lib_in_a_bottle.blocks.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.neoforged.neoforge.registries.DeferredItem;

import static com.ieya.lib_in_a_bottle.LibInABottleServer.ITEMS;

public class ModItems {
    public static void init() {}

    public static final DeferredItem<BlockItem> LIB_BOTTLE_ITEM = ITEMS.registerSimpleBlockItem("library_in_a_bottle", ModBlocks.LIB_BOTTLE);
}