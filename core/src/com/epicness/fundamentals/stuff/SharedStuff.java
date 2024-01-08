package com.epicness.fundamentals.stuff;

import static com.epicness.fundamentals.constants.SharedConstants.BLACK_CLEAR_50;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.graphics.Color;
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
            Color.NAVY,
            assets.getWeirdShape(),
            assets.getPixel(),
            screen.getStaticCamera(),
            24,
            12,
            20f
        );
        animatedBackground.setFollowBackgroundColor(false);
        animatedBackground.setSpriteColor(BLACK_CLEAR_50);

        fader = new Sprited(assets.getPixel());
        fader.setSize(CAMERA_WIDTH, CAMERA_HEIGHT);
        fader.setColor(Color.BLACK);
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