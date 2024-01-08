package com.epicness.fundamentals.stuff.shapes.bidimensional;

import static com.badlogic.gdx.graphics.Color.WHITE;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class Path {

    public final Array<Vector2> points;
    public final float thickness;
    public final Color color1, color2;

    public Path(float x1, float y1, float x2, float y2, Color startColor, Color endColor) {
        Vector2 start = new Vector2(x1, y1);
        Vector2 end = new Vector2(x2, y2);
        points = buildSegmentPoints(start, end);
        thickness = 1f;
        color1 = startColor;
        color2 = endColor;
    }

    public Path(float x1, float y1, float x2, float y2) {
        this(x1, y1, x2, y2, WHITE, WHITE);
    }

    protected Array<Vector2> buildSegmentPoints(Vector2 start, Vector2 end) {
        Array<Vector2> points = new Array<>();
        int pointAmount = (int) (start.dst(end) / 10f);
        for (int i = 0; i <= pointAmount; i++) {
            float t = MathUtils.map(0, pointAmount, 0f, 1f, i);
            points.add(start.cpy().lerp(end, t));
        }
        return points;
    }

    public void draw(ShapeDrawer shapeDrawer) {
        for (int i = 0; i < points.size; i++) {
            float progress = i / (points.size - 1f);
            shapeDrawer.filledCircle(points.get(i), thickness, color1.cpy().lerp(color2, progress));
        }
    }
}