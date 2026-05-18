package com.ieya.lib_in_a_bottle;

import com.ieya.lib_in_a_bottle.blockEntities.ModBlockEntities;
import com.ieya.lib_in_a_bottle.blockEntityRenderers.LibInABottleEntityRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = LibInABottleServer.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = LibInABottleServer.MODID, value = Dist.CLIENT)
public class LibInABottleClient {
    public LibInABottleClient(ModContainer container) {


        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        // container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        LibInABottleServer.LOGGER.info("HELLO FROM CLIENT SETUP");
        LibInABottleServer.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }

    @SubscribeEvent
    static void registerAdditional(ModelEvent.RegisterAdditional event) {
        event.register(ModelResourceLocation.standalone(
                ResourceLocation.fromNamespaceAndPath("lib_in_a_bottle", "block/lib_in_a_bottle_model")
        ));
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(
                ModBlockEntities.LIB_IN_A_BOTTLE_ENTITY.get(),
                LibInABottleEntityRenderer::new
        );
    }
}
