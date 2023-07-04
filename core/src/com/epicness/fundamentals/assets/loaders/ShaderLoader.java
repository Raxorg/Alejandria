package com.epicness.fundamentals.assets.loaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.epicness.fundamentals.assets.Shader;

@SuppressWarnings("rawtypes")
public class ShaderLoader extends SynchronousAssetLoader<Shader, ShaderLoader.ShaderParameter> {

    public ShaderLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public Shader load(AssetManager assetManager, String fileName, FileHandle file, ShaderParameter parameter) {
        return new Shader(file.readString());
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, ShaderParameter parameter) {
        return null;
    }

    public static class ShaderParameter extends AssetLoaderParameters<Shader> {
    }
}