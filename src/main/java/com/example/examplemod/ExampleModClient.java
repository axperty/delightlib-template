package com.example.examplemod;

import com.axperty.delightlib.DelightLibClient;
import net.fabricmc.api.ClientModInitializer;

public class ExampleModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        DelightLibClient.registerClient(ExampleMod.addon);
    }
}

