package net.artgod13.extrafarmingaddonmod.item;

import net.minecraft.component.type.FoodComponent;

public class ModFoodComponent {
    public static final FoodComponent POPCORN = new FoodComponent.Builder().nutrition(3).saturationModifier(.75F).build();
    public static final FoodComponent CORN = new FoodComponent.Builder().nutrition(4).saturationModifier(1F).build();
    public static final FoodComponent TOMATO = new FoodComponent.Builder().nutrition(3).saturationModifier(1F).build();
}
