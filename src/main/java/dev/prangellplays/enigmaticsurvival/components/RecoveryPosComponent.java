package dev.prangellplays.enigmaticsurvival.components;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.prangellplays.enigmaticsurvival.init.EnigmaticSurvivalComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class RecoveryPosComponent implements AutoSyncedComponent {
    private final PlayerEntity player;
    private BlockPos deathPos = null;
    private int deathTime = 0;

    public RecoveryPosComponent(PlayerEntity player) {
        this.player = player;
    }

    public void tick() {
        if (this.deathTime > 0) {
            --this.deathTime;
            if (this.deathTime == 0) {
                this.setDeathPos((BlockPos)null);
            }
        }

    }

    public Optional<BlockPos> getDeathPos() {
        return Optional.ofNullable(this.deathPos);
    }

    public void setDeathPos(BlockPos deathPos) {
        this.deathPos = deathPos;
        if (deathPos != null) {
            this.deathTime = 100;
        }

        EnigmaticSurvivalComponents.RECOVERY_POS_COMPONENT.sync(this.player);
    }

    public void readFromNbt(@NotNull NbtCompound tag) {
        if (tag.contains("deathPos")) {
            NbtCompound pos = tag.getCompound("deathPos");
            this.deathPos = new BlockPos(pos.getInt("x"), pos.getInt("y"), pos.getInt("z"));
        } else {
            this.deathPos = null;
        }

    }

    public void writeToNbt(@NotNull NbtCompound tag) {
        if (this.deathPos != null) {
            NbtCompound pos = new NbtCompound();
            pos.putInt("x", this.deathPos.getX());
            pos.putInt("y", this.deathPos.getY());
            pos.putInt("z", this.deathPos.getZ());
            tag.put("deathPos", pos);
        }

    }
}