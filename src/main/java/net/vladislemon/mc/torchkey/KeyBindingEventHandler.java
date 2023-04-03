package net.vladislemon.mc.torchkey;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.settings.KeyBinding;

public class KeyBindingEventHandler {
    private final KeyBinding keyBinding;
    private final Runnable callback;

    public KeyBindingEventHandler(KeyBinding keyBinding, Runnable callback) {
        this.keyBinding = keyBinding;
        this.callback = callback;
        ClientRegistry.registerKeyBinding(keyBinding);
        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void onKeyInputEvent(InputEvent.KeyInputEvent event) {
        if (keyBinding.isPressed()) {
            callback.run();
        }
    }

    @SubscribeEvent
    public void onMouseInputEvent(InputEvent.MouseInputEvent event) {
        if (keyBinding.isPressed()) {
            callback.run();
        }
    }
}
