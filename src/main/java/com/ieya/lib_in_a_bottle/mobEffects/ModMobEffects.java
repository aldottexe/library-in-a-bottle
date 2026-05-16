package com.ieya.lib_in_a_bottle.mobEffects;

import com.ieya.lib_in_a_bottle.LibInABottle;

public class ModMobEffects {
    public static init() {}

    //MOB_EFFECTS is a DeferredRegister<MobEffect>
    public static final Holder<EnchantersAffinity> enchantersAffinity = MOB_EFFECTS.register("enchanters_affinity", () -> new EnchantersAffinity(
        //Can be either BENEFICIAL, NEUTRAL or HARMFUL. Used to determine the potion tooltip color of this effect.
        MobEffectCategory.BENEFICIAL,
        //The color of the effect particles.
        0xffffff
    ));

}