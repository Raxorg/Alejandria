package com.epicness.alejandria.showcase.modules.rendering;

import static com.badlogic.gdx.Input.Keys.SPACE;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.Gdx;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.Sprited;

public class ManualScreenClear extends Module<ManualScreenClearDrawable> {

    private Sprited weirdShape;

    public ManualScreenClear() {
        super(
            "Manual Screen Clear",
            "Press space to toggle screen clearing\n\n" +
                "Requires preserveDrawingBuffer config set to true for web projects"
        );
    }

    @Override
    protected ManualScreenClearDrawable setup() {
        drawable = new ManualScreenClearDrawable(sharedAssets.getWeirdShape());
        weirdShape = drawable.getWeirdShape();
        Gdx.app.postRunnable(() -> renderer.clearScreen = false);
        return drawable;
    }

    @Override
    public void update(float delta) {
        weirdShape.translateX(delta * 150f);
        if (weirdShape.getX() >= CAMERA_WIDTH) {
            weirdShape.setX(-weirdShape.getWidth());
        }
    }

    @Override
    protected void exit() {
        renderer.clearScreen = true;
    }

    @Override
    public void keyDown(int keycode) {
        if (keycode == SPACE) renderer.clearScreen = !renderer.clearScreen;
    }
}