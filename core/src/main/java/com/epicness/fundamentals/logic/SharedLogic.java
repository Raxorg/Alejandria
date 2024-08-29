package com.epicness.fundamentals.logic;

import com.epicness.fundamentals.SharedResources;
import com.epicness.fundamentals.logic.behaviors.Fader;
import com.epicness.fundamentals.logic.behaviors.Tracker;
import com.epicness.fundamentals.logic.handlers.AssetLoader;
import com.epicness.fundamentals.logic.handlers.BackgroundHandler;
import com.epicness.fundamentals.logic.handlers.PreferencesHandler;
import com.epicness.fundamentals.logic.handlers.TransitionHandler;

public class SharedLogic {

    private final AssetLoader assetLoader;
    private final BackgroundHandler backgroundHandler;
    private final Tracker<Boolean> pauseTracker;
    private final PreferencesHandler preferencesHandler;
    private final TransitionHandler transitionHandler;
    private final Fader fader;

    public SharedLogic() {
        assetLoader = new AssetLoader();
        backgroundHandler = new BackgroundHandler();
        pauseTracker = new Tracker<>();
        preferencesHandler = new PreferencesHandler();
        transitionHandler = new TransitionHandler();
        fader = new Fader();

        transitionHandler.setLogic(this);
    }

    public void setSharedResources(SharedResources sharedResources) {
        backgroundHandler.setStuff(sharedResources.getStuff());
        transitionHandler.setSharedResources(sharedResources);
        fader.setStuff(sharedResources.getStuff());
    }

    // Helpers
    public AssetLoader getAssetLoader() {
        return assetLoader;
    }

    public BackgroundHandler getBackgroundHandler() {
        return backgroundHandler;
    }

    public Tracker<Boolean> getPauseTracker() {
        return pauseTracker;
    }

    public PreferencesHandler getPreferencesHandler() {
        return preferencesHandler;
    }

    public TransitionHandler getTransitionHandler() {
        return transitionHandler;
    }

    public Fader getFader() {
        return fader;
    }
}