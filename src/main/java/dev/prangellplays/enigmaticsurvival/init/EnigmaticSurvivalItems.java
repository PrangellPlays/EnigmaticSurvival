package dev.prangellplays.enigmaticsurvival.init;

import dev.prangellplays.enigmaticsurvival.EnigmaticSurvival;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public class EnigmaticSurvivalItems {
    protected static final Map<Item, Identifier> ITEMS = new LinkedHashMap();
    public static final Item ENIGMATIC_SURVIVAL_LOGO;

    public static void init() {
        ITEMS.forEach((item, id) -> {
            Registry.register(Registries.ITEM, id, item);
        });
    }

    protected static <T extends Item> T register(String name, T item) {
        ITEMS.put(item, EnigmaticSurvival.id(name));
        return item;
    }

    public EnigmaticSurvivalItems() {
    }

    static {
        ENIGMATIC_SURVIVAL_LOGO = register((String) "enigmatic_survival_logo", (Item) (new Item(new FabricItemSettings().maxCount(1))));
    }
}
