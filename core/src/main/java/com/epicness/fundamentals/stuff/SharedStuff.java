package com.epicness.fundamentals.stuff;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.NAVY;
import static com.epicness.fundamentals.constants.ColorConstants.BLACK_50;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;

import com.epicness.fundamentals.SharedScreen;
import com.epicness.fundamentals.assets.SharedAssets;

public class SharedStuff extends Stuff<SharedAssets> {

    // Structure
    private SharedAssets assets;
    private SharedScreen screen;
    // Stuff
    private AnimatedBackgroundDeluxe animatedBackground;
    private Sprited fader;

    @Override
    public void initializeStuff() {
        animatedBackground = new AnimatedBackgroundDeluxe(
            0, 0,
            CAMERA_WIDTH, CAMERA_HEIGHT,
            NAVY.cpy(),
            assets.getWeirdShape(),
            assets.getPixel(),
            screen.getStaticCamera(),
            24,
            12,
            20f
        );
        animatedBackground.setFollowBackgroundColor(false);
        animatedBackground.setSpriteColor(BLACK_50.cpy());

        fader = new Sprited(assets.getPixel());
        fader.setSize(CAMERA_WIDTH, CAMERA_HEIGHT);
        fader.setColor(BLACK.cpy());
    }

    // Stuff
    public AnimatedBackgroundDeluxe getAnimatedBackground() {
        return animatedBackground;
    }

    public Sprited getFader() {
        return fader;
    }

    // Structure
    public void setAssets(SharedAssets assets) {
        this.assets = assets;
    }

    public void setScreen(SharedScreen screen) {
        this.screen = screen;
    }
}