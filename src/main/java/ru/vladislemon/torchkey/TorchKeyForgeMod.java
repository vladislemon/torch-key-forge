package ru.vladislemon.torchkey;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkConstants;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.glfw.GLFW;

@Mod(TorchKeyForgeMod.MOD_ID)
public class TorchKeyForgeMod {
    public static final String MOD_ID = "torch_key_forge";

    private final Config config;
    private final KeyMapping keyBinding = new KeyMapping(
            "key.torch-key.place-torch",
            InputConstants.Type.MOUSE,
            GLFW.GLFW_MOUSE_BUTTON_MIDDLE,
            "category.torch-key.general"
    );

    public TorchKeyForgeMod() {
        //Register config
        final Pair<Config, ForgeConfigSpec> configSpecPair = new ForgeConfigSpec.Builder().configure(Config::new);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, configSpecPair.getValue());
        config = configSpecPair.getKey();
        //Register client initializer
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetupEvent);
        //Make sure the mod being absent on the other network side does not cause the client to display the server as incompatible
        ModLoadingContext.get().registerExtensionPoint(
                IExtensionPoint.DisplayTest.class,
                () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (incoming, isNetwork) -> true)
        );
    }

    @OnlyIn(Dist.CLIENT)
    private void onClientSetupEvent(final FMLClientSetupEvent event) {
        final TorchPlacer torchPlacer = new TorchPlacer(Minecraft.getInstance(), new TorchDetector(config));
        new KeyBindingEventHandler(keyBinding, torchPlacer::tryPlaceTorch);
    }
}
