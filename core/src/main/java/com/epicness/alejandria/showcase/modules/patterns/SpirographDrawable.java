package com.epicness.alejandria.showcase.modules.patterns;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.epicness.alejandria.showcase.constants.SpirographConstants.BALL_RADIUS;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.alejandria.showcase.stuff.modules.animations.LinedBall;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Line;
import com.epicness.fundamentals.utils.Random;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class SpirographDrawable implements ModuleDrawable {

    private final ShapeDrawer shapeDrawer;
    private final LinedBall[] linedBalls;
    private final DelayedRemovalArray<Line> trailLines;

    public SpirographDrawable(ShapeDrawerPlus shapeDrawer) {
        this.shapeDrawer = shapeDrawer;
        linedBalls = new LinedBall[2];
        Color color = Random.rainbowColor().cpy();
        linedBalls[0] = new LinedBall(BALL_RADIUS, 200f, 280f, color);
        linedBalls[1] = new LinedBall(BALL_RADIUS, 200f, 3.5f * 280f + 7f, color);
        trailLines = new DelayedRemovalArray<>();
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRendererPlus shapeRenderer) {
        ScreenUtils.clear(BLACK);

        spriteBatch.begin();
        for (int i = 0; i < trailLines.size; i++) {
            trailLines.get(i).draw(shapeDrawer);
        }
        for (int i = 0; i < linedBalls.length; i++) {
            linedBalls[i].draw(shapeDrawer);
        }
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {

    }

    public LinedBall[] getLinedBalls() {
        return linedBalls;
    }

    public DelayedRemovalArray<Line> getTrailLines() {
        return trailLines;
    }
}