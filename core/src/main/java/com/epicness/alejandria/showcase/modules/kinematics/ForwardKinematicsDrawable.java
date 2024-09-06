package com.epicness.alejandria.showcase.modules.kinematics;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.RED;
import static com.epicness.alejandria.showcase.constants.KinematicsConstants.FK_LINES;
import static com.epicness.alejandria.showcase.constants.KinematicsConstants.FK_LINE_LENGTH;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_STRIPE_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.shapes.bidimensional.ConnectedLine;

public class ForwardKinematicsDrawable implements ModuleDrawable {

    private final ConnectedLine[] lines;

    public ForwardKinematicsDrawable() {
        lines = new ConnectedLine[FK_LINES];
        ConnectedLine root = new ConnectedLine(VIEWPORT_HALF_WIDTH, SHOWCASE_STRIPE_HEIGHT, FK_LINE_LENGTH, 90f, true);
        root.thickness = 15f;
        root.setColor(BLACK);
        lines[0] = root;
        for (int i = 1; i < FK_LINES; i++) {
            ConnectedLine connectedLine = new ConnectedLine(lines[i - 1], FK_LINE_LENGTH, 90f);
            connectedLine.setColor(BLACK.cpy().lerp(RED, i / (FK_LINES - 1f)));
            connectedLine.thickness = MathUtils.map(1, FK_LINES - 1, 15f, 3f, i);
            lines[i] = connectedLine;
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        for (int i = 0; i < lines.length; i++) {
            lines[i].draw(shapeDrawer);
        }
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
    }

    public ConnectedLine[] getLines() {
        return lines;
    }
}