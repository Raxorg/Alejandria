package com.epicness.alejandria.gwt;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.epicness.alejandria.AlejandriaApp;

public class GwtLauncher extends GwtApplication {

    @Override
    public GwtApplicationConfiguration getConfig() {
        // Resizable application, uses available space in browser with no padding:
        GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(true);
        cfg.padVertical = 0;
        cfg.padHorizontal = 0;
        cfg.preserveDrawingBuffer = true;
        cfg.antialiasing = true;
        return cfg;
    }

    @Override
    public ApplicationListener createApplicationListener() {
        AlejandriaApp app = new AlejandriaApp();
        app.setAlertSystem(new HTMLAlertSystem());
        app.setShowcasePicker(new HTMLShowcasePicker());
        return app;
    }
}