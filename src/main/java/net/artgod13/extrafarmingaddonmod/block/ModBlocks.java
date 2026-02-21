package net.artgod13.extrafarmingaddonmod.block;

import net.artgod13.extrafarmingaddonmod.ExtraFarmingAddOnMod;
import net.artgod13.extrafarmingaddonmod.block.custom.CornCropBlock;
import net.artgod13.extrafarmingaddonmod.block.custom.TomatoCropBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

//Step one of adding a block, create the block class and register it here, then add it to the item group so you can find it in creative mode


public class ModBlocks {

    public static final Block LOOT_CRATE = registerBlock("loot_crate",
            new LootCrateBlock(AbstractBlock.Settings.create().strength(1f).sounds(BlockSoundGroup.WOOD)));


    public static final Block CORN_CROP = registerBlockWithoutBlockItem("corn_crop",
            new CornCropBlock(AbstractBlock.Settings.create().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY).mapColor(MapColor.DARK_GREEN)));


    public static final Block TOMATO_CROP = registerBlockWithoutBlockItem("tomato_crop",
            new TomatoCropBlock(AbstractBlock.Settings.create().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY).mapColor(MapColor.DARK_GREEN)));

    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK , Identifier.of(ExtraFarmingAddOnMod.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK , Identifier.of(ExtraFarmingAddOnMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(ExtraFarmingAddOnMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        ExtraFarmingAddOnMod.LOGGER.info("Registering ModBlocks for " + ExtraFarmingAddOnMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.LOOT_CRATE);
        });
    }
}
