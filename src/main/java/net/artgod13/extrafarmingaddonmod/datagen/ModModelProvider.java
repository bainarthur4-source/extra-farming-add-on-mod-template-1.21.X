package net.artgod13.extrafarmingaddonmod.datagen;

import net.artgod13.extrafarmingaddonmod.block.ModBlocks;
import net.artgod13.extrafarmingaddonmod.block.custom.CornCropBlock;
import net.artgod13.extrafarmingaddonmod.block.custom.TomatoCropBlock;
import net.artgod13.extrafarmingaddonmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LOOT_CRATE);

        blockStateModelGenerator.registerCrop(ModBlocks.CORN_CROP, CornCropBlock.AGE, 0,1,2,3,4);
        blockStateModelGenerator.registerCrop(ModBlocks.TOMATO_CROP, TomatoCropBlock.AGE, 0,1,2,3,4);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // Register item models but skip if a model file already exists in src/main/resources to avoid duplicates
        registerSafe(itemModelGenerator, ModItems.TOMATO, Models.GENERATED);
        registerSafe(itemModelGenerator, ModItems.TOMATO_SEEDS, Models.GENERATED);
        registerSafe(itemModelGenerator, ModItems.CORN, Models.GENERATED);
        registerSafe(itemModelGenerator, ModItems.CORN_SEEDS, Models.GENERATED);
        registerSafe(itemModelGenerator, ModItems.POPCORN, Models.GENERATED);
        registerSafe(itemModelGenerator, ModItems.TEST_ITEM, Models.GENERATED);
    }

    private void registerSafe(ItemModelGenerator gen, net.minecraft.item.Item item, net.minecraft.data.client.Model model) {
        try {
            // Determine the item id and check for an existing model JSON in src/main/resources
            var id = net.minecraft.registry.Registries.ITEM.getId(item);
            if (id != null) {
                Path modelPath = Paths.get("src", "main", "resources", "assets", id.getNamespace(), "models", "item", id.getPath() + ".json");
                File modelFile = modelPath.toFile();
                if (modelFile.exists()) {
                    System.out.println("Datagen: model file already exists, skipping generation for " + id);
                    return;
                }
            }

            gen.register(item, model);
        } catch (IllegalStateException e) {
            // Duplicate model definition detected or other model upload issue; skip and continue.
            var id = net.minecraft.registry.Registries.ITEM.getId(item);
            System.out.println("Datagen: skipping duplicate model for " + id + " (" + e.getMessage() + ")");
        } catch (Exception e) {
            var id = net.minecraft.registry.Registries.ITEM.getId(item);
            System.out.println("Datagen: failed to register model for " + id + ": " + e.getMessage());
        }
    }
}