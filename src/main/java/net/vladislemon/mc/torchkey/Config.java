package net.vladislemon.mc.torchkey;

import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;

public class Config {

    public static class Categories {
        public static final String CLIENT = "client";
    }

    public static class Keys {
        public static final String TORCH_ITEMS = "torch-items";
    }

    public static class Defaults {
        public static final String[] TORCH_ITEMS = new String[]{"minecraft:torch"};
    }

    public static class Comments {
        public static final String TORCH_ITEMS = "List of item ID which must be detected as torch";
    }

    public static class Patterns {
        public static final Pattern TORCH_ITEMS = Pattern.compile("^([a-zA-Z0-9.\\-_]+):([a-zA-Z0-9.\\-_]+)$");
    }

    private final Collection<String> torchItems;

    public Config(File configFile) {
        Configuration configuration = new Configuration(configFile);
        configuration.load();

        torchItems = Arrays.asList(configuration
                .get(Categories.CLIENT, Keys.TORCH_ITEMS, Defaults.TORCH_ITEMS, Comments.TORCH_ITEMS, Patterns.TORCH_ITEMS)
                .getStringList());

        TorchKeyMod.debug("torchItems = " + torchItems);

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }

    public Collection<String> getTorchItems() {
        return torchItems;
    }
}