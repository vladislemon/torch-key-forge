package ru.vladislemon.torchkey;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class KeyBindingEventHandler {
    private final KeyBinding keyBinding;
    private final Runnable runnable;

    public KeyBindingEventHandler(KeyBinding keyBinding, Runnable runnable) {
        this.keyBinding = keyBinding;
        this.runnable = runnable;
        ClientRegistry.registerKeyBinding(keyBinding);
        MinecraftForge.EVENT_BUS.addListener(this::onKeyInputEvent);
        MinecraftForge.EVENT_BUS.addListener(this::onMouseInputEvent);
    }

    private void onKeyInputEvent(InputEvent.KeyInputEvent event) {
        if (event.getAction() == GLFW.GLFW_PRESS && keyBinding.matchesKey(event.getKey(), event.getScanCode())) {
            runnable.run();
        }
    }

    private void onMouseInputEvent(InputEvent.MouseInputEvent event) {
        if (event.getAction() == GLFW.GLFW_PRESS && keyBinding.matchesMouseKey(event.getButton())) {
            runnable.run();
        }
    }
}
