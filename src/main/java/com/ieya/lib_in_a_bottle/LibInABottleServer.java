package com.ieya.lib_in_a_bottle;

import com.ieya.lib_in_a_bottle.blockEntities.ModBlockEntities;
import com.ieya.lib_in_a_bottle.events.ModEvents;
import com.ieya.lib_in_a_bottle.mobEffects.ModMobEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.slf4j.Logger;

import com.ieya.lib_in_a_bottle.blocks.ModBlocks;
import com.ieya.lib_in_a_bottle.items.ModItems;
import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(LibInABottleServer.MODID)
public class LibInABottleServer {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "lib_in_a_bottle";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MODID);

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, MODID);


    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public LibInABottleServer(IEventBus modEventBus, ModContainer modContainer) {

        ModItems.init();
        ModBlocks.init();
        ModMobEffects.init();
        ModBlockEntities.init();


        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITIES_TYPES.register(modEventBus);
        MOB_EFFECTS.register(modEventBus);

        modEventBus.addListener(this::addCreative);

        NeoForge.EVENT_BUS.addListener(ModEvents::onTableOpened);
        NeoForge.EVENT_BUS.addListener(ModEvents::onEnchantLevelSet);
    }


    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModItems.LIB_BOTTLE_ITEM);
        }
    }
}
