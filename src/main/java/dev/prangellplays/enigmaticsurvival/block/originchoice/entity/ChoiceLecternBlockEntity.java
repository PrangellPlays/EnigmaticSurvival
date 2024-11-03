package dev.prangellplays.enigmaticsurvival.block.originchoice.entity;

import dev.prangellplays.enigmaticsurvival.init.EnigmaticSurvivalBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class ChoiceLecternBlockEntity extends BlockEntity {
    public ChoiceLecternBlockEntity(BlockPos pos, BlockState state) {
        super(EnigmaticSurvivalBlockEntities.CHOICE_LECTERN, pos, state);
    }
}
