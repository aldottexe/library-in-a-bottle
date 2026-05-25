package com.ieya.lib_in_a_bottle.blockEntities;

import com.ieya.lib_in_a_bottle.mobEffects.EnchantersAffinity;
import com.ieya.lib_in_a_bottle.mobEffects.ModMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import static com.ieya.lib_in_a_bottle.LibInABottleServer.MOB_EFFECTS;
import static com.ieya.lib_in_a_bottle.mobEffects.ModMobEffects.enchantersAffinity;

public class LibInABottleEntity extends BlockEntity {

    // server
    public boolean playerNearby = false;

    // client
    public float floatProgress = 0f;
    private static final float LERP_SPEED = 0.05f;
    private static final double ACTIVATION_RADIUS = 5.0f;

    private static final float SPIN_SPEED = 60f;     // degrees per second
    public float spinAngle = 0f;
    public float previousSpinAngle = 0f;

    private int tickTimer;
    private static final int CHECK_INTERVAL = 20;

    public LibInABottleEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.LIB_IN_A_BOTTLE_ENTITY.get(), pos, state);

        // offset the tick timer based on location so all instances don't run checks at once.
        this.tickTimer = Math.abs(pos.hashCode()) % CHECK_INTERVAL;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, LibInABottleEntity be) {
        // client stuff
        if (level.isClientSide) {
            // floating
            float target = be.playerNearby ? 1f : 0f;
            be.floatProgress += (target - be.floatProgress) * LERP_SPEED;

            // spin
            be.previousSpinAngle = be.spinAngle;
            be.spinAngle = be.spinAngle + SPIN_SPEED / 20f * be.floatProgress;
            be.spinAngle %= 360;

            return;
        }

        // server stuff
        if (++be.tickTimer % CHECK_INTERVAL == 0) {
            // Server detects players and syncs the boolean only when it flips
            boolean nearby = level.players().stream().anyMatch(p ->
                    p.distanceToSqr(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5)
                            < ACTIVATION_RADIUS * ACTIVATION_RADIUS
            );
            level.players().forEach(p -> {
                if (p.distanceToSqr(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) < ACTIVATION_RADIUS * ACTIVATION_RADIUS) {
                    p.addEffect(new MobEffectInstance(enchantersAffinity.getDelegate(), 200, 0, true, true));
                }
            });

            if (nearby != be.playerNearby) {
                be.playerNearby = nearby;
                level.sendBlockUpdated(pos, state, state, 3);
            }
            be.tickTimer = 0;
        }
    }
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("nearby", playerNearby);
        return tag;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt,
                             HolderLookup.Provider registries) {
        CompoundTag tag = pkt.getTag();
        if (tag != null) playerNearby = tag.getBoolean("nearby");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putBoolean("nearby", playerNearby);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        playerNearby = tag.getBoolean("nearby");
    }
}
