package com.epicness.alejandria.showcase.logic.modules.masking;

import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.modules.masking.AnimatedBackgroundsDrawable;

public class AnimatedBackgrounds extends Module {

    private AnimatedBackgroundsDrawable drawable;

    @Override
    public void setup() {
        drawable = new AnimatedBackgroundsDrawable(
                sharedAssets.getPixel(),
                sharedAssets.getWeirdShape(),
                screen.getStaticCamera()
        );
        stuff.getShowcase().setDrawable(drawable);
    }

    @Override
    public void update(float delta) {
        drawable.getBackground1().update(delta);
        drawable.getBackground2().update(delta);
        drawable.getBackground3().update(delta);
    }
}