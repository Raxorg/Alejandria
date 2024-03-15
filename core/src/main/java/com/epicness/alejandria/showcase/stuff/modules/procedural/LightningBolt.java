package com.epicness.alejandria.showcase.stuff.modules.procedural;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class LightningBolt {

    private final NinePatch ninePatch;
    private final Array<Vector2> points;
    private final Vector2 auxVector;

    public LightningBolt(Sprite boltSegment, Vector2 from, Vector2 to) {
        ninePatch = new NinePatch(boltSegment, 31, 31, 31, 31);
        points = new Array<>();
        auxVector = new Vector2();
        setup(from, to);
    }

    public LightningBolt(Sprite boltSegment, float x1, float y1, float x2, float y2) {
        this(boltSegment, new Vector2(x1, y1), new Vector2(x2, y2));
    }

    private void setup(Vector2 from, Vector2 to) {
        auxVector.set(to).sub(from);
        float len = auxVector.len();
        //noinspection SuspiciousNameCombination
        Vector2 normal = new Vector2(auxVector.y, -auxVector.x).nor();

        Array<Float> fractions = new Array<>();
        for (int i = 0; i < len / 6; i++) {
            fractions.add(MathUtils.random());
        }
        fractions.sort();


        points.clear();
        Vector2 offset = new Vector2();
        float sway = 60;
        float jaggedness = 1f / sway;
        float prevDisplacement = 0;

        points.add(from);
        for (int i = 1; i < fractions.size; i++) {
            float fraction = fractions.get(i);

            float scale = (len * jaggedness) * (fraction - fractions.get(i - 1));
            // defines an envelope. Points near the middle of the bolt can be further from the central line.
            float envelope = fraction > 0.9f ? 10 * (1 - fraction) : 1;

            float displacement = MathUtils.random(-sway, sway);
            displacement -= (displacement - prevDisplacement) * (1 - scale);
            displacement *= envelope;
            prevDisplacement = displacement;

            Vector2 point = new Vector2();
            point.set(auxVector).scl(fraction);
            offset.set(normal).scl(displacement);
            point.add(offset);
            point.add(from);
            points.add(point);
        }
        points.add(to);
    }

    public void draw(SpriteBatch spriteBatch) {
        for (int i = 0; i < points.size - 1; i++) {
            Vector2 from = points.get(i);
            Vector2 to = points.get(i + 1);
            drawSegment(spriteBatch, from, to);
        }
    }

    private void drawSegment(SpriteBatch spriteBatch, Vector2 from, Vector2 to) {
        auxVector.set(to).sub(from);
        float len = auxVector.len();
        float angle = auxVector.angleDeg();

        ninePatch.draw(spriteBatch, from.x, from.y, 31, 31, 64 + len - 3, 64, 1, 1, angle);
    }

    public Vector2 getPoint(float fraction) {
        return points.get((int) (fraction * points.size));
    }

    public Color getColor() {
        return ninePatch.getColor();
    }

    public void setColor(Color color) {
        ninePatch.setColor(color);
    }
}