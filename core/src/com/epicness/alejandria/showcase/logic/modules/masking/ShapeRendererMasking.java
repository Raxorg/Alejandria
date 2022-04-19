package com.epicness.alejandria.showcase.logic.modules.masking;

import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.alejandria.showcase.stuff.modules.masking.ShapeRendererMaskingDrawable;

public class ShapeRendererMasking extends Module {

    private ShapeRendererMaskingDrawable drawable;

    public ShapeRendererMasking() {
        super("Shape Renderer Masking");
    }

    @Override
    public Drawable setup() {
        return drawable = new ShapeRendererMaskingDrawable();
    }

    @Override
    public void update(float delta) {

    }
}