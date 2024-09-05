package com.epicness.alejandria.lwjgl3;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.WINDOW_SIZE;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.epicness.fundamentals.FundamentalsTest;

/**
 * Used to launch the standalone @{@link com.badlogic.gdx.Game}s found within the core module's standalone package
 */
public class StandaloneLauncher {

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(WINDOW_SIZE, WINDOW_SIZE);
        config.setBackBufferConfig(8, 8, 8, 8, 16, 0, 4);
        new Lwjgl3Application(new FundamentalsTest(), config);
    }
}