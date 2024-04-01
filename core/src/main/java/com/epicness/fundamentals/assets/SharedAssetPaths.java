package com.epicness.fundamentals.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import java.util.ArrayList;
import java.util.List;

public class SharedAssetPaths {
    static final List<AssetDescriptor<?>> ASSETS;

    public static final AssetDescriptor<TextureAtlas> SPRITES_ATLAS;

    public static final AssetDescriptor<Sound> BEEP_SOUND;

    public static final AssetDescriptor<Sound> SHORTLASER_SOUND;

    public static final AssetDescriptor<BitmapFont> PIXELFONT_FONT;

    public static final AssetDescriptor<BitmapFont> TIMESSQUARE_FONT;

    public static final AssetDescriptor<Sprite> PIXEL_SPRITE;

    static {
        ASSETS = new ArrayList<>();
        ASSETS.add(SPRITES_ATLAS = new AssetDescriptor<>("fundamentals/atlases/sprites.atlas", TextureAtlas.class));
        ASSETS.add(BEEP_SOUND = new AssetDescriptor<>("fundamentals/audios/beep.swav", Sound.class));
        ASSETS.add(SHORTLASER_SOUND = new AssetDescriptor<>("fundamentals/audios/shortLaser.swav", Sound.class));
        ASSETS.add(PIXELFONT_FONT = new AssetDescriptor<>("fundamentals/fonts/pixelFont.fnt", BitmapFont.class));
        ASSETS.add(TIMESSQUARE_FONT = new AssetDescriptor<>("fundamentals/fonts/timesSquare.fnt", BitmapFont.class));
        ASSETS.add(PIXEL_SPRITE = new AssetDescriptor<>("fundamentals/images/pixel.png", Sprite.class));
    }
}