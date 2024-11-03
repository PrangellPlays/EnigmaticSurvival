package dev.prangellplays.enigmaticsurvival.init;

import dev.prangellplays.enigmaticsurvival.EnigmaticSurvival;
import dev.prangellplays.enigmaticsurvival.block.originchoice.entity.ChoiceLecternBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EnigmaticSurvivalBlockEntities {
    public static final BlockEntityType<ChoiceLecternBlockEntity> CHOICE_LECTERN = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(EnigmaticSurvival.MOD_ID, "choice_lectern"), FabricBlockEntityTypeBuilder.create(ChoiceLecternBlockEntity::new,
            EnigmaticSurvivalBlocks.PRANGELLPLAYS_CHOICE_LECTERN,
            EnigmaticSurvivalBlocks.BLIMPTYLER_CHOICE_LECTERN,
            EnigmaticSurvivalBlocks.POTATOBOY_CHOICE_LECTERN,
            EnigmaticSurvivalBlocks.SEMMPIE_CHOICE_LECTERN,
            EnigmaticSurvivalBlocks.DANIPADIPA_CHOICE_LECTERN,
            EnigmaticSurvivalBlocks.ALOPECIAGAMING_CHOICE_LECTERN,
            EnigmaticSurvivalBlocks.SKYNOVIC_CHOICE_LECTERN,
            EnigmaticSurvivalBlocks.IIWATERDRAGON_CHOICE_LECTERN,
            EnigmaticSurvivalBlocks.NITWIT_CHOICE_LECTERN,
            EnigmaticSurvivalBlocks.FARMER_CHOICE_LECTERN,
            EnigmaticSurvivalBlocks.RANCHER_CHOICE_LECTERN,
            EnigmaticSurvivalBlocks.MINER_CHOICE_LECTERN,
            EnigmaticSurvivalBlocks.LUMBERJACK_CHOICE_LECTERN,
            EnigmaticSurvivalBlocks.COOK_CHOICE_LECTERN,
            EnigmaticSurvivalBlocks.BLACKSMITH_CHOICE_LECTERN,
            EnigmaticSurvivalBlocks.CLERIC_CHOICE_LECTERN,
            EnigmaticSurvivalBlocks.MERCHANT_CHOICE_LECTERN,
            EnigmaticSurvivalBlocks.EXPLORER_CHOICE_LECTERN,
            EnigmaticSurvivalBlocks.WARRIOR_CHOICE_LECTERN,
            EnigmaticSurvivalBlocks.ARCHER_CHOICE_LECTERN,
            EnigmaticSurvivalBlocks.ROGUE_CHOICE_LECTERN,
            EnigmaticSurvivalBlocks.BEASTMASTER_CHOICE_LECTERN,
            EnigmaticSurvivalBlocks.CHOICE_LECTERN_BLANK_SINGLE,
            EnigmaticSurvivalBlocks.CHOICE_LECTERN_BLANK_DUAL).build());

    public static void init() {

    }
}
