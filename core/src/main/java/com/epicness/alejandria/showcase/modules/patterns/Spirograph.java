package com.epicness.alejandria.showcase.modules.patterns;

import static com.epicness.alejandria.showcase.constants.SpirographConstants.FADE_DURATION;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_WIDTH;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.alejandria.showcase.stuff.modules.animations.LinedBall;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Line;

public class Spirograph extends Module<SpirographDrawable> {

    private LinedBall[] linedBalls;
    private DelayedRemovalArray<Line> trailLines;
    // Optimization
    private Color auxColor;
    private float[] hsv;

    public Spirograph() {
        super("Spirograph", "A circle rotating around a circle rotating around the center");
    }

    @Override
    protected SpirographDrawable setup() {
        drawable = new SpirographDrawable(renderer.getShapeDrawer());
        linedBalls = drawable.getLinedBalls();
        trailLines = drawable.getTrailLines();
        auxColor = new Color();
        hsv = new float[3];

        linedBalls[0].setCenter(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);
        linedBalls[0].lastTrackedPosition.set(linedBalls[0].getEnd());
        for (int i = 1; i < linedBalls.length; i++) {
            linedBalls[i].setCenter(linedBalls[i - 1].getCenter().x + linedBalls[i - 1].getLength(), CAMERA_HALF_HEIGHT);
            linedBalls[i].lastTrackedPosition.set(linedBalls[i].getEnd());
        }
        trailLines.clear();

        return drawable;
    }

    @Override
    public void update(float delta) {
        updateColors(delta);
        moveCircles(delta);
        updateLineTimer(delta);
        fadeLines(delta);
    }

    private void updateColors(float delta) {
        for (int i = 0; i < linedBalls.length; i++) {
            auxColor.set(linedBalls[i].color.fromHsv(linedBalls[i].color.toHsv(hsv)[0] + delta * 2f % 360f, 1f, 1f));
            linedBalls[i].color.set(auxColor);
        }
    }

    private void moveCircles(float delta) {
        for (int i = 0; i < linedBalls.length; i++) {
            linedBalls[i].rotate(delta * linedBalls[i].speed);
            if (i == 0) continue;
            linedBalls[i].setCenter(linedBalls[i - 1].getEnd());
        }
    }

    private void updateLineTimer(float delta) {
        LinedBall ball = linedBalls[1];
        ball.timer += delta;
        if (ball.timer < 1f / ball.speed) return;

        trailLines.add(new Line(
            ball.lastTrackedPosition.x,
            ball.lastTrackedPosition.y,
            ball.getEnd().x,
            ball.getEnd().y,
            1f,
            ball.color.cpy()
        ));
        ball.lastTrackedPosition.set(ball.getEnd());
        ball.timer -= 1f / ball.speed;
    }

    private void fadeLines(float delta) {
        Color color;
        trailLines.begin();
        Line line;
        for (int i = 0; i < trailLines.size; i++) {
            line = trailLines.get(i);
            color = line.getColor();
            color.a -= delta / FADE_DURATION;
            line.setColor(color);
            if (color.a <= 0f) {
                trailLines.removeValue(line, true);
            }
        }
        trailLines.end();
    }
}