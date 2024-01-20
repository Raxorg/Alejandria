package com.epicness.alejandria.showcase.modules.fun;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.epicness.alejandria.showcase.constants.BeepingBallsConstants.BALLS;
import static com.epicness.alejandria.showcase.constants.BeepingBallsConstants.BALL_COLORS;
import static com.epicness.alejandria.showcase.constants.BeepingBallsConstants.SPACING;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.alejandria.showcase.stuff.modules.fun.BeepingBall;
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
    public void draw(SpriteBatch spriteBatch, ShapeRendererPlus shapeRenderer) {
        ScreenUtils.clear(BLACK);

        shapeRenderer.begin();
        shapeRenderer.line(100f, CAMERA_HALF_HEIGHT, 100f + SPACING * BALLS, CAMERA_HALF_HEIGHT - SPACING * BALLS);
        shapeRenderer.line(CAMERA_WIDTH - 100f, CAMERA_HALF_HEIGHT, CAMERA_WIDTH - 100f - SPACING * BALLS, CAMERA_HALF_HEIGHT - SPACING * BALLS);
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

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
        for (int i = 0; i < BALLS; i++) {
            balls[i].drawDebug(shapeRenderer);
        }
    }

    public BeepingBall[] getBalls() {
        return balls;
    }
}