package net.artgod13.extrafarmingaddonmod;
//idk
import net.artgod13.extrafarmingaddonmod.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtraFarmingAddOnMod implements ModInitializer {
	public static final String MOD_ID = "extrafarmingaddonmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
	}
}