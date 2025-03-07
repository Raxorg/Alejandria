package com.epicness.fundamentals.logic.handlers;

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

    public void saveBoolean(String preferencesPath, String key, boolean value) {
        Preferences prefs = Gdx.app.getPreferences(preferencesPath);
        prefs.putBoolean(key, value);
        prefs.flush();
    }

    public void saveFloat(String preferencesPath, String key, float value) {
        Preferences prefs = Gdx.app.getPreferences(preferencesPath);
        prefs.putFloat(key, value);
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

    public boolean loadBoolean(String preferencesPath, String key) {
        Preferences prefs = Gdx.app.getPreferences(preferencesPath);
        return prefs.getBoolean(key);
    }

    public float loadFloat(String preferencesPath, String key) {
        Preferences prefs = Gdx.app.getPreferences(preferencesPath);
        return prefs.getFloat(key);
    }

    public void removeString(String preferencesPath, String key) {
        Preferences prefs = Gdx.app.getPreferences(preferencesPath);
        prefs.remove(key);
        prefs.flush();
    }
}