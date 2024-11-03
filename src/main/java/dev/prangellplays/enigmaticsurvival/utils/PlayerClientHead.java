package dev.prangellplays.enigmaticsurvival.utils;

import dev.prangellplays.enigmaticsurvival.entity.PlayerHeadEntity;
import net.minecraft.client.MinecraftClient;

import java.util.Objects;

public class PlayerClientHead {
    public PlayerClientHead() {
    }

    public static boolean isOwned(PlayerHeadEntity head) {
        if (MinecraftClient.getInstance().player != null) {
            return head.getPlayerUuid().isPresent() && head.getPlayerUuid().get() == MinecraftClient.getInstance().player.getUuid() ? true : Objects.equals(head.getPlayerName(), MinecraftClient.getInstance().player.getEntityName());
        } else {
            return false;
        }
    }
}