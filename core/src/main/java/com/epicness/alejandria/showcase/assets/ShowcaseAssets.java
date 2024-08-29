package com.epicness.alejandria.showcase.assets;

import static com.epicness.alejandria.showcase.assets.ShowcaseAssetPaths.*;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.epicness.fundamentals.assets.Assets;

public class ShowcaseAssets extends Assets {
    private Sprite stickmanRun;

    private Sprite[] stickmanRunFrames;

    private Sound ballBeep;

    private Sound loudThunder;

    private Sprite gun;

    private Sprite arrow;

    private Sprite gitHub;

    private Sprite info;

    private Sprite beamEnd;

    private Sprite beamEndTop;

    private Sprite beamMid;

    private Sprite beamMidTop;

    private Sprite beamStart;

    private Sprite beamStartTop;

    private Sprite circle;

    private Sprite circleGlow;

    private Sprite roundedSquare;

    private Sprite glow;

    private Sprite smiley_color;

    private Sprite smiley_outline;

    private Sprite boltSegment;

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
        loudThunder = get(LOUDTHUNDER_SOUND);
        gun = get(GUN_SPRITE);
        arrow = get(ARROW_SPRITE);
        gitHub = get(GITHUB_SPRITE);
        info = get(INFO_SPRITE);
        beamEnd = get(BEAMEND_SPRITE);
        beamEndTop = get(BEAMENDTOP_SPRITE);
        beamMid = get(BEAMMID_SPRITE);
        beamMidTop = get(BEAMMIDTOP_SPRITE);
        beamStart = get(BEAMSTART_SPRITE);
        beamStartTop = get(BEAMSTARTTOP_SPRITE);
        circle = get(CIRCLE_SPRITE);
        circleGlow = get(CIRCLEGLOW_SPRITE);
        roundedSquare = get(ROUNDEDSQUARE_SPRITE);
        glow = get(GLOW_SPRITE);
        smiley_color = get(SMILEY_COLOR_SPRITE);
        smiley_outline = get(SMILEY_OUTLINE_SPRITE);
        boltSegment = get(BOLTSEGMENT_SPRITE);
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

    public Sound getLoudThunder() {
        return loudThunder;
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

    public Sprite getBeamEnd() {
        return beamEnd;
    }

    public Sprite getBeamEndTop() {
        return beamEndTop;
    }

    public Sprite getBeamMid() {
        return beamMid;
    }

    public Sprite getBeamMidTop() {
        return beamMidTop;
    }

    public Sprite getBeamStart() {
        return beamStart;
    }

    public Sprite getBeamStartTop() {
        return beamStartTop;
    }

    public Sprite getCircle() {
        return circle;
    }

    public Sprite getCircleGlow() {
        return circleGlow;
    }

    public Sprite getRoundedSquare() {
        return roundedSquare;
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

    public Sprite getBoltSegment() {
        return boltSegment;
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