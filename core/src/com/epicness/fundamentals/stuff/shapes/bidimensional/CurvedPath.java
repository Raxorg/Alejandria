package com.epicness.fundamentals.stuff.shapes.bidimensional;

import static com.badlogic.gdx.graphics.Color.WHITE;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class CurvedPath extends Path {

    private final float curving;

    public CurvedPath(float x1, float y1, float x2, float y2, Color startColor, Color endColor, float curving) {
        super(x1, y1, x2, y2, startColor, endColor);
        this.curving = curving;
    }

    public CurvedPath(float x1, float y1, float x2, float y2) {
        this(x1, y1, x2, y2, WHITE, WHITE, 0f);
    }

    @Override
    protected Array<Vector2> buildSegmentPoints(Vector2 start, Vector2 end) {
        Array<Vector2> points = new Array<>();
        int pointAmount = (int) (start.dst(end) / 10f);

        Vector2 curver = start.cpy().lerp(end, 0.5f);
        if (Math.abs(start.x - end.x) > Math.abs(start.y - end.y)) {
            curver.y += curving;
        } else {
            curver.x += curving;
        }
        Bezier<Vector2> curve = new Bezier<>(start, curver, end);

        for (int i = 0; i <= pointAmount; i++) {
            float t = MathUtils.map(0, pointAmount, 0f, 1f, i);
            points.add(curve.valueAt(new Vector2(), t));
        }
        return points;
    }
}