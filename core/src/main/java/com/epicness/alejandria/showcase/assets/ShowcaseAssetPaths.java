package com.epicness.alejandria.showcase.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import java.util.ArrayList;
import java.util.List;

public class ShowcaseAssetPaths {
    static final List<AssetDescriptor<?>> ASSETS;

    public static final AssetDescriptor<Sprite> STICKMANRUN_SPRITE;

    public static final AssetDescriptor<Sprite[]> STICKMANRUNFRAMES_ANIMATION;

    public static final AssetDescriptor<Sound> BALLBEEP_SOUND;

    public static final AssetDescriptor<Sound> LOUDTHUNDER_SOUND;

    public static final AssetDescriptor<Sprite> GUN_SPRITE;

    public static final AssetDescriptor<Sprite> ARROW_SPRITE;

    public static final AssetDescriptor<Sprite> GITHUB_SPRITE;

    public static final AssetDescriptor<Sprite> INFO_SPRITE;

    public static final AssetDescriptor<Sprite> BEAMEND_SPRITE;

    public static final AssetDescriptor<Sprite> BEAMENDTOP_SPRITE;

    public static final AssetDescriptor<Sprite> BEAMMID_SPRITE;

    public static final AssetDescriptor<Sprite> BEAMMIDTOP_SPRITE;

    public static final AssetDescriptor<Sprite> BEAMSTART_SPRITE;

    public static final AssetDescriptor<Sprite> BEAMSTARTTOP_SPRITE;

    public static final AssetDescriptor<Sprite> CIRCLE_SPRITE;

    public static final AssetDescriptor<Sprite> CIRCLEGLOW_SPRITE;

    public static final AssetDescriptor<Sprite> ROUNDEDSQUARE_SPRITE;

    public static final AssetDescriptor<Sprite> GLOW_SPRITE;

    public static final AssetDescriptor<Sprite> SMILEY_COLOR_SPRITE;

    public static final AssetDescriptor<Sprite> SMILEY_OUTLINE_SPRITE;

    public static final AssetDescriptor<Sprite> BOLTSEGMENT_SPRITE;

    public static final AssetDescriptor<ShaderProgram> INVERT_SHADER_PROGRAM;

    public static final AssetDescriptor<ShaderProgram> NEONWAVES_SHADER_PROGRAM;

    public static final AssetDescriptor<ShaderProgram> RAYMARCHING_SHADER_PROGRAM;

    public static final AssetDescriptor<ShaderProgram> SHAKE_SHADER_PROGRAM;

    public static final AssetDescriptor<ShaderProgram> VIGNETTE_SHADER_PROGRAM;

    static {
        ASSETS = new ArrayList<>();
        ASSETS.add(STICKMANRUN_SPRITE = new AssetDescriptor<>("alejandria/showcase/animations/stickmanRun.png", Sprite.class));
        ASSETS.add(STICKMANRUNFRAMES_ANIMATION = new AssetDescriptor<>("alejandria/showcase/animations/stickmanRunFrames.anim", Sprite[].class));
        ASSETS.add(BALLBEEP_SOUND = new AssetDescriptor<>("alejandria/showcase/audios/ballBeep.sogg", Sound.class));
        ASSETS.add(LOUDTHUNDER_SOUND = new AssetDescriptor<>("alejandria/showcase/audios/loudThunder.sogg", Sound.class));
        ASSETS.add(GUN_SPRITE = new AssetDescriptor<>("alejandria/showcase/images/bullets/gun.png", Sprite.class));
        ASSETS.add(ARROW_SPRITE = new AssetDescriptor<>("alejandria/showcase/images/buttons/arrow.png", Sprite.class));
        ASSETS.add(GITHUB_SPRITE = new AssetDescriptor<>("alejandria/showcase/images/buttons/gitHub.png", Sprite.class));
        ASSETS.add(INFO_SPRITE = new AssetDescriptor<>("alejandria/showcase/images/buttons/info.png", Sprite.class));
        ASSETS.add(BEAMEND_SPRITE = new AssetDescriptor<>("alejandria/showcase/images/fun/beamEnd.png", Sprite.class));
        ASSETS.add(BEAMENDTOP_SPRITE = new AssetDescriptor<>("alejandria/showcase/images/fun/beamEndTop.png", Sprite.class));
        ASSETS.add(BEAMMID_SPRITE = new AssetDescriptor<>("alejandria/showcase/images/fun/beamMid.png", Sprite.class));
        ASSETS.add(BEAMMIDTOP_SPRITE = new AssetDescriptor<>("alejandria/showcase/images/fun/beamMidTop.png", Sprite.class));
        ASSETS.add(BEAMSTART_SPRITE = new AssetDescriptor<>("alejandria/showcase/images/fun/beamStart.png", Sprite.class));
        ASSETS.add(BEAMSTARTTOP_SPRITE = new AssetDescriptor<>("alejandria/showcase/images/fun/beamStartTop.png", Sprite.class));
        ASSETS.add(CIRCLE_SPRITE = new AssetDescriptor<>("alejandria/showcase/images/fun/circle.png", Sprite.class));
        ASSETS.add(CIRCLEGLOW_SPRITE = new AssetDescriptor<>("alejandria/showcase/images/fun/circleGlow.png", Sprite.class));
        ASSETS.add(ROUNDEDSQUARE_SPRITE = new AssetDescriptor<>("alejandria/showcase/images/fun/roundedSquare.png", Sprite.class));
        ASSETS.add(GLOW_SPRITE = new AssetDescriptor<>("alejandria/showcase/images/masking/glow.png", Sprite.class));
        ASSETS.add(SMILEY_COLOR_SPRITE = new AssetDescriptor<>("alejandria/showcase/images/masking/smiley_color.png", Sprite.class));
        ASSETS.add(SMILEY_OUTLINE_SPRITE = new AssetDescriptor<>("alejandria/showcase/images/masking/smiley_outline.png", Sprite.class));
        ASSETS.add(BOLTSEGMENT_SPRITE = new AssetDescriptor<>("alejandria/showcase/images/patterns/boltSegment.png", Sprite.class));
        ASSETS.add(INVERT_SHADER_PROGRAM = new AssetDescriptor<>("alejandria/showcase/shaders/invert.sp", ShaderProgram.class));
        ASSETS.add(NEONWAVES_SHADER_PROGRAM = new AssetDescriptor<>("alejandria/showcase/shaders/neonwaves/neonWaves.sp", ShaderProgram.class));
        ASSETS.add(RAYMARCHING_SHADER_PROGRAM = new AssetDescriptor<>("alejandria/showcase/shaders/raymarching/raymarching.sp", ShaderProgram.class));
        ASSETS.add(SHAKE_SHADER_PROGRAM = new AssetDescriptor<>("alejandria/showcase/shaders/shake.sp", ShaderProgram.class));
        ASSETS.add(VIGNETTE_SHADER_PROGRAM = new AssetDescriptor<>("alejandria/showcase/shaders/vignette.sp", ShaderProgram.class));
    }
}