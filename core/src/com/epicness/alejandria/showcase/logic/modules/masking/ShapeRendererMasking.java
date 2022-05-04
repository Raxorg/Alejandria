package com.epicness.alejandria.showcase.logic.modules.masking;

import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.Gdx;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.alejandria.showcase.stuff.modules.masking.ShapeRendererMaskingDrawable;
import com.epicness.fundamentals.stuff.shapes.Circle;
import com.epicness.fundamentals.stuff.shapes.Triangle;

public class ShapeRendererMasking extends Module {

    private ShapeRendererMaskingDrawable drawable;
    // Logic
    private boolean direction;

    public ShapeRendererMasking() {
        super(
                "Shape Renderer Masking",
                "The moving shapes are the masks that determine which parts get rendered"
        );
    }

    @Override
    public Drawable setup() {
        Gdx.gl.glLineWidth(3f);
        drawable = new ShapeRendererMaskingDrawable();
        return drawable;
    }

    @Override
    public void update(float delta) {
        float translation = direction ? -delta * 300f : delta * 300f;

        Triangle triangleMask = drawable.getTriangleMask();
        triangleMask.translateX(translation);
        Circle circleMask = drawable.getCircleMask();
        circleMask.translateX(-translation);
        if (triangleMask.getEndX() >= CAMERA_WIDTH) {
            direction = true;
        } else if (triangleMask.getX() <= 0f) {
            direction = false;
        }
    }

    @Override
    public void exit() {
        drawable = null;
        Gdx.gl.glLineWidth(1f);
    }
}