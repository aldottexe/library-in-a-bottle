package com.ieya.lib_in_a_bottle.mobEffects;

import com.ieya.lib_in_a_bottle.LibInABottle;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredHolder;

import static com.ieya.lib_in_a_bottle.LibInABottle.MOB_EFFECTS;

public class ModMobEffects {
    public static void init() {}

    //MOB_EFFECTS is a DeferredRegister<MobEffect>
    public static final DeferredHolder<MobEffect, EnchantersAffinity> enchantersAffinity = MOB_EFFECTS.register("enchanters_affinity", () -> new EnchantersAffinity(
        //Can be either BENEFICIAL, NEUTRAL or HARMFUL. Used to determine the potion tooltip color of this effect.
        MobEffectCategory.BENEFICIAL,
        //The color of the effect particles.
        0xffffff
    ));

}