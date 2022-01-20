package com.epicness.alejandria.desktop;

import static com.epicness.alejandria.Constants.INITIAL_WINDOW_SIZE;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.epicness.alejandria.AlejandriaApp;

public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(INITIAL_WINDOW_SIZE, INITIAL_WINDOW_SIZE);
        new Lwjgl3Application(new AlejandriaApp(), config);
        // See package com.epicness.alejandria.standalone for demos
    }
}