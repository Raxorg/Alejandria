package com.epicness.fundamentals.assets;

import static com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;

import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.epicness.fundamentals.assets.loaders.SpriteArrayLoader;
import com.epicness.fundamentals.assets.loaders.SpriteLoader;

public abstract class AssetPaths {

    public static final SpriteArrayLoader.SpriteArrayParameter LINEAR_SPRITE_ARRAY;
    static final SpriteLoader.SpriteParameter LINEAR_SPRITE;
    static final TextureLoader.TextureParameter LINEAR_TEXTURE;

    static {
        LINEAR_SPRITE_ARRAY = new SpriteArrayLoader.SpriteArrayParameter();
        LINEAR_SPRITE_ARRAY.minFilter = Linear;
        LINEAR_SPRITE_ARRAY.magFilter = Linear;

        LINEAR_SPRITE = new SpriteLoader.SpriteParameter();
        LINEAR_SPRITE.minFilter = Linear;
        LINEAR_SPRITE.magFilter = Linear;

        LINEAR_TEXTURE = new TextureLoader.TextureParameter();
        LINEAR_TEXTURE.minFilter = Linear;
        LINEAR_TEXTURE.magFilter = Linear;
    }

    protected static BitmapFontLoader.BitmapFontParameter scaledParameter(FileHandle fileHandle, float scale) {
        BitmapFontLoader.BitmapFontParameter bitmapFontParameter = new BitmapFontLoader.BitmapFontParameter();
        BitmapFontData bitmapFontData = new BitmapFontData(fileHandle, false);
        bitmapFontData.setScale(scale);
        bitmapFontParameter.bitmapFontData = bitmapFontData;
        return bitmapFontParameter;
    }
}