package net.artgod13.extrafarmingaddonmod.item;

import net.artgod13.extrafarmingaddonmod.ExtraFarmingAddOnMod;
import net.artgod13.extrafarmingaddonmod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup EXTRA_FARMING_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(ExtraFarmingAddOnMod.MOD_ID, "extra_farming_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.CORN))
                    .displayName(Text.translatable("Extra Farming Items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.CORN);
                        entries.add(ModItems.CORN_SEEDS);
                        entries.add(ModItems.POPCORN);
                        entries.add(ModItems.TOMATO);
                        entries.add(ModItems.TOMATO_SEEDS);
                        entries.add(ModBlocks.LOOT_CRATE);
                    }).build());



    public static void registerItemGroups() {
        ExtraFarmingAddOnMod.LOGGER.info("Registering Item Groups for " + ExtraFarmingAddOnMod.MOD_ID);
    }
}