package dev.prangellplays.enigmaticsurvival.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

@Environment(EnvType.CLIENT)
public class EnigmaticSurvivalKeybinds {
    public static KeyBinding swapWeaponAbility;
    public static KeyBinding weaponKeybind;
    public static KeyBinding swapKeybind;

    public EnigmaticSurvivalKeybinds() {
    }

    public static void init() {
        swapWeaponAbility = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.enigmaticsurvival.swapWeaponAbility", InputUtil.Type.KEYSYM, 67, "category.enigmaticsurvival"));
        weaponKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.enigmaticsurvival.select_weapon", InputUtil.Type.KEYSYM, 82, "category.enigmaticsurvival"));
        swapKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.enigmaticsurvival.swap_weapon", InputUtil.Type.KEYSYM, 71, "category.enigmaticsurvival"));
    }
}
