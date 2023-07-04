package com.epicness.alejandria.showcase.modules.kinematics;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.RED;
import static com.epicness.alejandria.showcase.constants.KinematicsConstants.FK_LINES;
import static com.epicness.alejandria.showcase.constants.KinematicsConstants.FK_LINE_LENGTH;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.STRIPE_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.stuff.shapes.ConnectedLine;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class ForwardKinematicsDrawable implements Drawable {

    private final ShapeDrawer shapeDrawer;
    private final ConnectedLine[] lines;

    public ForwardKinematicsDrawable(SpriteBatch spriteBatch, Sprite pixel) {
        shapeDrawer = new ShapeDrawer(spriteBatch, pixel);

        lines = new ConnectedLine[FK_LINES];
        ConnectedLine root = new ConnectedLine(CAMERA_HALF_WIDTH, STRIPE_HEIGHT, FK_LINE_LENGTH, 90f, true);
        root.width = 15f;
        root.setColor(BLACK);
        lines[0] = root;
        for (int i = 1; i < FK_LINES; i++) {
            ConnectedLine connectedLine = new ConnectedLine(lines[i - 1], FK_LINE_LENGTH, 90f);
            connectedLine.setColor(BLACK.cpy().lerp(RED, i / (FK_LINES - 1f)));
            connectedLine.width = MathUtils.map(1, FK_LINES - 1, 15f, 3f, i);
            lines[i] = connectedLine;
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        spriteBatch.begin();
        for (int i = 0; i < lines.length; i++) {
            lines[i].draw(shapeDrawer);
        }
        spriteBatch.end();
    }

    public ConnectedLine[] getLines() {
        return lines;
    }
}