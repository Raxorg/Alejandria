package com.epicness.alejandria;

import com.badlogic.gdx.Game;
import com.epicness.alejandria.showcase.ShowcaseInitializer;
import com.epicness.fundamentals.SharedResources;

public class AlejandriaApp extends Game {

    @Override
    public void create() {
        new ShowcaseInitializer().initialize(new SharedResources());
    }
}