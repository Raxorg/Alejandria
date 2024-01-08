package com.epicness.alejandria.gwt;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.WINDOW_SIZE;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.epicness.alejandria.AlejandriaApp;

/**
 * Launches the GWT application.
 */
public class GwtLauncher extends GwtApplication {
    @Override
    public GwtApplicationConfiguration getConfig() {
        // Resizable application, uses available space in browser with no padding:
        GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(WINDOW_SIZE, WINDOW_SIZE, true);
        cfg.padVertical = 0;
        cfg.padHorizontal = 0;
        cfg.preserveDrawingBuffer = true;
        return cfg;
    }

    @Override
    public ApplicationListener createApplicationListener() {
        return new AlejandriaApp();
    }
}
