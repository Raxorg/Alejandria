package com.epicness.fundamentals.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.epicness.fundamentals.assets.loaders.ExplicitMusicLoader;
import com.epicness.fundamentals.assets.loaders.ExplicitSoundLoader;
import com.epicness.fundamentals.assets.loaders.ShaderLoader;
import com.epicness.fundamentals.assets.loaders.ShaderProgramLoader;
import com.epicness.fundamentals.assets.loaders.SpriteArrayLoader;
import com.epicness.fundamentals.assets.loaders.SpriteLoader;

import java.util.List;

public abstract class Assets {

    protected final AssetManager assetManager;
    private final List<AssetDescriptor<?>> assetDescriptors;
    private boolean assetsInitialized = false;

    public Assets(List<AssetDescriptor<?>> assetDescriptors) {
        assetManager = new AssetManager();
        FileHandleResolver resolver = assetManager.getFileHandleResolver();
        assetManager.setLoader(Music.class, new ExplicitMusicLoader(resolver));
        assetManager.setLoader(Shader.class, new ShaderLoader(resolver));
        assetManager.setLoader(ShaderProgram.class, new ShaderProgramLoader(resolver));
        assetManager.setLoader(Sound.class, new ExplicitSoundLoader(resolver));
        assetManager.setLoader(Sprite.class, new SpriteLoader(resolver));
        assetManager.setLoader(Sprite[].class, new SpriteArrayLoader(resolver));
        this.assetDescriptors = assetDescriptors;
    }

    public final void queueAssetLoading() {
        for (int i = 0; i < assetDescriptors.size(); i++) {
            assetManager.load(assetDescriptors.get(i));
        }
    }

    /* Default recommended way of loading assets -> async */
    public final boolean loadAssets() {
        return assetManager.update();
    }

    /* Obstructing way of loading assets -> blocks rendering until finished */
    public final void finishLoading() {
        assetManager.finishLoading();
    }

    public final void initAssets() {
        initializeAssets();
        assetsInitialized = true;
    }

    protected abstract void initializeAssets();

    public final void assetsInitialized() {
        assetsInitialized = true;
    }

    public final boolean areAssetsInitialized() {
        return assetsInitialized;
    }

    protected final <T> T get(AssetDescriptor<T> assetDescriptor) {
        return assetManager.get(assetDescriptor);
    }
}