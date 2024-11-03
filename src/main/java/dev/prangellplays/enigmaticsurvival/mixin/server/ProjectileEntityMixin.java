package dev.prangellplays.enigmaticsurvival.mixin.server;

import dev.prangellplays.enigmaticsurvival.utils.ProjectileSlotHolder;
import net.minecraft.entity.projectile.ProjectileEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ProjectileEntity.class})
public class ProjectileEntityMixin implements ProjectileSlotHolder {
    private int enigmaticsurvival$ownedSlot = -1;

    public ProjectileEntityMixin() {
    }

    public int enigmaticsurvival$getOwnedSlot() {
        return this.enigmaticsurvival$ownedSlot;
    }

    public void enigmaticsurvival$setOwnedSlot(int slot) {
        this.enigmaticsurvival$ownedSlot = slot;
    }
}