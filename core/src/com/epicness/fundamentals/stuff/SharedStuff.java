package com.epicness.fundamentals.stuff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.epicness.fundamentals.SharedScreen;
import com.epicness.fundamentals.assets.SharedAssets;

import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

public class SharedStuff extends Stuff {

    // Structure
    private SharedAssets assets;
    private SharedScreen screen;
    // Stuff
    private AnimatedBackground animatedBackground;
    private Sprite fader;

    @Override
    public void initializeStuff() {
        animatedBackground = new AnimatedBackgroundDeluxe(
                0, 0,
                CAMERA_WIDTH, CAMERA_HEIGHT,
                Color.NAVY,
                assets.getWeirdShape(),
                assets.getPixel(),
                screen.getStaticCamera(),
                15,
                4,
                20f
        );

        fader = new Sprite(assets.getPixel());
        fader.setSize(CAMERA_WIDTH, CAMERA_HEIGHT);
        fader.setColor(Color.BLACK);
    }

    // Stuff
    public AnimatedBackground getAnimatedBackground() {
        return animatedBackground;
    }

    public Sprite getFader() {
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