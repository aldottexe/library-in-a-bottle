package com.ieya.lib_in_a_bottle.blockEntityRenderers;

import com.ieya.lib_in_a_bottle.LibInABottleServer;
import com.ieya.lib_in_a_bottle.blockEntities.LibInABottleEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.neoforge.client.model.data.ModelData;

public class LibInABottleEntityRenderer implements BlockEntityRenderer<LibInABottleEntity> {

    private static final float FLOAT_HEIGHT = 0.25f; // blocks to rise

    private final ModelBlockRenderer blockRenderer;

    public LibInABottleEntityRenderer(BlockEntityRendererProvider.Context context){
        this.blockRenderer = context.getBlockRenderDispatcher().getModelRenderer();
    }

    @Override
    public void render(LibInABottleEntity libInABottleEntity, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int combinedLight, int combinedOverlay) {
        // if (libInABottleEntity.floatProgress <= 0.001f) return;

        Minecraft mc = Minecraft.getInstance();

        BakedModel innerModel = mc.getModelManager().getModel(
                ModelResourceLocation.standalone(
                        ResourceLocation.fromNamespaceAndPath(LibInABottleServer.MODID, "block/lib_in_a_bottle_model")
                )
        );

        float progress = easeInOut(libInABottleEntity.floatProgress) * FLOAT_HEIGHT;
        float floatAnimation = ((float) Math.sin(System.currentTimeMillis() / 1000.0) * FLOAT_HEIGHT / 2f) * libInABottleEntity.floatProgress;
        float spin = Mth.rotLerp(partialTick, libInABottleEntity.previousSpinAngle, libInABottleEntity.spinAngle);

        poseStack.pushPose();
        poseStack.translate(0, 1f / 16f, 0);

        poseStack.translate(0.5, progress + floatAnimation, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(spin));
        poseStack.translate(-0.5, 0, -0.5);

        blockRenderer.renderModel(
                poseStack.last(),
                multiBufferSource.getBuffer(RenderType.cutout()),
                null,
                innerModel,
                1f, 1f, 1f,
                combinedLight,
                combinedLight,
                ModelData.EMPTY,
                RenderType.cutout()
        );

        poseStack.popPose();
    }

    private float easeInOut(float t) {
        return t * t * (3f - 2f * t);
    }
}
