package com.epicness.alejandria.showcase.logic.modules.masking;

import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.alejandria.showcase.stuff.modules.masking.ShapeDrawerMaskingDrawable;

public class ShapeDrawerMasking extends Module {

    public ShapeDrawerMasking() {
        super("Shape Drawer Masking", "Masks a shape drawn by shape drawer with another");
    }

    @Override
    public Drawable setup() {
        return new ShapeDrawerMaskingDrawable(renderer.getSpriteBatch(), sharedAssets.getPixel());
    }
}