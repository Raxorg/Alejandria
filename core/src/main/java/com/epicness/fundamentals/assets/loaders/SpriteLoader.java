package com.epicness.fundamentals.assets.loaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.epicness.fundamentals.assets.loaders.SpriteLoader.SpriteParameter;

@SuppressWarnings("rawtypes")
public class SpriteLoader extends AsynchronousAssetLoader<Sprite, SpriteParameter> {

    static public class SpriteLoaderInfo {
        String filename;
        TextureData data;
        Texture texture;
    }

    SpriteLoaderInfo info = new SpriteLoaderInfo();

    public SpriteLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, SpriteParameter parameter) {
        info.filename = fileName;
        if (parameter == null || parameter.textureData == null) {
            Pixmap.Format format = null;
            boolean genMipMaps = false;
            info.texture = null;

            if (parameter != null) {
                format = parameter.format;
                genMipMaps = parameter.genMipMaps;
                info.texture = parameter.texture;
            }

            info.data = TextureData.Factory.loadFromFile(file, format, genMipMaps);
        } else {
            info.data = parameter.textureData;
            info.texture = parameter.texture;
        }
        if (!info.data.isPrepared()) info.data.prepare();
    }

    @Override
    public Sprite loadSync(AssetManager manager, String fileName, FileHandle file, SpriteParameter parameter) {
        if (info == null) return null;
        Texture texture = info.texture;
        if (texture != null) {
            texture.load(info.data);
        } else {
            texture = new Texture(info.data);
        }
        if (parameter != null) {
            texture.setFilter(parameter.minFilter, parameter.magFilter);
            texture.setWrap(parameter.wrapU, parameter.wrapV);
        }
        return new Sprite(texture);
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, SpriteParameter parameter) {
        return null;
    }

    static public class SpriteParameter extends AssetLoaderParameters<Sprite> {
        public Pixmap.Format format = null;
        public boolean genMipMaps = false;
        public Texture texture = null;
        public TextureData textureData = null;
        public Texture.TextureFilter minFilter = Texture.TextureFilter.Nearest;
        public Texture.TextureFilter magFilter = Texture.TextureFilter.Nearest;
        public Texture.TextureWrap wrapU = Texture.TextureWrap.ClampToEdge;
        public Texture.TextureWrap wrapV = Texture.TextureWrap.ClampToEdge;
    }
}