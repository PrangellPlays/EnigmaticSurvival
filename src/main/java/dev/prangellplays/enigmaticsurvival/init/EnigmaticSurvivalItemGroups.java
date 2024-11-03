package dev.prangellplays.enigmaticsurvival.init;

import dev.prangellplays.enigmaticsurvival.EnigmaticSurvival;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class EnigmaticSurvivalItemGroups {
    public static final ItemGroup ENIGMATIC_SURVIVAL_ITEMS = Registry.register(Registries.ITEM_GROUP,
            new Identifier(EnigmaticSurvival.MOD_ID, "items"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.enigmaticsurvival.items")).icon(() -> new ItemStack(EnigmaticSurvivalItems.ENIGMATIC_SURVIVAL_LOGO)).entries((displayContext, entries) -> {
                //entries.add(EnigmaticSurvivalItems.ENIGMATIC_SURVIVAL_LOGO);
                entries.add(EnigmaticSurvivalBlocks.CLEAR_BARRIER);
            }).build());
    public static final ItemGroup ENIGMATIC_SURVIVAL_LECTERNS = Registry.register(Registries.ITEM_GROUP,
            new Identifier(EnigmaticSurvival.MOD_ID, "lecterns"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.enigmaticsurvival.lecterns")).icon(() -> new ItemStack(EnigmaticSurvivalBlocks.PRANGELLPLAYS_CHOICE_LECTERN)).entries((displayContext, entries) -> {
                //Origin
                entries.add(EnigmaticSurvivalBlocks.PRANGELLPLAYS_CHOICE_LECTERN);
                entries.add(EnigmaticSurvivalBlocks.BLIMPTYLER_CHOICE_LECTERN);
                entries.add(EnigmaticSurvivalBlocks.POTATOBOY_CHOICE_LECTERN);
                entries.add(EnigmaticSurvivalBlocks.SEMMPIE_CHOICE_LECTERN);
                entries.add(EnigmaticSurvivalBlocks.DANIPADIPA_CHOICE_LECTERN);
                entries.add(EnigmaticSurvivalBlocks.ALOPECIAGAMING_CHOICE_LECTERN);

                //Class
                entries.add(EnigmaticSurvivalBlocks.NITWIT_CHOICE_LECTERN);
                entries.add(EnigmaticSurvivalBlocks.FARMER_CHOICE_LECTERN);
                entries.add(EnigmaticSurvivalBlocks.RANCHER_CHOICE_LECTERN);
                entries.add(EnigmaticSurvivalBlocks.MINER_CHOICE_LECTERN);
                entries.add(EnigmaticSurvivalBlocks.LUMBERJACK_CHOICE_LECTERN);
                entries.add(EnigmaticSurvivalBlocks.COOK_CHOICE_LECTERN);
                entries.add(EnigmaticSurvivalBlocks.BLACKSMITH_CHOICE_LECTERN);
                entries.add(EnigmaticSurvivalBlocks.CLERIC_CHOICE_LECTERN);
                entries.add(EnigmaticSurvivalBlocks.MERCHANT_CHOICE_LECTERN);
                entries.add(EnigmaticSurvivalBlocks.EXPLORER_CHOICE_LECTERN);
                entries.add(EnigmaticSurvivalBlocks.WARRIOR_CHOICE_LECTERN);
                entries.add(EnigmaticSurvivalBlocks.ARCHER_CHOICE_LECTERN);
                entries.add(EnigmaticSurvivalBlocks.ROGUE_CHOICE_LECTERN);
                entries.add(EnigmaticSurvivalBlocks.BEASTMASTER_CHOICE_LECTERN);

                entries.add(EnigmaticSurvivalBlocks.CHOICE_LECTERN_BLANK_SINGLE);
                entries.add(EnigmaticSurvivalBlocks.CHOICE_LECTERN_BLANK_DUAL);
            }).build());

    public static void init() {

    }
}
