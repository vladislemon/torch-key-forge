package net.vladislemon.mc.torchkey;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;

public class TorchPlacer {
    private static final int INVENTORY_SELECTION_SIZE = 9;
    private final Minecraft minecraft;
    private final TorchDetector torchDetector;

    public TorchPlacer(Minecraft minecraft, TorchDetector torchDetector) {
        this.minecraft = minecraft;
        this.torchDetector = torchDetector;
    }

    public void tryPlaceTorch() {
        if (!isWorldInFocus()) {
            return;
        }
        InventoryPlayer inventory = minecraft.thePlayer.inventory;
        int previousItem = inventory.currentItem;
        for (int i = 0; i < INVENTORY_SELECTION_SIZE; i++) {
            if (torchDetector.isTorch(inventory.getStackInSlot(i))) {
                inventory.currentItem = i;
                minecraft.func_147121_ag(); //right click
                inventory.currentItem = previousItem;
                return;
            }
        }
    }

    private boolean isWorldInFocus() {
        if (minecraft == null) {
            return false;
        }
        if (minecraft.thePlayer == null) {
            return false;
        }
        return minecraft.currentScreen == null;
    }
}
