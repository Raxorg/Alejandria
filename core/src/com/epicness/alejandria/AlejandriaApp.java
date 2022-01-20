package com.epicness.alejandria;

import com.badlogic.gdx.Game;
import com.epicness.alejandria.module.ModuleInitializer;
import com.epicness.fundamentals.SharedResources;

public class AlejandriaApp extends Game {

    @Override
    public void create() {
        new ModuleInitializer().initialize(new SharedResources());
    }
}