package com.epicness.alejandria.showcase.modules.masking;

import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_WIDTH;

import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Circle;

public class ShapeDrawerMasking extends Module<ShapeDrawerMaskingDrawable> {

    private boolean goingLeft;

    public ShapeDrawerMasking() {
        super(
            "Shape Drawer Masking",
            "Masks 4 triangles with a circle, all of them drawn by a ShapeDrawer and one has a gradient"
        );
    }

    @Override
    public ShapeDrawerMaskingDrawable setup() {
        return new ShapeDrawerMaskingDrawable();
    }

    @Override
    public void update(float delta) {
        Circle mask = drawable.getMask();
        if (mask.getCenterX() >= VIEWPORT_WIDTH - mask.radius) {
            goingLeft = true;
        } else if (mask.getCenterX() - mask.radius <= 0f) {
            goingLeft = false;
        }
        float translation = goingLeft ? -delta * 300f : delta * 300f;
        mask.translateX(translation);
    }
}