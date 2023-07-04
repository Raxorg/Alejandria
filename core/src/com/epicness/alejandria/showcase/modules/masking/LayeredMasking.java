package com.epicness.alejandria.showcase.modules.masking;

import static com.epicness.alejandria.showcase.constants.LayeredMaskingConstants.SHAPE_SIZE;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.Gdx;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.DualSprited;
import com.epicness.fundamentals.stuff.shapes.Circle;

import java.util.List;

public class LayeredMasking extends Module<LayeredMaskingDrawable> {

    public LayeredMasking() {
        super("Layered Masking", "Too much happening here to explain xD" +
                "\nCheck the source code");
    }

    @Override
    public LayeredMaskingDrawable setup() {
        Gdx.gl.glLineWidth(5f);

        return new LayeredMaskingDrawable(
                sharedAssets.getWeirdShape(),
                sharedAssets.getSquare32(),
                sharedAssets.getSquare32Inverted(),
                sharedAssets.getPixel()
        );
    }

    @Override
    public void update(float delta) {
        drawable.getMask().rotate(delta * 7f);

        List<DualSprited> shapes = drawable.getShapes();
        for (int i = 0; i < shapes.size(); i++) {
            DualSprited shape = shapes.get(i);
            shape.translateY(delta * 50f);
            if (shape.getY() >= CAMERA_HEIGHT) {
                shape.setY(-SHAPE_SIZE);
            }
        }

        Circle circle1 = drawable.getCircle1();
        circle1.translateX(delta * 150f);
        if (circle1.getCenterX() - circle1.radius >= CAMERA_WIDTH) {
            circle1.setX(-circle1.radius);
        }

        Circle circle2 = drawable.getCircle2();
        circle2.translateX(-delta * 150f);
        if (circle2.getCenterX() + circle2.radius <= 0f) {
            circle2.setX(CAMERA_WIDTH + circle2.radius);
        }
    }

    @Override
    public void exit() {
        Gdx.gl.glLineWidth(1f);
    }
}