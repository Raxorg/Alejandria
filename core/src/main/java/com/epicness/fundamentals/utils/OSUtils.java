package com.epicness.fundamentals.utils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

import java.io.File;

public class OSUtils {

    @SuppressWarnings("SdCardPath")
    private static final String[] BLUESTACKS_DIRS = {
        "/sdcard/windows/BstSharedFolder",
        "/mnt/windows/BstSharedFolder",
    };

    public static boolean isRunningOnBluestacks() {
        for (int i = 0; i < BLUESTACKS_DIRS.length; i++) {
            if (new File(BLUESTACKS_DIRS[i]).exists()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isHTML() {
        return Gdx.app.getType() == Application.ApplicationType.WebGL;
    }

    public static boolean isDesktop() {
        return Gdx.app.getType() == Application.ApplicationType.Desktop;
    }
}