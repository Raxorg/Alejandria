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
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.epicness.fundamentals.assets.loaders.SpriteArrayLoader.SpriteArrayParameter;
import com.epicness.fundamentals.utils.AnimationUtils;

import java.io.BufferedReader;

@SuppressWarnings("rawtypes")
public class SpriteArrayLoader extends AsynchronousAssetLoader<Sprite[], SpriteArrayParameter> {

    static public class SpriteArrayLoaderInfo {
        String filename;
        TextureData data;
        Texture texture;
        int frameWidth;
        int frameHeight;
    }

    SpriteArrayLoaderInfo info = new SpriteArrayLoaderInfo();

    public SpriteArrayLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, SpriteArrayParameter parameter) {
        try (BufferedReader reader = file.reader(128)) {
            String line = reader.readLine();
            fileName = file.sibling(line).path();
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

                FileHandle textureHandle = file.sibling(line);
                info.data = TextureData.Factory.loadFromFile(textureHandle, format, genMipMaps);
            } else {
                info.data = parameter.textureData;
                info.texture = parameter.texture;
            }

            line = reader.readLine();
            info.frameWidth = Integer.parseInt(line.split(":")[1]);
            line = reader.readLine();
            info.frameHeight = Integer.parseInt(line.split(":")[1]);
        } catch (Exception ex) {
            throw new GdxRuntimeException("Error loading anim file: " + fileName, ex);
        }
        if (!info.data.isPrepared()) info.data.prepare();
    }

    @Override
    public Sprite[] loadSync(AssetManager manager, String fileName, FileHandle file, SpriteArrayParameter parameter) {
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
        return AnimationUtils.split(texture, info.frameWidth, info.frameHeight);
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, SpriteArrayParameter parameter) {
        return null;
    }

    public static class SpriteArrayParameter extends AssetLoaderParameters<Sprite[]> {
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