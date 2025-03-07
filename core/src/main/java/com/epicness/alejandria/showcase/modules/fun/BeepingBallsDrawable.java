package com.epicness.alejandria.showcase.modules.fun;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.epicness.alejandria.showcase.constants.FunConstants.BALLS;
import static com.epicness.alejandria.showcase.constants.FunConstants.BALL_COLORS;
import static com.epicness.alejandria.showcase.constants.FunConstants.LINE_THICKNESS;
import static com.epicness.alejandria.showcase.constants.FunConstants.SPACING;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_WIDTH;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.alejandria.showcase.stuff.modules.fun.BeepingBall;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;

public class BeepingBallsDrawable implements ModuleDrawable {

    private final BeepingBall[] balls;

    public BeepingBallsDrawable(Sprite ballSprite, Sprite ballGlowSprite) {
        balls = new BeepingBall[BALLS];
        for (int i = 0; i < BALLS; i++) {
            balls[i] = new BeepingBall(ballGlowSprite, ballSprite);
            balls[i].setSize(60f);
            balls[i].setOriginCenter();
            balls[i].setBackgroundColor(BALL_COLORS[i % BALL_COLORS.length]);
            balls[i].setForegroundColor(BALL_COLORS[i % BALL_COLORS.length].cpy().lerp(WHITE, 0.5f));
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        ScreenUtils.clear(BLACK);

        spriteBatch.begin();

        shapeDrawer.line(
            100f, VIEWPORT_HALF_HEIGHT,
            100f + SPACING * BALLS, VIEWPORT_HALF_HEIGHT - SPACING * BALLS,
            LINE_THICKNESS
        );
        shapeDrawer.line(
            VIEWPORT_WIDTH - 100f, VIEWPORT_HALF_HEIGHT,
            VIEWPORT_WIDTH - 100f - SPACING * BALLS, VIEWPORT_HALF_HEIGHT - SPACING * BALLS,
            LINE_THICKNESS
        );

        for (int i = 0; i < BALLS - 1; i++) {
            BeepingBall ball = balls[i];
            BeepingBall nextBall = balls[i + 1];
            shapeDrawer.line(ball.getBackgroundCenter(), nextBall.getBackgroundCenter(), LINE_THICKNESS);
        }

        for (int i = 0; i < BALLS; i++) {
            balls[i].draw(spriteBatch);
        }
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeDrawerPlus shapeDrawer) {
        for (int i = 0; i < BALLS; i++) {
            balls[i].drawDebug(shapeDrawer);
        }
    }

    public BeepingBall[] getBalls() {
        return balls;
    }
}