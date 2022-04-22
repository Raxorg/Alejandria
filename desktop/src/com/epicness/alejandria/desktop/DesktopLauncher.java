package com.epicness.alejandria.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.epicness.alejandria.AlejandriaApp;

public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(800, 800);
        new Lwjgl3Application(new AlejandriaApp(), config);
        // The standalone package in the core module contains demos independent from the showcase app
    }
}