package ru.vladislemon.torchkey;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class TorchDetector {

    public boolean isTorch(final ItemStack itemStack) {
        return Items.TORCH.equals(itemStack.getItem());
    }
}
