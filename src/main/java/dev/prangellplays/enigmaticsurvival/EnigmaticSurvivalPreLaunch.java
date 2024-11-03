package dev.prangellplays.enigmaticsurvival;

import com.llamalad7.mixinextras.MixinExtrasBootstrap;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class EnigmaticSurvivalPreLaunch implements PreLaunchEntrypoint {
    public EnigmaticSurvivalPreLaunch() {
    }

    public void onPreLaunch() {
        MixinExtrasBootstrap.init();
    }
}