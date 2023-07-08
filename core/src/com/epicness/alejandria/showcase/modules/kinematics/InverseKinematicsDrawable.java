package com.epicness.alejandria.showcase.modules.kinematics;

import static com.epicness.alejandria.showcase.constants.KinematicsConstants.IK_LINES;
import static com.epicness.alejandria.showcase.constants.KinematicsConstants.IK_LINE_LENGTH;
import static com.epicness.alejandria.showcase.constants.KinematicsConstants.IK_TENTACLES;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.epicness.fundamentals.renderer.ShapeBatch;
import com.epicness.fundamentals.stuff.interfaces.Drawable;
import com.epicness.fundamentals.stuff.shapes.Tentacle;
import com.epicness.fundamentals.utils.Random;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class InverseKinematicsDrawable implements Drawable {

    private final ShapeDrawer shapeDrawer;
    private final Tentacle[] tentacles;

    public InverseKinematicsDrawable(SpriteBatch spriteBatch, Sprite pixel) {
        shapeDrawer = new ShapeDrawer(spriteBatch, pixel);

        tentacles = new Tentacle[IK_TENTACLES];
        Array<Color> colors = Random.randomColors(IK_TENTACLES);
        float angleDelta = 360f / IK_TENTACLES;
        for (int i = 0; i < IK_TENTACLES; i++) {
            tentacles[i] = new Tentacle(IK_LINES, IK_LINE_LENGTH, 5f, 20f, colors.get(i), colors.get((i + 1) % IK_TENTACLES));
            float x = CAMERA_HALF_WIDTH + MathUtils.cosDeg(i * angleDelta) * 150f;
            float y = CAMERA_HALF_HEIGHT + MathUtils.sinDeg(i * angleDelta) * 150f;
            tentacles[i].setPosition(x, y);
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeBatch shapeBatch) {
        spriteBatch.begin();
        for (int i = 0; i < tentacles.length; i++) {
            tentacles[i].draw(shapeDrawer);
        }
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeBatch shapeBatch) {
    }

    public Tentacle[] getTentacles() {
        return tentacles;
    }
}