package com.epicness.alejandria.showcase.modules.fun;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.epicness.alejandria.showcase.constants.BeepingBallsConstants.BALLS;
import static com.epicness.alejandria.showcase.constants.BeepingBallsConstants.BALL_COLORS;
import static com.epicness.alejandria.showcase.constants.BeepingBallsConstants.SPACING;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.stuff.modules.fun.BeepingBall;
import com.epicness.fundamentals.renderer.ShapeBatch;
import com.epicness.fundamentals.stuff.interfaces.Drawable;

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
    public void draw(SpriteBatch spriteBatch, ShapeBatch shapeBatch) {
        ScreenUtils.clear(BLACK);

        shapeBatch.begin();
        shapeBatch.line(100f, CAMERA_HALF_HEIGHT, 100f + SPACING * BALLS, CAMERA_HALF_HEIGHT - SPACING * BALLS);
        shapeBatch.line(CAMERA_WIDTH - 100f, CAMERA_HALF_HEIGHT, CAMERA_WIDTH - 100f - SPACING * BALLS, CAMERA_HALF_HEIGHT - SPACING * BALLS);
        for (int i = 0; i < BALLS; i++) {
            if (i >= BALLS - 1) {
                continue;
            }
            BeepingBall ball = balls[i];
            BeepingBall nextBall = balls[i + 1];
            shapeBatch.line(ball.getBackgroundCenter(), nextBall.getBackgroundCenter());
        }
        shapeBatch.end();

        spriteBatch.begin();
        for (int i = 0; i < BALLS; i++) {
            balls[i].draw(spriteBatch);
        }
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeBatch shapeBatch) {
        shapeBatch.begin();
        for (int i = 0; i < BALLS; i++) {
            balls[i].drawDebug(shapeBatch);
        }
        shapeBatch.end();
    }

    public BeepingBall[] getBalls() {
        return balls;
    }
}