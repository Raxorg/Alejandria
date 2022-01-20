package com.epicness.fundamentals.logic;

import com.epicness.fundamentals.SharedResources;
import com.epicness.fundamentals.logic.behaviors.Fader;

public class SharedLogic {

    private final AssetLoader assetLoader;
    private final BackgroundHandler backgroundHandler;
    private final PreferencesHandler preferencesHandler;
    private final TransitionHandler transitionHandler;
    private final Fader fader;

    public SharedLogic() {
        assetLoader = new AssetLoader();
        backgroundHandler = new BackgroundHandler();
        preferencesHandler = new PreferencesHandler();
        transitionHandler = new TransitionHandler();
        fader = new Fader();

        transitionHandler.setLogic(this);
    }

    public void setSharedResources(SharedResources sharedResources) {
        backgroundHandler.setStuff(sharedResources.getStuff());
        transitionHandler.setSharedResources(sharedResources);
    }

    // Helpers
    public AssetLoader getAssetLoader() {
        return assetLoader;
    }

    public BackgroundHandler getBackgroundHandler() {
        return backgroundHandler;
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