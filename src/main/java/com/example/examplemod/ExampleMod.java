package com.example.examplemod;

import com.axperty.delightlib.api.DelightAddon;
import com.axperty.delightlib.api.DelightApi;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ExampleMod.MOD_ID)
public class ExampleMod {
    public static final String MOD_ID = "examplemod";
    public static DelightApi addon;

    public ExampleMod(FMLJavaModLoadingContext context) {
        IEventBus bus = context.getModEventBus();
        bus.addListener(this::setup);

        // Creative Tab Registry
        addon = DelightApi.create(MOD_ID, bus) // Creates the creative tab
                .withCreativeTab("Example Mod", // Sets the add-on name in the creative tab
                        () -> new ItemStack(Items.BREAD)); // Sets a bread item icon

        // Example Knife
        addon.knife("example_knife", Tiers.DIAMOND) // Creates a knife with diamond stats
                .attackDamage(1.5f) // Adds extra damage to the base knife damage
                .attackSpeed(-1.8f) // Sets how fast the weapon swings, negative numbers mean it's slower than an empty hand
                .fireResistant() // Prevents the item from burning up when dropped into lava or fire
                .build(); // Registers the item

        // Example Stew Default Food Item
        addon.food("example_stew") // Creates a stew
                .nutrition(4) // Determines how many hunger points it restores
                .saturation(0.4f) // Sets the hidden value that keeps a player full longer
                .build(); // Registers the item

        // Example Stew Cooking Pot Recipe
        addon.cookingRecipe("example_stew") // Creates a new recipe for the cooking pot
                .addIngredient("farmersdelight:onion") // Adds a required ingredient
                .addIngredient("minecraft:carrot") // Adds a second required ingredient
                .addIngredient("minecraft:potato") // Adds a third required ingredient

                // You're able to add up to six total ingredients for your recipe

                .result("examplemod:example_stew") // Sets the final item you receive when cooking finishes
                .container("minecraft:bowl") // Requires a specific container to hold the cooked item
                .experience(1.0f) // Amount of XP received after cooking
                .cookingTime(200) // Sets the cook duration in ticks, 200 ticks equals 10 seconds
                .recipeBookTab("meals") // Places this recipe into the meals category in the recipe book
                .build(); // Registers the recipe

        // Example Feast
        addon.placeableFood("example_feast") // Creates a food item you can place
                .feast(addon.food("example_feast_serving") // Sets the placeable food item as feast and creates the serving item
                        .nutrition(6) // Determines how many hunger points it restores
                        .saturation(0.6f) // Sets the hidden value that keeps a player full longer
                        .bowlFood()) // Returns a bowl after eating
                .feastOutput("minecraft:bone_meal") // Sets the item dropped when the feast has no more servings, leave it empty to disable.
                .build(); // Registers the block

        // Example Pie
        addon.placeableFood("example_pie") // Creates a food item you can place
                .pie(addon.food("example_pie_slice") // // Sets the placeable food item as pie and creates the serving item
                        .nutrition(6) // Determines how many hunger points it restores
                        .saturation(0.6f)) // Sets the hidden value that keeps a player full longer
                .build(); // Registers the block

        // Example Juice
        addon.food("example_juice") // Creates a food item
                .nutrition(2) // Determines how many hunger points it restores
                .saturation(0.2f) // Sets the hidden value that keeps a player full longer
                .drinkable() // Sets a drinking animation and returns a glass bottle as leftover.
                .alwaysEdible() // Allows to consume this even if the hunger bar is full
                .build(); // Registers the item

        // Example Juice Crafting Table Recipe
        addon.shapelessRecipe("example_juice") // Creates a shapeless recipe
                .addIngredient("minecraft:apple") // Adds a required ingredient
                .addIngredient("minecraft:apple") // Adds a second required ingredient
                .addIngredient("minecraft:glass_bottle") // Adds a third required ingredient
                .result("examplemod:example_juice", 2) // Sets the final item you receive and the amount
                .build(); // Registers the recipe

        // Example Crop
        addon.crop("example_crop") // Creates a new crop, crop seeds, and the crop when planted
                .asFood(1, 0.1f) // Makes the raw crop edible, first number is nutrition and second is saturation
                .seedIsItem() // Allows to plant the harvested crop directly instead of using seeds.
                .build(); // Registers the crop

        // Example Cabinet
        addon.cabinet("example_cabinet") // Creates a cabinet.
                .soundType(SoundType.WOOD) // Plays wood sound when broken
                .burnTime(300) // Allows the cabinet be used as fuel, 300 ticks equals 15 seconds
                .recipe(b -> b.grid("O  ", "T T", "OOO") // Defines the exact crafting shape in the crafting table
                        .define('O', "minecraft:spruce_log") // Tells the game what item the letter O represents
                        .define('T', "minecraft:spruce_trapdoor")) // Tells the game what item the letter T represents
                .build(); // Registers the cabinet
    }

    private void setup(final FMLCommonSetupEvent event) {}
}
