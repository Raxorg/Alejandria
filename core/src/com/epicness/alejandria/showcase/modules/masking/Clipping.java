package com.epicness.alejandria.showcase.modules.masking;

import com.epicness.alejandria.showcase.logic.Module;

public class Clipping extends Module<ClippingModuleDrawable> {

    public Clipping() {
        super("Clipping", "Sprites won't be rendered outside of the defined clipping rectangles");
    }

    @Override
    public ClippingModuleDrawable setup() {
        return new ClippingModuleDrawable(
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