package dev.prangellplays.enigmaticsurvival.init;

import dev.prangellplays.enigmaticsurvival.EnigmaticSurvival;
import dev.prangellplays.enigmaticsurvival.block.ClearBarrierBlock;
import dev.prangellplays.enigmaticsurvival.block.originchoice.*;
import dev.prangellplays.enigmaticsurvival.block.originchoice.classes.*;
import dev.prangellplays.enigmaticsurvival.block.originchoice.origin.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class EnigmaticSurvivalBlocks {
    protected static final Map<Block, Identifier> BLOCKS = new LinkedHashMap();
    public static final Block CLEAR_BARRIER;
    //Origin
    public static final Block PRANGELLPLAYS_CHOICE_LECTERN;
    public static final Block BLIMPTYLER_CHOICE_LECTERN;
    public static final Block POTATOBOY_CHOICE_LECTERN;
    public static final Block SEMMPIE_CHOICE_LECTERN;
    public static final Block DANIPADIPA_CHOICE_LECTERN;
    public static final Block ALOPECIAGAMING_CHOICE_LECTERN;
    public static final Block SKYNOVIC_CHOICE_LECTERN;
    public static final Block IIWATERDRAGON_CHOICE_LECTERN;

    //Class
    public static final Block NITWIT_CHOICE_LECTERN;
    public static final Block FARMER_CHOICE_LECTERN;
    public static final Block RANCHER_CHOICE_LECTERN;
    public static final Block MINER_CHOICE_LECTERN;
    public static final Block LUMBERJACK_CHOICE_LECTERN;
    public static final Block COOK_CHOICE_LECTERN;
    public static final Block BLACKSMITH_CHOICE_LECTERN;
    public static final Block CLERIC_CHOICE_LECTERN;
    public static final Block MERCHANT_CHOICE_LECTERN;
    public static final Block EXPLORER_CHOICE_LECTERN;
    public static final Block WARRIOR_CHOICE_LECTERN;
    public static final Block ARCHER_CHOICE_LECTERN;
    public static final Block ROGUE_CHOICE_LECTERN;
    public static final Block BEASTMASTER_CHOICE_LECTERN;

    public static final Block CHOICE_LECTERN_BLANK_SINGLE;
    public static final Block CHOICE_LECTERN_BLANK_DUAL;

    public static void init() {
        BLOCKS.forEach((block, id) -> {
            Registry.register(Registries.BLOCK, id, block);
        });
    }

    protected static <T extends Block> T register(String name, T block) {
        BLOCKS.put(block, EnigmaticSurvival.id(name));
        return block;
    }

    protected static <T extends Block> T registerWithItem(String name, T block) {
        return registerWithItem(name, block, new FabricItemSettings());
    }

    protected static <T extends Block> T registerWithItem(String name, T block, FabricItemSettings settings) {
        return registerWithItem(name, block, (b) -> {
            return new BlockItem(b, settings);
        });
    }

    protected static <T extends Block> T registerWithItem(String name, T block, Function<T, BlockItem> itemGenerator) {
        EnigmaticSurvivalItems.register(name, (BlockItem)itemGenerator.apply(block));
        return register(name, block);
    }

    public EnigmaticSurvivalBlocks() {
    }

    static {
        CLEAR_BARRIER = registerWithItem("clear_barrier", new ClearBarrierBlock(AbstractBlock.Settings.create().nonOpaque().allowsSpawning(Blocks::never).dropsNothing().hardness(-1)));
        //Origin
        PRANGELLPLAYS_CHOICE_LECTERN = registerWithItem("prangellplays_choice_lectern", new PrangellPlaysChoiceLecternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
        BLIMPTYLER_CHOICE_LECTERN = registerWithItem("blimptyler_choice_lectern", new BlimpTylerChoiceLecternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
        POTATOBOY_CHOICE_LECTERN = registerWithItem("potatoboy_choice_lectern", new PotatoBoyChoiceLecternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
        SEMMPIE_CHOICE_LECTERN = registerWithItem("semmpie_choice_lectern", new SemmpieChoiceLecternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
        DANIPADIPA_CHOICE_LECTERN = registerWithItem("danipadipa_choice_lectern", new DanipadipaChoiceLecternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
        ALOPECIAGAMING_CHOICE_LECTERN = registerWithItem("alopeciagaming_choice_lectern", new AlopeciaGamingChoiceLecternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
        SKYNOVIC_CHOICE_LECTERN = registerWithItem("skynovic_choice_lectern", new SkynovicChoiceLecternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
        IIWATERDRAGON_CHOICE_LECTERN = registerWithItem("iiwaterdragon_choice_lectern", new IIWaterDragonChoiceLecternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));

        //Class
        NITWIT_CHOICE_LECTERN = registerWithItem("nitwit_choice_lectern", new NitwitChoiceLecternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
        FARMER_CHOICE_LECTERN = registerWithItem("farmer_choice_lectern", new FarmerChoiceLecternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
        RANCHER_CHOICE_LECTERN = registerWithItem("rancher_choice_lectern", new RancherChoiceLecternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
        MINER_CHOICE_LECTERN = registerWithItem("miner_choice_lectern", new MinerChoiceLecternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
        LUMBERJACK_CHOICE_LECTERN = registerWithItem("lumberjack_choice_lectern", new LumberjackChoiceLecternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
        COOK_CHOICE_LECTERN = registerWithItem("cook_choice_lectern", new CookChoiceLecternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
        BLACKSMITH_CHOICE_LECTERN = registerWithItem("blacksmith_choice_lectern", new BlacksmithChoiceLecternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
        CLERIC_CHOICE_LECTERN = registerWithItem("cleric_choice_lectern", new ClericChoiceLecternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
        MERCHANT_CHOICE_LECTERN = registerWithItem("merchant_choice_lectern", new MerchantChoiceLecternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
        EXPLORER_CHOICE_LECTERN = registerWithItem("explorer_choice_lectern", new ExplorerChoiceLecternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
        WARRIOR_CHOICE_LECTERN = registerWithItem("warrior_choice_lectern", new WarriorChoiceLecternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
        ARCHER_CHOICE_LECTERN = registerWithItem("archer_choice_lectern", new ArcherChoiceLecternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
        ROGUE_CHOICE_LECTERN = registerWithItem("rogue_choice_lectern", new RogueChoiceLecternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
        BEASTMASTER_CHOICE_LECTERN = registerWithItem("beastmaster_choice_lectern", new BeastmasterChoiceLecternBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));

        CHOICE_LECTERN_BLANK_SINGLE = registerWithItem("choice_lectern_blank_single", new ChoiceLecternBlankSingleBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
        CHOICE_LECTERN_BLANK_DUAL = registerWithItem("choice_lectern_blank_dual", new ChoiceLecternBlankDualBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
    }
}
