package com.example.examplemod;

import com.axperty.delightlib.api.DelightAddon;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.SoundType;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(ExampleMod.MOD_ID)
public class ExampleMod {
    public static final String MOD_ID = "examplemod";
    public static DelightAddon addon;

    public ExampleMod(IEventBus bus, ModContainer modContainer)
    {
        bus.addListener(this::commonSetup);

        // Add-on Name & Creative Tab Registry
        addon = DelightAddon.create(MOD_ID, bus)
                .withCreativeTab("Example Mod", () -> new ItemStack(Items.BREAD));

        // Knife
        addon.knife("obsidian_knife", Tiers.DIAMOND)
                .attackDamage(1.5f)
                .attackSpeed(-1.8f)
                .fireResistant()
                .build();

        // Beef Stew (Default Food Item)
        addon.food("beef_stew")
                .nutrition(4)
                .saturation(0.4f)
                .build();

        // Beef Stew (Default Food Item) Cooking Pot Recipe
        addon.cookingRecipe("beef_stew")
                .addIngredient("minecraft:cooked_beef")
                .addIngredient("minecraft:carrot")
                .addIngredient("minecraft:potato")
                .result("examplemod:beef_stew")
                .container("minecraft:bowl")
                .experience(1.0f)
                .cookingTime(200)
                .recipeBookTab("meals")
                .build();

        // Cozy Stew (Placeable Food Item) & Cozy Stew Serving
        addon.food("cozy_stew_serving").nutrition(6).saturation(0.6f).bowlFood().build();
        addon.placeableFood("cozy_stew")
                .asFeast("cozy_stew_serving", true) // true indicates it leaves a container (leftovers)
                .build();

        // Orange Juice
        addon.food("orange_juice")
                .nutrition(2)
                .saturation(0.2f)
                .drinkable() // Sets bottle as remainder and stacks to 16
                .alwaysEdible()
                .build();

        // Garlic, Garlic Seeds, Garlic Crop
        addon.crop("garlic")
                .asFood(1, 0.1f)
                .seedIsItem()
                .build();

        // Chocolate Cake & Chocolate Cake Slice
        addon.food("chocolate_cake_slice").nutrition(3).saturation(0.3f).fast().build();
        addon.placeableFood("chocolate_cake")
                .asPie("chocolate_cake_slice")
                .build();

        // Oak Log Cabinet
        addon.cabinet("oak_log_cabinet")
                .soundType(SoundType.WOOD)
                .burnTime(300) // The time it takes to burn it inside a furnace (300 ticks)
                .recipe(b -> b.grid("OOO", "T T", "OOO")
                        .define('O', "minecraft:oak_log")
                        .define('T', "minecraft:spruce_trapdoor"))
                .build();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {}
}
