package com.epicness.fundamentals.assets.loaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import com.epicness.fundamentals.assets.Shader;
import com.epicness.fundamentals.assets.loaders.ShaderProgramLoader.ShaderProgramParameter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@SuppressWarnings("rawtypes")
public class ShaderProgramLoader extends SynchronousAssetLoader<ShaderProgram, ShaderProgramParameter> {

    private String vertexPath, fragmentPath;

    public ShaderProgramLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public ShaderProgram load(AssetManager assetManager, String fileName, FileHandle file, ShaderProgramParameter parameter) {
        Shader vertexShader = assetManager.get(vertexPath);
        Shader fragmentShader = assetManager.get(fragmentPath);
        return new ShaderProgram(vertexShader.definition, fragmentShader.definition);
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, ShaderProgramParameter parameter) {
        Array<AssetDescriptor> dependencies = new Array<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.read()), 128);
        try {
            String line = reader.readLine();
            FileHandle vertexHandle = file.parent().child(line.split(" ")[1]);
            vertexPath = vertexHandle.path();
            dependencies.add(new AssetDescriptor<>(vertexHandle, Shader.class));

            line = reader.readLine();
            FileHandle fragmentHandle = file.parent().child(line.split(" ")[1]);
            fragmentPath = fragmentHandle.path();
            dependencies.add(new AssetDescriptor<>(fragmentHandle, Shader.class));
        } catch (Exception ex) {
            throw new GdxRuntimeException("Error loading sp file: " + fileName, ex);
        } finally {
            StreamUtils.closeQuietly(reader);
        }
        return dependencies;
    }

    public static class ShaderProgramParameter extends AssetLoaderParameters<ShaderProgram> {
    }
}