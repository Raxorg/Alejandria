package com.epicness.alejandria.showcase.assets;

import static com.epicness.alejandria.showcase.assets.ShowcaseAssetPaths.*;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.epicness.fundamentals.assets.Assets;
import java.lang.Override;

public class ShowcaseAssets extends Assets {
    private Sprite stickmanRun;

    private Sprite[] stickmanRunFrames;

    private Sound ballBeep;

    private BitmapFont pixelFont;

    private Sprite gun;

    private Sprite arrow;

    private Sprite gitHub;

    private Sprite info;

    private Sprite circle;

    private Sprite circleGlow;

    private Sprite glow;

    private Sprite smiley_color;

    private Sprite smiley_outline;

    private ShaderProgram invert;

    private ShaderProgram neonWaves;

    private ShaderProgram raymarching;

    private ShaderProgram shake;

    private ShaderProgram vignette;

    public ShowcaseAssets() {
        super(ASSETS);
    }

    @Override
    public void initializeAssets() {
        stickmanRun = get(STICKMANRUN_SPRITE);
        stickmanRunFrames = get(STICKMANRUNFRAMES_ANIMATION);
        ballBeep = get(BALLBEEP_SOUND);
        pixelFont = get(PIXELFONT_FONT);
        gun = get(GUN_SPRITE);
        arrow = get(ARROW_SPRITE);
        gitHub = get(GITHUB_SPRITE);
        info = get(INFO_SPRITE);
        circle = get(CIRCLE_SPRITE);
        circleGlow = get(CIRCLEGLOW_SPRITE);
        glow = get(GLOW_SPRITE);
        smiley_color = get(SMILEY_COLOR_SPRITE);
        smiley_outline = get(SMILEY_OUTLINE_SPRITE);
        invert = get(INVERT_SHADER_PROGRAM);
        neonWaves = get(NEONWAVES_SHADER_PROGRAM);
        raymarching = get(RAYMARCHING_SHADER_PROGRAM);
        shake = get(SHAKE_SHADER_PROGRAM);
        vignette = get(VIGNETTE_SHADER_PROGRAM);
    }

    public Sprite getStickmanRun() {
        return stickmanRun;
    }

    public Sprite[] getStickmanRunFrames() {
        return stickmanRunFrames;
    }

    public Sound getBallBeep() {
        return ballBeep;
    }

    public BitmapFont getPixelFont() {
        return pixelFont;
    }

    public Sprite getGun() {
        return gun;
    }

    public Sprite getArrow() {
        return arrow;
    }

    public Sprite getGitHub() {
        return gitHub;
    }

    public Sprite getInfo() {
        return info;
    }

    public Sprite getCircle() {
        return circle;
    }

    public Sprite getCircleGlow() {
        return circleGlow;
    }

    public Sprite getGlow() {
        return glow;
    }

    public Sprite getSmiley_color() {
        return smiley_color;
    }

    public Sprite getSmiley_outline() {
        return smiley_outline;
    }

    public ShaderProgram getInvert() {
        return invert;
    }

    public ShaderProgram getNeonWaves() {
        return neonWaves;
    }

    public ShaderProgram getRaymarching() {
        return raymarching;
    }

    public ShaderProgram getShake() {
        return shake;
    }

    public ShaderProgram getVignette() {
        return vignette;
    }
}
