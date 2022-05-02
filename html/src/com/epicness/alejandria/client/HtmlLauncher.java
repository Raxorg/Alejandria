package com.epicness.alejandria.client;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.WINDOW_SIZE;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.epicness.alejandria.AlejandriaApp;

public class HtmlLauncher extends GwtApplication {

    @Override
    public GwtApplicationConfiguration getConfig() {
        // Resizable application, uses available space in browser
        GwtApplicationConfiguration config = new GwtApplicationConfiguration(WINDOW_SIZE, WINDOW_SIZE, true);
        config.padHorizontal = 0;
        config.padVertical = 0;
        return config;
        // Fixed size application:
        //return new GwtApplicationConfiguration(480, 320);
    }

    @Override
    public ApplicationListener createApplicationListener() {
        return new AlejandriaApp();
    }
}