package com.epicness.fundamentals.logic;

import com.epicness.fundamentals.Initializer;
import com.epicness.fundamentals.SharedResources;

public class TransitionHandler {

    // Structure
    private SharedResources sharedResources;
    private Initializer initializer;
    private SharedLogic logic;
    // Logic
    private boolean transitionAllowed;

    public void startTransition(Initializer nextInitializer) {
        initializer = sharedResources.findInitializer(nextInitializer);
        logic.getAssetLoader().startLoadingAssets(initializer.getAssets());
    }

    public void update() {
        if (initializer.getAssets().areAssetsInitialized() && transitionAllowed) {
            showNewScreen();
            transitionAllowed = false;
        }
    }

    public void allowTransition() {
        transitionAllowed = true;
    }

    private void showNewScreen() {
        if (initializer.wasInitialized()) {
            initializer.fastInitialize(sharedResources);
            return;
        }
        initializer.initialize(sharedResources);
        initializer.initialized();
    }

    public void setSharedResources(SharedResources sharedResources) {
        this.sharedResources = sharedResources;
        logic = sharedResources.getLogic();
    }

    public void setLogic(SharedLogic logic) {
        this.logic = logic;
    }
}