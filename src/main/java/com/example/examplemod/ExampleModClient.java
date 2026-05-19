package com.example.examplemod;

import com.axperty.delightlib.DelightLibClient;
import com.axperty.delightlib.api.DelightAddon;
import net.fabricmc.api.ClientModInitializer;

public class ExampleModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        DelightLibClient.registerClient((DelightAddon) ExampleMod.addon);
    }
}