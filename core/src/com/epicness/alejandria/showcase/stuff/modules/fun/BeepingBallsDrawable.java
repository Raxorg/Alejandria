package com.epicness.alejandria.showcase.stuff.modules.fun;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.epicness.alejandria.showcase.constants.BeepingBallsConstants.BALLS;
import static com.epicness.alejandria.showcase.constants.BeepingBallsConstants.BALL_COLORS;
import static com.epicness.alejandria.showcase.constants.BeepingBallsConstants.SPACING;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;
import static com.epicness.fundamentals.SharedConstants.CENTER_Y;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.stuff.Drawable;

public class BeepingBallsDrawable implements Drawable {

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
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        ScreenUtils.clear(BLACK.cpy().lerp(WHITE, 0.1f));

        shapeRenderer.begin();
        shapeRenderer.line(100f, CENTER_Y, 100f + SPACING * BALLS, CENTER_Y - SPACING * BALLS);
        shapeRenderer.line(CAMERA_WIDTH - 100f, CENTER_Y, CAMERA_WIDTH - 100f - SPACING * BALLS, CENTER_Y - SPACING * BALLS);
        for (int i = 0; i < BALLS; i++) {
            if (i >= BALLS - 1) {
                continue;
            }
            BeepingBall ball = balls[i];
            BeepingBall nextBall = balls[i + 1];
            shapeRenderer.line(ball.getBackgroundCenter(), nextBall.getBackgroundCenter());
        }
        shapeRenderer.end();

        spriteBatch.begin();
        for (int i = 0; i < BALLS; i++) {
            balls[i].draw(spriteBatch);
        }
        spriteBatch.end();
    }

    public BeepingBall[] getBalls() {
        return balls;
    }
}