package com.epicness.alejandria.showcase.logic.modules.masking;

import static com.epicness.alejandria.showcase.constants.LayeredMaskingConstants.SHAPE_SIZE;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.Gdx;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.alejandria.showcase.stuff.modules.masking.LayeredMaskingDrawable;
import com.epicness.fundamentals.stuff.shapes.Circle;
import com.epicness.fundamentals.stuff.DualSprited;
import com.epicness.fundamentals.stuff.Sprited;

import java.util.List;

public class LayeredMasking extends Module {

    private LayeredMaskingDrawable drawable;

    public LayeredMasking() {
        super("Layered Masking");
    }

    @Override
    public Drawable setup() {
        Gdx.gl.glLineWidth(3f);

        return drawable = new LayeredMaskingDrawable(
                sharedAssets.getWeirdShape(),
                sharedAssets.getSquare(),
                sharedAssets.getSquareInverted(),
                sharedAssets.getPixel()
        );
    }

    @Override
    public void update(float delta) {
        Sprited mask = drawable.getMask();
        mask.rotate(delta * 5f);
        List<DualSprited> shapes = drawable.getShapes();
        for (int i = 0; i < shapes.size(); i++) {
            DualSprited shape = shapes.get(i);
            shape.translateY(delta * 30f);
            if (shape.getY() >= CAMERA_HEIGHT) {
                shape.setY(-SHAPE_SIZE);
            }
        }

        Circle circle1 = drawable.getCircle1();
        circle1.translateX(delta * 100f);
        if (circle1.getCenterX() - circle1.getRadius() >= CAMERA_WIDTH) {
            circle1.setX(-circle1.getRadius());
        }

        Circle circle2 = drawable.getCircle2();
        circle2.translateX(-delta * 100f);
        if (circle2.getCenterX() + circle2.getRadius() <= 0f) {
            circle2.setX(CAMERA_WIDTH + circle2.getRadius());
        }
    }

    @Override
    public void exit() {
        Gdx.gl.glLineWidth(1f);
        drawable = null;
    }
}