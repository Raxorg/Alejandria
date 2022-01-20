package com.epicness.fundamentals.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.Map;

public class PreferencesHandler {

    public void saveData(String preferencesPath, Map<String, ?> data) {
        Preferences prefs = Gdx.app.getPreferences(preferencesPath);
        prefs.put(data);
        prefs.flush();
    }

    public Map<String, ?> loadData(String preferencesPath) {
        Preferences prefs = Gdx.app.getPreferences(preferencesPath);
        return prefs.get();
    }

    public void clearData(String preferencesPath) {
        Preferences prefs = Gdx.app.getPreferences(preferencesPath);
        prefs.clear();
        prefs.flush();
    }

    public void saveString(String preferencesPath, String key, String value) {
        Preferences prefs = Gdx.app.getPreferences(preferencesPath);
        prefs.putString(key, value);
        prefs.flush();
    }

    public void saveInteger(String preferencesPath, String key, int value) {
        Preferences prefs = Gdx.app.getPreferences(preferencesPath);
        prefs.putInteger(key, value);
        prefs.flush();
    }

    public String loadString(String preferencesPath, String key) {
        Preferences prefs = Gdx.app.getPreferences(preferencesPath);
        return prefs.getString(key);
    }

    public int loadInteger(String preferencesPath, String key) {
        Preferences prefs = Gdx.app.getPreferences(preferencesPath);
        return prefs.getInteger(key);
    }

    public void removeString(String preferencesPath, String key) {
        Preferences prefs = Gdx.app.getPreferences(preferencesPath);
        prefs.remove(key);
        prefs.flush();
    }
}