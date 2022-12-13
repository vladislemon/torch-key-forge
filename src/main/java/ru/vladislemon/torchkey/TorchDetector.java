package ru.vladislemon.torchkey;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class TorchDetector {
    private final Config config;

    public TorchDetector(final Config config) {
        this.config = config;
    }

    public boolean isTorch(final ItemStack itemStack) {
        final String itemId = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(itemStack.getItem())).toString();
        return config.getTorchItems().contains(itemId);
    }
}
