package com.epicness.alejandria.showcase.logic.modules.fun;

import static com.epicness.alejandria.showcase.constants.BeepingBallsConstants.BALLS;
import static com.epicness.alejandria.showcase.constants.BeepingBallsConstants.SPACING;
import static com.epicness.alejandria.showcase.constants.BeepingBallsConstants.VOLUME;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;
import static com.epicness.fundamentals.SharedConstants.CENTER_Y;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.modules.fun.BeepingBall;
import com.epicness.alejandria.showcase.stuff.modules.fun.BeepingBallsDrawable;

public class BeepingBalls extends Module<BeepingBallsDrawable> {

    public BeepingBalls() {
        super("Some cool beeping balls", "Some cool beeping balls");
    }

    @Override
    public BeepingBallsDrawable setup() {
        Gdx.gl.glLineWidth(3f);
        drawable = new BeepingBallsDrawable(assets.getCircle(), assets.getCircleGlow());
        BeepingBall[] balls = drawable.getBalls();
        for (int i = 0; i < BALLS; i++) {
            BeepingBall ball = balls[i];
            ball.startingX = 100f + i * SPACING;
            ball.startingY = CENTER_Y - i * SPACING;
            ball.finalX = CAMERA_WIDTH - 100f - i * SPACING;
            ball.setOriginBasedPosition(ball.startingX, ball.startingY);
        }
        return drawable;
    }

    @Override
    public void update(float delta) {
        BeepingBall[] balls = drawable.getBalls();
        for (int i = 0; i < BALLS; i++) {

            BeepingBall ball = balls[i];
            float pitch = MathUtils.map(0, BALLS - 1, 1f, 0.5f, i);
            float speedModifier = MathUtils.map(0, BALLS - 1, 1f, 0.75f, i);
            if (ball.forward) {
                ball.angle += delta * 180f * speedModifier;
                if (ball.angle > 180f) {
                    ball.angle = 180f - (ball.angle - 180f);
                    ball.forward = false;
                    assets.getBallBeep().play(VOLUME, pitch, 0f);
                }
            } else {
                ball.angle -= delta * 180f * speedModifier;
                if (ball.angle < 0f) {
                    ball.angle = -ball.angle;
                    ball.forward = true;
                    assets.getBallBeep().play(VOLUME, pitch, 0f);
                }
            }
            float x = MathUtils.map(0f, 180f, ball.startingX, ball.finalX, ball.angle);
            float y = MathUtils.sinDeg(ball.angle) * (200f - i * 6f);
            y += ball.startingY;
            balls[i].setOriginBasedPosition(x, y);
        }
    }

    @Override
    public void exit() {
        Gdx.gl.glLineWidth(1f);
    }
}