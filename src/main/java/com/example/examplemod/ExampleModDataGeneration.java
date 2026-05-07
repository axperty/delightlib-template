package com.example.examplemod;

import com.axperty.delightlib.api.DelightAddon;
import com.axperty.delightlib.internal.DelightDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ExampleModDataGeneration implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        DelightAddon addon = ExampleMod.addon;

        pack.addProvider((output, registries) -> new DelightDataGenerator(output, addon));
    }
}