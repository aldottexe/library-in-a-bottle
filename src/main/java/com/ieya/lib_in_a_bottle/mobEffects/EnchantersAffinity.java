package com.ieya.lib_in_a_bottle.mobEffects;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.client.extensions.common.IClientMobEffectExtensions;

import java.util.function.Consumer;

public class EnchantersAffinity extends MobEffect {
    public EnchantersAffinity(MobEffectCategory category, int color) {
        super(category, color);
    }
    
    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        // Apply your effect logic here.

        // If this returns false when shouldApplyEffectTickThisTick returns true, the effect will immediately be removed
        return true;
    }
    
    // Whether the effect should apply this tick. Used e.g. by the Regeneration effect that only applies
    // once every x ticks, depending on the tick count and amplifier.
    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplifier) {
        return true;
    }
    
    // Utility method that is called when the effect is first added to the entity.
    // This does not get called again until all instances of this effect have been removed from the entity.
    @Override
    public void onEffectAdded(LivingEntity entity, int amplifier) {
        super.onEffectAdded(entity, amplifier);
    }

    @Override
    public int getColor() {
        return 0x00000000;
    }

    @Override
    public ParticleOptions createParticleOptions(MobEffectInstance effect) {
        return ParticleTypes.ENCHANT;
    }
}