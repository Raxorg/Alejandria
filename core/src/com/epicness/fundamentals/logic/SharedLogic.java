package com.epicness.fundamentals.logic;

import com.epicness.fundamentals.SharedResources;

public class SharedLogic {

    private final AssetLoader assetLoader;
    private final BackgroundHandler backgroundHandler;
    private final PreferencesHandler preferencesHandler;
    private final TransitionHandler transitionHandler;

    public SharedLogic() {
        assetLoader = new AssetLoader();
        backgroundHandler = new BackgroundHandler();
        preferencesHandler = new PreferencesHandler();
        transitionHandler = new TransitionHandler();

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
}