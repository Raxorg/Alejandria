package com.epicness.alejandria.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.epicness.alejandria.AlejandriaApp;

import static com.epicness.alejandria.Constants.INITIAL_WINDOW_SIZE;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = INITIAL_WINDOW_SIZE;
        config.height = INITIAL_WINDOW_SIZE;
        config.forceExit = false;
        new LwjglApplication(new AlejandriaApp(), config);
    }
}