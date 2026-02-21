package net.artgod13.extrafarmingaddonmod.datagen;

import net.artgod13.extrafarmingaddonmod.block.ModBlocks;
import net.artgod13.extrafarmingaddonmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        List<ItemConvertible> CORN_SMELTABLES = List.of(ModItems.CORN_SEEDS);

        offerSmelting(exporter, CORN_SMELTABLES, RecipeCategory.FOOD, ModItems.POPCORN, .25f, 200, "extra_farming_items");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.LOOT_CRATE)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .input('A', ItemTags.PLANKS)
                .input('B', Items.EMERALD)
                .criterion(hasItem(Items.EMERALD ), conditionsFromItem(Items.EMERALD))
                .offerTo(exporter, "loot_crate");
    }
}
