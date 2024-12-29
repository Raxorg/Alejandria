package com.epicness.alejandria.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.epicness.alejandria.AlejandriaApp;
import com.epicness.alejandria.interfacing.ShowcasePicker;

public class AndroidLauncher extends AndroidApplication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
        configuration.useImmersiveMode = true; // Recommended, but not required.
        AlejandriaApp app = new AlejandriaApp();
        app.setShowcasePicker(new ShowcasePicker() {
            @Override
            public void setShowcase(String example) {

            }

            @Override
            public String getShowcase() {
                return "";
            }
        });
        initialize(app, configuration);
    }
}