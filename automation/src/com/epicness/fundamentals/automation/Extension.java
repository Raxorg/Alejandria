package com.epicness.fundamentals.automation;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.epicness.fundamentals.assets.Shader;

import java.util.HashMap;
import java.util.Map;

public enum Extension {

    ANIM("anim", Sprite[].class, "ANIMATION"),
    FNT("fnt", BitmapFont.class, "FONT"),
    GLSL("glsl", Shader.class, "SHADER"),
    JPG("jpg", Sprite.class, "SPRITE"),
    MOGG("mogg", Music.class, "MUSIC"),
    PNG("png", Sprite.class, "SPRITE"),
    SOGG("sogg", Sound.class, "SOUND"),
    SP("sp", ShaderProgram.class, "SHADER_PROGRAM"),
    SWAV("swav", Sound.class, "SOUND");

    private static final Map<String, Extension> lookup = new HashMap<>();

    static {
        for (Extension ext : Extension.values()) {
            lookup.put(ext.name, ext);
        }
    }

    public final String name;
    public final Class<?> type;
    public final String typeName;

    Extension(String name, Class<?> type, String typeName) {
        this.name = name;
        this.type = type;
        this.typeName = typeName;
    }

    static Class<?> getType(String extensionName) {
        Extension ext = lookup.get(extensionName);
        if (ext == null) {
            throw new GdxRuntimeException("Unsupported extension: " + extensionName);
        }
        return ext.type;
    }

    static String getTypeName(String extensionName) {
        Extension ext = lookup.get(extensionName);
        if (ext == null) {
            throw new GdxRuntimeException("Unsupported extension: " + extensionName);
        }
        return ext.typeName;
    }
}