package ru.vladislemon.torchkey;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.glfw.GLFW;

public class KeyBindingEventHandler {
    private final KeyMapping keyBinding;
    private final Runnable callback;

    public KeyBindingEventHandler(final KeyMapping keyBinding, final Runnable callback) {
        this.keyBinding = keyBinding;
        this.callback = callback;
        MinecraftForge.EVENT_BUS.addListener(this::onKeyInputEvent);
        MinecraftForge.EVENT_BUS.addListener(this::onMouseInputEvent);
    }

    private void onKeyInputEvent(final InputEvent.Key event) {
        if (event.getAction() == GLFW.GLFW_PRESS && keyBinding.matches(event.getKey(), event.getScanCode())) {
            callback.run();
        }
    }

    private void onMouseInputEvent(final InputEvent.MouseButton event) {
        if (event.getAction() == GLFW.GLFW_PRESS && keyBinding.matchesMouse(event.getButton())) {
            callback.run();
        }
    }
}
