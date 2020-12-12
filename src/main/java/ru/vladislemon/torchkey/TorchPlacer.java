package ru.vladislemon.torchkey;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TorchPlacer {
    private final Minecraft minecraft;
    private final TorchDetector torchDetector;
    private final Method rightClickMouseMethod;

    public TorchPlacer(final Minecraft minecraft, final TorchDetector torchDetector) {
        this.minecraft = minecraft;
        this.torchDetector = torchDetector;
        try {
            this.rightClickMouseMethod = minecraft.getClass().getDeclaredMethod("rightClickMouse");
            this.rightClickMouseMethod.setAccessible(true);
        } catch (final NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
    }

    public void tryPlaceTorch() {
        if (minecraft == null || minecraft.player == null) {
            return;
        }
        final int previousItem = minecraft.player.inventory.currentItem;
        for (int i = 0; i < PlayerInventory.getHotbarSize(); i++) {
            if (torchDetector.isTorch(minecraft.player.inventory.mainInventory.get(i))) {
                minecraft.player.inventory.currentItem = i;
                tryRightClick();
                minecraft.player.inventory.currentItem = previousItem;
                return;
            }
        }
    }

    private void tryRightClick() {
        try {
            rightClickMouseMethod.invoke(minecraft);
        } catch (final IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(e);
        }
    }
}
