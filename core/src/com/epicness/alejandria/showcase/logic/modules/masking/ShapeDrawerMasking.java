package com.epicness.alejandria.showcase.logic.modules.masking;

import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.modules.masking.ShapeDrawerMaskingDrawable;
import com.epicness.alejandria.showcase.stuff.modules.masking.helpers.SDCircle;

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
        return new ShapeDrawerMaskingDrawable(renderer.getSpriteBatch(), sharedAssets.getPixel());
    }

    @Override
    public void update(float delta) {
        SDCircle mask = drawable.getMask();
        if (mask.getCenterX() >= CAMERA_WIDTH - mask.getRadius()) {
            goingLeft = true;
        } else if (mask.getCenterX() - mask.getRadius() <= 0f) {
            goingLeft = false;
        }
        float translation = goingLeft ? -delta * 300f : delta * 300f;
        mask.translateX(translation);
    }
}