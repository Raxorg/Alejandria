package com.epicness.fundamentals.utils;

import java.io.File;

public class OSUtils {

    @SuppressWarnings("SdCardPath")
    private static final String[] BLUESTACKS_DIRS = {
            "/sdcard/windows/BstSharedFolder",
            "/mnt/windows/BstSharedFolder",
    };

    public static boolean IsRunningOnBluestacks() {
        for (int i = 0; i < BLUESTACKS_DIRS.length; i++) {
            if (new File(BLUESTACKS_DIRS[i]).exists()) {
                return true;
            }
        }
        return false;
    }
}