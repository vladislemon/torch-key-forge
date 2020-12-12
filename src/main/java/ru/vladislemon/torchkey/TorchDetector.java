package ru.vladislemon.torchkey;

import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;

public class TorchDetector {
    private final Config config;

    public TorchDetector(final Config config) {
        this.config = config;
    }

    public boolean isTorch(final ItemStack itemStack) {
        //noinspection deprecation
        final String itemId = Registry.ITEM.getKey(itemStack.getItem()).toString();
        return config.getTorchItems().contains(itemId);
    }
}
