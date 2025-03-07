package com.epicness.alejandria.showcase.modules.masking;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.shapes.bidimensional.CirclePlus;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Triangle;

public class ShapeRendererMasking extends Module<ShapeRendererMaskingDrawable> {

    private boolean direction;

    public ShapeRendererMasking() {
        super(
            "Shape Renderer Masking",
            "The moving shapes are the masks that determine which parts of the circle get rendered"
        );
    }

    @Override
    public ShapeRendererMaskingDrawable setup() {
        Gdx.gl.glLineWidth(3f);
        return new ShapeRendererMaskingDrawable();
    }

    @Override
    public void update(float delta) {
        float translation = direction ? -delta * 300f : delta * 300f;

        Triangle triangleMask = drawable.getTriangleMask();
        triangleMask.translateX(translation);
        CirclePlus circleMask = drawable.getCircleMask();
        circleMask.translateX(-translation);
        if (triangleMask.getEndX() >= VIEWPORT_WIDTH) {
            direction = true;
        } else if (triangleMask.getX() <= 0f) {
            direction = false;
        }
    }

    @Override
    public void exit() {
        renderer.getShapeRenderer().setColor(WHITE);
        Gdx.gl.glLineWidth(1f);
        Gdx.gl.glDepthFunc(GL20.GL_LESS); // Important
    }
}