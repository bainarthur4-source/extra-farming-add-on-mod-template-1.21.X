package net.artgod13.extrafarmingaddonmod;
//idk
import net.artgod13.extrafarmingaddonmod.block.ModBlocks;
import net.artgod13.extrafarmingaddonmod.item.ModItemGroups;
import net.artgod13.extrafarmingaddonmod.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtraFarmingAddOnMod implements ModInitializer {
	public static final String MOD_ID = "extrafarmingaddonmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {

		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		CompostingChanceRegistry.INSTANCE.add(ModItems.CORN, 0.5f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.TOMATO, 0.5f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.CORN_SEEDS, 0.25f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.TOMATO_SEEDS, 0.25f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.POPCORN, 0.5f);

	} 
}