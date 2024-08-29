package com.epicness.alejandria.showcase.modules.kinematics;

import static com.epicness.alejandria.showcase.constants.KinematicsConstants.IK_LINES;
import static com.epicness.alejandria.showcase.constants.KinematicsConstants.IK_LINE_LENGTH;
import static com.epicness.alejandria.showcase.constants.KinematicsConstants.IK_TENTACLES;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Tentacle;
import com.epicness.fundamentals.utils.Random;

public class InverseKinematicsDrawable implements ModuleDrawable {

    private final Tentacle[] tentacles;

    public InverseKinematicsDrawable() {
        tentacles = new Tentacle[IK_TENTACLES];
        Array<Color> colors = Random.randomColors(IK_TENTACLES);
        for (int i = 0; i < IK_TENTACLES; i++) {
            tentacles[i] = new Tentacle(
                IK_LINES, IK_LINE_LENGTH,
                5f, 20f,
                colors.get(i), colors.get((i + 1) % IK_TENTACLES)
            );
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        for (int i = 0; i < tentacles.length; i++) {
            tentacles[i].draw(shapeDrawer);
        }
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
    }

    public Tentacle[] getTentacles() {
        return tentacles;
    }
}