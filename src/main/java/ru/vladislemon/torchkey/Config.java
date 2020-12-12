package ru.vladislemon.torchkey;

import com.google.common.collect.ImmutableList;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class Config {
    @SuppressWarnings("deprecation")
    private static final List<String> DEFAULT_TORCH_ITEMS = ImmutableList.of(
            Registry.ITEM.getKey(Items.TORCH).toString(),
            Registry.ITEM.getKey(Items.SOUL_TORCH).toString()
    );
    private final ForgeConfigSpec.ConfigValue<List<? extends String>> torchItems;

    public Config(final ForgeConfigSpec.Builder builder) {
        torchItems = builder
                .comment("List of item ID which must be detected as torch")
                .translation("config.torch-key.torch-items")
                .defineList("torch-items", DEFAULT_TORCH_ITEMS, Config::validateTorchItems);
    }

    public List<? extends String> getTorchItems() {
        return torchItems.get();
    }

    private static boolean validateTorchItems(final Object element) {
        if (!(element instanceof String)) {
            return false;
        }
        final String s = (String) element;
        return !s.trim().isEmpty();
    }
}
