package com.ieya.lib_in_a_bottle.blockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LibInABottleEntity extends BlockEntity {

        // server
        private boolean playerNearby = false;

        // client
        private float floatProgress = 0f;
        private static final float LERP_SPEED = 0.05f;
        private static final double ACTIVATION_RADIUS = 5.0f;


        public LibInABottleEntity(BlockPos pos, BlockState state) {
            super(ModBlockEntities.LIB_IN_A_BOTTLE_ENTITY.get(), pos, state);
        }

        public static void tick(Level level, BlockPos pos, BlockState state, LibInABottleEntity be) {
            if (level.isClientSide) {
                // Client owns the lerp
                float target = be.playerNearby ? 1f : 0f;
                be.floatProgress += (target - be.floatProgress) * LERP_SPEED;
                return;
            }

            // Server detects players and syncs the boolean only when it flips
            boolean nearby = level.players().stream().anyMatch(p ->
                    p.distanceToSqr(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5)
                            < ACTIVATION_RADIUS * ACTIVATION_RADIUS
            );

            if (nearby != be.playerNearby) {
                be.playerNearby = nearby;
                level.sendBlockUpdated(pos, state, state, 3);
            }
        }
}
