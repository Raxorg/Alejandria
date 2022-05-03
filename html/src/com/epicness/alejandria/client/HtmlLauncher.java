package com.epicness.alejandria.client;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.WINDOW_SIZE;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.epicness.alejandria.AlejandriaApp;

public class HtmlLauncher extends GwtApplication {

    @Override
    public GwtApplicationConfiguration getConfig() {
        GwtApplicationConfiguration config = new GwtApplicationConfiguration(WINDOW_SIZE, WINDOW_SIZE, true);
        config.padHorizontal = 0;
        config.padVertical = 0;
        return config;
    }

    @Override
    public ApplicationListener createApplicationListener() {
        return new AlejandriaApp();
    }
}