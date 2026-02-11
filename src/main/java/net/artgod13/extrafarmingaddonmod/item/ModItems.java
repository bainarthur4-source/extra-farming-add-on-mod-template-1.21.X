package net.artgod13.extrafarmingaddonmod.item;

import net.artgod13.extrafarmingaddonmod.ExtraFarmingAddOnMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    //Step one of adding an item, create a public static final item and register it using the registerItem method. You can change the name and settings of the item as you wish.
    public static final Item TEST_ITEM = registerItem("test_item", new Item(new Item.Settings()));
    public static final Item TOMATO = registerItem("tomato", new Item(new Item.Settings()));
    public static final Item TOMATO_SEEDS = registerItem("tomato_seeds", new Item(new Item.Settings()));
    public static final Item CORN = registerItem("corn", new Item(new Item.Settings()));
    public static final Item CORN_SEEDS = registerItem("corn_seeds", new Item(new Item.Settings()));
    public static final Item POPCORN = registerItem("popcorn", new Item(new Item.Settings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(ExtraFarmingAddOnMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ExtraFarmingAddOnMod.LOGGER.info("Registering ModItems for " + ExtraFarmingAddOnMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            //Step two of adding an item, add the item to using the entries.add() method. You can change the item group to whatever you want, but make sure to import the correct one.
            entries.add(TEST_ITEM);
            entries.add(TOMATO);
            entries.add(TOMATO_SEEDS);
            entries.add(CORN);
            entries.add(CORN_SEEDS);
            entries.add(POPCORN);
        });
    }
}

//Step 3 of adding a new item to the mod, this file is used to add the name of the item in the game, you can change the "Test Item" to whatever you want the item to be called in the game.

//step 4 make a itemname.json file in the models/item folder, this file is used to add the texture of the item in the game, you can change the "test_item" to whatever you want the item to be called in the game, but make sure it matches the name of the texture file you have in the textures/item folder.

//step 5 make a texture for the item and put it in the textures/item folder, the name of the texture file should match the name you used in the itemname.json file.