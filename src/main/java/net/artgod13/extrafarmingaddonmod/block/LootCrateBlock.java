package net.artgod13.extrafarmingaddonmod.block;

import net.artgod13.extrafarmingaddonmod.ExtraFarmingAddOnMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.World;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;

import java.util.List;

// A custom block that handles its own drops based on whether silk touch is used.
// With Silk Touch: drops the loot crate itself
// Without Silk Touch: drops 3 random farming items

public class LootCrateBlock extends Block {
    // List of farming item identifiers to drop randomly. Includes vanilla crops and my new mod items.
    private static final Identifier[] FARMING_ITEM_IDS = new Identifier[] {
        Identifier.of("minecraft", "wheat"),
        Identifier.of("minecraft", "wheat_seeds"),
        Identifier.of("minecraft", "carrot"),
        Identifier.of("minecraft", "potato"),
        Identifier.of("minecraft", "beetroot"),
        Identifier.of("minecraft", "beetroot_seeds"),
        Identifier.of("minecraft", "melon_slice"),
        Identifier.of("minecraft", "pumpkin"),
        Identifier.of(ExtraFarmingAddOnMod.MOD_ID, "corn"),
        Identifier.of(ExtraFarmingAddOnMod.MOD_ID, "corn_seeds"),
        Identifier.of(ExtraFarmingAddOnMod.MOD_ID, "tomato"),
        Identifier.of(ExtraFarmingAddOnMod.MOD_ID, "tomato_seeds"),
    };

    public LootCrateBlock(Settings settings) {
        super(settings);
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, net.minecraft.loot.context.LootContextParameterSet.Builder builder) {
        return java.util.Collections.emptyList();
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack tool) {
        // Run only on server
        if (world.isClient) {
            super.afterBreak(world, player, pos, state, blockEntity, tool);
            return;
        }

        int silkTouchLevel = 0;
        if (world instanceof ServerWorld serverWorld) {
            var enchantmentRegistry = serverWorld.getServer().getRegistryManager().get(RegistryKeys.ENCHANTMENT);
            var silkTouchEntry = enchantmentRegistry.getEntry(Enchantments.SILK_TOUCH);
            if (silkTouchEntry.isPresent()) {
                silkTouchLevel = EnchantmentHelper.getLevel(silkTouchEntry.get(), tool);
            }

            if (silkTouchLevel > 0) {
                // Drop the crate itself
                dropStack(serverWorld, pos, new ItemStack(this, 1));
            } else {
                // Drop 3 random farming items resolved at runtime
                for (int i = 0; i < 3; i++) {
                    int randomIndex = serverWorld.getRandom().nextInt(FARMING_ITEM_IDS.length);
                    Identifier id = FARMING_ITEM_IDS[randomIndex];
                    Item item = Registries.ITEM.get(id);
                    if (item == null) continue; // skip if not registered
                    ItemStack itemToDrop = new ItemStack(item);
                    dropStack(serverWorld, pos, itemToDrop);
                }
            }
        }


    }

    private static void dropStack(ServerWorld world, BlockPos pos, ItemStack stack) {
        if (!world.isClient) {
            double x = (double)pos.getX() + 0.5D + (double)(world.getRandom().nextFloat() - 0.5F) * 0.5D;
            double y = (double)pos.getY() + 0.5D + (double)(world.getRandom().nextFloat() - 0.5F) * 0.5D;
            double z = (double)pos.getZ() + 0.5D + (double)(world.getRandom().nextFloat() - 0.5F) * 0.5D;

            ItemEntity itemEntity = new ItemEntity(world, x, y, z, stack);
            itemEntity.setVelocity(world.getRandom().nextDouble() * 0.1D - 0.05D,
                                   world.getRandom().nextDouble() * 0.05D,
                                   world.getRandom().nextDouble() * 0.1D - 0.05D);
            world.spawnEntity(itemEntity);
        }
    }
}