package ru.vladislemon.torchkey;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TorchPlacer {
    private static final int INVENTORY_SELECTION_SIZE;

    static {
        //noinspection ConstantConditions
        INVENTORY_SELECTION_SIZE = ObfuscationReflectionHelper
                .getPrivateValue(Inventory.class, null, "f_150070_");
    }

    private final Minecraft minecraft;
    private final TorchDetector torchDetector;
    private final Method rightClickMouseMethod;

    public TorchPlacer(final Minecraft minecraft, final TorchDetector torchDetector) {
        this.minecraft = minecraft;
        this.torchDetector = torchDetector;
        this.rightClickMouseMethod = ObfuscationReflectionHelper.findMethod(minecraft.getClass(), "m_91277_");
    }

    public void tryPlaceTorch() {
        if (!isWorldInFocus()) {
            return;
        }
        //noinspection ConstantConditions
        final Inventory inventory = minecraft.player.getInventory();
        final int previousItem = inventory.selected;
        for (int i = 0; i < INVENTORY_SELECTION_SIZE; i++) {
            if (torchDetector.isTorch(inventory.getItem(i))) {
                inventory.selected = i;
                tryRightClick();
                inventory.selected = previousItem;
                return;
            }
        }
    }

    private boolean isWorldInFocus() {
        if (minecraft == null) {
            return false;
        }
        if (minecraft.player == null) {
            return false;
        }
        //noinspection RedundantIfStatement
        if (minecraft.screen != null) {
            return false;
        }
        return true;
    }

    private void tryRightClick() {
        try {
            rightClickMouseMethod.invoke(minecraft);
        } catch (final IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(e);
        }
    }
}
