package ru.vladislemon.torchkey;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.glfw.GLFW;

@Mod("torch-key-forge")
public class TorchKeyForgeMod {
    private final KeyBinding keyBinding = new KeyBinding(
            "key.torch-key.place-torch",
            InputMappings.Type.MOUSE,
            GLFW.GLFW_MOUSE_BUTTON_MIDDLE,
            "category.torch-key.general"
    );

    public TorchKeyForgeMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetupEvent);
    }

    @OnlyIn(Dist.CLIENT)
    private void onClientSetupEvent(final FMLClientSetupEvent event) {
        TorchPlacer torchPlacer = new TorchPlacer(event.getMinecraftSupplier().get(), new TorchDetector());
        new KeyBindingEventHandler(keyBinding, torchPlacer::tryPlaceTorch);
    }
}
