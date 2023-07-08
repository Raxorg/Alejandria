package com.epicness.fundamentals.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.List;

public class SharedAssetPaths {
    static final List<AssetDescriptor<?>> ASSETS;

    public static final AssetDescriptor<Sound> SHORTLASER_SOUND;

    public static final AssetDescriptor<BitmapFont> PIXELFONT_FONT;

    public static final AssetDescriptor<BitmapFont> TIMESSQUARE_FONT;

    public static final AssetDescriptor<Sprite> CIRCLE_SPRITE;

    public static final AssetDescriptor<Sprite> GLOW_SPRITE;

    public static final AssetDescriptor<Sprite> LIBGDX_SPRITE;

    public static final AssetDescriptor<Sprite> PIXEL_SPRITE;

    public static final AssetDescriptor<Sprite> SQUARE32_SPRITE;

    public static final AssetDescriptor<Sprite> SQUARE32INVERTED_SPRITE;

    public static final AssetDescriptor<Sprite> TRIANGLE_SPRITE;

    public static final AssetDescriptor<Sprite> WEIRDSHAPE_SPRITE;

    static {
        ASSETS = new ArrayList<>();
        ASSETS.add(SHORTLASER_SOUND = new AssetDescriptor<>("fundamentals/audios/shortLaser.swav", Sound.class));
        ASSETS.add(PIXELFONT_FONT = new AssetDescriptor<>("fundamentals/fonts/pixelFont.fnt", BitmapFont.class));
        ASSETS.add(TIMESSQUARE_FONT = new AssetDescriptor<>("fundamentals/fonts/timesSquare.fnt", BitmapFont.class));
        ASSETS.add(CIRCLE_SPRITE = new AssetDescriptor<>("fundamentals/images/circle.png", Sprite.class));
        ASSETS.add(GLOW_SPRITE = new AssetDescriptor<>("fundamentals/images/glow.png", Sprite.class));
        ASSETS.add(LIBGDX_SPRITE = new AssetDescriptor<>("fundamentals/images/libGDX.png", Sprite.class));
        ASSETS.add(PIXEL_SPRITE = new AssetDescriptor<>("fundamentals/images/pixel.png", Sprite.class));
        ASSETS.add(SQUARE32_SPRITE = new AssetDescriptor<>("fundamentals/images/square32.png", Sprite.class));
        ASSETS.add(SQUARE32INVERTED_SPRITE = new AssetDescriptor<>("fundamentals/images/square32Inverted.png", Sprite.class));
        ASSETS.add(TRIANGLE_SPRITE = new AssetDescriptor<>("fundamentals/images/triangle.png", Sprite.class));
        ASSETS.add(WEIRDSHAPE_SPRITE = new AssetDescriptor<>("fundamentals/images/weirdShape.png", Sprite.class));
    }
}
