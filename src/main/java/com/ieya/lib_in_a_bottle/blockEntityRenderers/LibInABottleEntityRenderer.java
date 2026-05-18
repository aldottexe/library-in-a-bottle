package com.ieya.lib_in_a_bottle.blockEntityRenderers;

import com.ieya.lib_in_a_bottle.LibInABottleServer;
import com.ieya.lib_in_a_bottle.blockEntities.LibInABottleEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.data.ModelData;

public class LibInABottleEntityRenderer implements BlockEntityRenderer<LibInABottleEntity> {

    private static final float FLOAT_HEIGHT = 0.25f; // blocks to rise
    private static final float SPIN_SPEED = 60f;     // degrees per second

    private ModelBlockRenderer blockRenderer;

    public LibInABottleEntityRenderer(BlockEntityRendererProvider.Context context){
        this.blockRenderer = context.getBlockRenderDispatcher().getModelRenderer();
    }

    @Override
    public void render(LibInABottleEntity libInABottleEntity, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int combinedLight, int combinedOverlay) {
        Minecraft mc = Minecraft.getInstance();


        BakedModel model = mc.getModelManager().getModel(
                ModelResourceLocation.standalone(
                        ResourceLocation.fromNamespaceAndPath(LibInABottleServer.MODID, "block/lib_in_a_bottle_model")
                )
        );

        poseStack.pushPose();
        poseStack.translate(0, 1f / 16f, 0);

        blockRenderer.renderModel(
                poseStack.last(),
                multiBufferSource.getBuffer(RenderType.cutout()),
                null,
                model,
                1f, 1f, 1f,
                combinedLight,
                combinedLight,
                ModelData.EMPTY,
                RenderType.cutout()
        );

        poseStack.popPose()
        ;
    }
}
