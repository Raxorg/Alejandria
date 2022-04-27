package com.epicness.alejandria.desktop;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.WINDOW_SIZE;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.epicness.alejandria.AlejandriaApp;

public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(WINDOW_SIZE, WINDOW_SIZE);
        config.setResizable(false);
        new Lwjgl3Application(new AlejandriaApp(), config);
        // The standalone package in the core module contains demos independent from the showcase app
    }
}