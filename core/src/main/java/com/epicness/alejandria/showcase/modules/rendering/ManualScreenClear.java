package com.epicness.alejandria.showcase.modules.rendering;

import static com.badlogic.gdx.Input.Keys.SPACE;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_BACKGROUND_COLOR;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_WIDTH;

import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.SpritePlus;

public class ManualScreenClear extends Module<ManualScreenClearDrawable> {

    private SpritePlus weirdShape;

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
        renderer.clearScreen = false;
        return drawable;
    }

    @Override
    public void update(float delta) {
        weirdShape.translateX(delta * 150f);
        if (weirdShape.getX() >= VIEWPORT_WIDTH) {
            weirdShape.setX(-weirdShape.getWidth());
        }
    }

    @Override
    public void resize(int width, int height) {
        ScreenUtils.clear(SHOWCASE_BACKGROUND_COLOR);
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