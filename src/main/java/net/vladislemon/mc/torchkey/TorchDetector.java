package net.vladislemon.mc.torchkey;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TorchDetector {
    private final Config config;

    public TorchDetector(Config config) {
        this.config = config;
    }

    public boolean isTorch(ItemStack itemStack) {
        String itemId = Item.itemRegistry.getNameForObject(itemStack.getItem());
        return config.getTorchItems().contains(itemId);
    }
}
