package com.epicness.alejandria.showcase.modules.masking;

import static com.epicness.alejandria.showcase.constants.MaskingConstants.SHAPE_SIZE;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_WIDTH;

import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.DualSprited;
import com.epicness.fundamentals.stuff.shapes.bidimensional.CirclePlus;

import java.util.List;

public class LayeredMasking extends Module<LayeredMaskingDrawable> {

    private CirclePlus circle1, circle2;

    public LayeredMasking() {
        super("Layered Masking", "Too much happening here to explain xD" +
            "\nCheck the source code");
    }

    @Override
    public LayeredMaskingDrawable setup() {
        drawable = new LayeredMaskingDrawable(
            sharedAssets.getWeirdShape(),
            sharedAssets.getSquare32(),
            sharedAssets.getSquare32Inverted(),
            sharedAssets.getPixel()
        );
        circle1 = drawable.getCircle1();
        circle2 = drawable.getCircle2();
        return drawable;
    }

    @Override
    public void update(float delta) {
        drawable.getMask().rotate(delta * 7f);

        List<DualSprited> shapes = drawable.getShapes();
        for (int i = 0; i < shapes.size(); i++) {
            DualSprited shape = shapes.get(i);
            shape.translateY(delta * 50f);
            if (shape.getY() >= VIEWPORT_HEIGHT) {
                shape.setY(-SHAPE_SIZE);
            }
        }

        circle1.translateX(delta * 150f);
        if (circle1.getX() >= VIEWPORT_WIDTH) {
            circle1.setX(-circle1.getWidth());
        }

        circle2.translateX(-delta * 150f);
        if (circle2.getEndX() <= 0f) {
            circle2.setX(VIEWPORT_WIDTH);
        }
    }
}