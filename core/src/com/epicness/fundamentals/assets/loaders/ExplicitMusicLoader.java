package com.epicness.fundamentals.assets.loaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.epicness.fundamentals.assets.loaders.ExplicitMusicLoader.MusicParameter;

@SuppressWarnings("rawtypes")
public class ExplicitMusicLoader extends AsynchronousAssetLoader<Music, MusicParameter> {


    private Music music;

    public ExplicitMusicLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    /**
     * Returns the {@link Music} instance currently loaded by this {@link com.badlogic.gdx.assets.loaders.MusicLoader}.
     *
     * @return the currently loaded {@link Music}, otherwise {@code null} if no {@link Music} has been loaded yet.
     */
    protected Music getLoadedMusic() {
        return music;
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, MusicParameter parameter) {
        String name = file.name().replace(".mmp3", ".mp3").replace(".mogg", ".ogg").replace(".mwav", ".wav");
        music = Gdx.audio.newMusic(file.sibling(name));
    }

    @Override
    public Music loadSync(AssetManager manager, String fileName, FileHandle file, MusicParameter parameter) {
        Music music = this.music;
        this.music = null;
        return music;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, MusicParameter parameter) {
        return null;
    }

    static public class MusicParameter extends AssetLoaderParameters<Music> {
    }
}