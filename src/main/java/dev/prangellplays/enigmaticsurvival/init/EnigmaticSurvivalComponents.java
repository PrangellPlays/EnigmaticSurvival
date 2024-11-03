package dev.prangellplays.enigmaticsurvival.init;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import dev.prangellplays.enigmaticsurvival.EnigmaticSurvival;
import dev.prangellplays.enigmaticsurvival.components.BackSlotComponent;
import dev.prangellplays.enigmaticsurvival.components.RecoveryPosComponent;
import net.minecraft.entity.player.PlayerEntity;

public class EnigmaticSurvivalComponents implements EntityComponentInitializer {
    public static final ComponentKey<RecoveryPosComponent> RECOVERY_POS_COMPONENT = ComponentRegistry.getOrCreate(EnigmaticSurvival.id("recoverypos"), RecoveryPosComponent.class);
    public static final ComponentKey<BackSlotComponent> BACK_WEAPON_COMPONENT = ComponentRegistry.getOrCreate(EnigmaticSurvival.id("backweapon"), BackSlotComponent.class);

    public EnigmaticSurvivalComponents() {
    }

    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(PlayerEntity.class, RECOVERY_POS_COMPONENT).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(RecoveryPosComponent::new);
        registry.beginRegistration(PlayerEntity.class, BACK_WEAPON_COMPONENT).respawnStrategy(RespawnCopyStrategy.CHARACTER).end(BackSlotComponent::new);
    }
}