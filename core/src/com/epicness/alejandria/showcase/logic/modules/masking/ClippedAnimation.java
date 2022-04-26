package com.epicness.alejandria.showcase.logic.modules.masking;

import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.alejandria.showcase.stuff.modules.masking.ClippedAnimationDrawable;

public class ClippedAnimation extends Module {

    private ClippedAnimationDrawable drawable;

    public ClippedAnimation() {
        super("Clipped Animation");
    }

    @Override
    public Drawable setup() {
        return drawable = new ClippedAnimationDrawable(
                sharedAssets.getPixel(),
                sharedAssets.getWeirdShape(),
                screen.getStaticCamera()
        );
    }

    @Override
    public void update(float delta) {
        drawable.getBackground1().update(delta);
        drawable.getBackground2().update(delta);
        drawable.getBackground3().update(delta);
    }

    @Override
    public void exit() {
        drawable = null;
    }
}