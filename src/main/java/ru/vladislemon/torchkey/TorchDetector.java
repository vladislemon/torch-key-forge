package ru.vladislemon.torchkey;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class TorchDetector {

    public boolean isTorch(ItemStack itemStack) {
        return itemStack.getItem() == Items.TORCH;
    }
}
