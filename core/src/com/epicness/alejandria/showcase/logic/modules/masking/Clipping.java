package com.epicness.alejandria.showcase.logic.modules.masking;

import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.modules.masking.ClippingDrawable;

public class Clipping extends Module<ClippingDrawable> {

    public Clipping() {
        super("Clipping", "Sprites won't be rendered outside defined clipping rectangles");
    }

    @Override
    public ClippingDrawable setup() {
        return new ClippingDrawable(
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
}