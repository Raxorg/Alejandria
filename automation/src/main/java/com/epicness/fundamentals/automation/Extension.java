package com.epicness.fundamentals.automation;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.HashMap;
import java.util.Map;

public enum Extension {

    ANIM("anim", Sprite[].class, "ANIMATION"),
    ATLAS("atlas", TextureAtlas.class, "ATLAS"),
    FNT("fnt", BitmapFont.class, "FONT"),
    JPG("jpg", Sprite.class, "SPRITE"),
    MOGG("mogg", Music.class, "MUSIC"),
    PNG("png", Sprite.class, "SPRITE"),
    SOGG("sogg", Sound.class, "SOUND"),
    SP("sp", ShaderProgram.class, "SHADER_PROGRAM"),
    SWAV("swav", Sound.class, "SOUND");

    private static final Map<String, Extension> LOOKUP = new HashMap<>();

    static {
        for (Extension ext : Extension.values()) {
            LOOKUP.put(ext.name, ext);
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
        Extension ext = LOOKUP.get(extensionName);
        if (ext == null) {
            throw new GdxRuntimeException("Unsupported extension: " + extensionName);
        }
        return ext.type;
    }

    static String getTypeName(String extensionName) {
        Extension ext = LOOKUP.get(extensionName);
        if (ext == null) {
            throw new GdxRuntimeException("Unsupported extension: " + extensionName);
        }
        return ext.typeName;
    }
}