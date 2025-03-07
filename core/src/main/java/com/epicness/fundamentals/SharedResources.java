package com.epicness.fundamentals;

import com.badlogic.gdx.utils.Array;
import com.epicness.fundamentals.assets.SharedAssets;
import com.epicness.fundamentals.initializer.Initializer;
import com.epicness.fundamentals.input.SharedInput;
import com.epicness.fundamentals.logic.SharedLogic;
import com.epicness.fundamentals.stuff.SharedStuff;

public class SharedResources {

    private final SharedAssets assets;
    private final SharedLogic logic;
    private final SharedInput input;
    private final SharedScreen screen;
    private final SharedStuff stuff;
    private final Array<Initializer<?, ?, ?, ?>> initializers;

    public SharedResources() {
        assets = new SharedAssets();
        input = new SharedInput();
        logic = new SharedLogic();
        screen = new SharedScreen();
        stuff = new SharedStuff();
        initializers = new Array<>();

        input.setScreen(screen);
        logic.setSharedResources(this);
        stuff.setAssets(assets);
        stuff.setScreen(screen);

        assets.queueAssetLoading();
        assets.finishLoading();
        assets.initializeAssets();
        stuff.initializeStuff();
    }

    public SharedAssets getAssets() {
        return assets;
    }

    public SharedLogic getLogic() {
        return logic;
    }

    public SharedInput getInput() {
        return input;
    }

    public SharedScreen getScreen() {
        return screen;
    }

    public SharedStuff getStuff() {
        return stuff;
    }

    public Initializer<?, ?, ?, ?> findInitializer(Initializer<?, ?, ?, ?> initializer) {
        String initializerName = initializer.getClass().getName();
        for (int i = 0; i < initializers.size; i++) {
            Initializer<?, ?, ?, ?> currentInitializer = initializers.get(i);
            String currentName = currentInitializer.getClass().getName();
            if (currentName.equals(initializerName)) {
                return currentInitializer;
            }
        }
        initializers.add(initializer);
        return initializer;
    }

    public void registerInitializer(Initializer<?, ?, ?, ?> initializer) {
        initializers.add(initializer);
    }
}