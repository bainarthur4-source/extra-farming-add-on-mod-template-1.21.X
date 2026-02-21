package net.artgod13.extrafarmingaddonmod.event;

import net.artgod13.extrafarmingaddonmod.block.ModBlocks;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.util.ActionResult;

/**
 * Event handler for block interactions
 */
public class BlockBreakHandler {
    public static void register() {
        // Placeholder for future event handling
        // Block drops are now handled through LootCrateBlock.onStacksDropped()
    }
}
