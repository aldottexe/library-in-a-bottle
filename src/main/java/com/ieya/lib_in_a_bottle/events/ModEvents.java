package com.ieya.lib_in_a_bottle.events;

import com.ieya.lib_in_a_bottle.LibInABottle;
import com.ieya.lib_in_a_bottle.mobEffects.EnchantersAffinity;
import com.ieya.lib_in_a_bottle.mobEffects.ModMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.enchanting.EnchantmentLevelSetEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class ModEvents {


    // Events for the Enchanter's Affinity Enchant
    private static final Map<BlockPos, WeakReference<Player>> TABLE_USERS = new HashMap<>();

    public static void onTableOpened(PlayerInteractEvent.RightClickBlock event) {
        if (event.getLevel().getBlockState(event.getPos()).is(Blocks.ENCHANTING_TABLE)) {
            TABLE_USERS.put(event.getPos(), new WeakReference<>(event.getEntity()));
        }
    }

    public static void onEnchantLevelSet(EnchantmentLevelSetEvent event) {
        WeakReference<Player> ref = TABLE_USERS.get(event.getPos());
        if (ref == null) return;
        Player player = ref.get();
        if (player == null || !player.hasEffect(ModMobEffects.enchantersAffinity)) return;

        int amplifier = player.getEffect(ModMobEffects.enchantersAffinity).getAmplifier();
        event.setEnchantLevel(Math.min(event.getEnchantLevel() + (amplifier + 1) * 10, 30));
    }
}
