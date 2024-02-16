package com.epicness.fundamentals.assets.loaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.epicness.fundamentals.assets.loaders.ExplicitSoundLoader.SoundParameter;


@SuppressWarnings("rawtypes")
public class ExplicitSoundLoader extends AsynchronousAssetLoader<Sound, SoundParameter> {

    private Sound sound;

    public ExplicitSoundLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    /**
     * Returns the {@link Sound} instance currently loaded by this {@link com.badlogic.gdx.assets.loaders.SoundLoader}.
     *
     * @return the currently loaded {@link Sound}, otherwise {@code null} if no {@link Sound} has been loaded yet.
     */
    protected Sound getLoadedSound() {
        return sound;
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, SoundParameter parameter) {
        String name = file.name().replace(".smp3", ".mp3").replace(".sogg", ".ogg").replace(".swav", ".wav");
        sound = Gdx.audio.newSound(file.sibling(name));
    }

    @Override
    public Sound loadSync(AssetManager manager, String fileName, FileHandle file, SoundParameter parameter) {
        Sound sound = this.sound;
        this.sound = null;
        return sound;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, SoundParameter parameter) {
        return null;
    }

    static public class SoundParameter extends AssetLoaderParameters<Sound> {
    }
}