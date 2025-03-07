package com.epicness.alejandria.showcase.modules.patterns;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.epicness.alejandria.showcase.constants.PatternsConstants.DRAGON_RECURSIONS;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_HALF_SIZE;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;

import java.util.Collections;
import java.util.LinkedList;

public class DragonCurveDrawable implements ModuleDrawable {

    private final Vector2 head, heading;
    private final float[] dragonCurve;
    private int offset;

    public DragonCurveDrawable() {
        head = new Vector2();
        heading = new Vector2();
        dragonCurve = generateDragonCurve();
        offset = dragonCurve.length;
    }

    private float[] generateDragonCurve() {
        LinkedList<Boolean> turns = dragonTurns();

        head.set(SHOWCASE_HALF_SIZE + 10f, SHOWCASE_HALF_SIZE - 170f);
        heading.set(20f, 0);

        float[] curve = new float[(turns.size() + 1) * 2];

        int i = 0;
        curve[curve.length - 1 - i++] = head.x;
        curve[curve.length - 1 - i++] = head.y;

        for (Boolean turn : turns) {
            heading.set(turn(heading, turn));
            head.x += heading.x;
            head.y += heading.y;
            curve[curve.length - 1 - i++] = head.x;
            curve[curve.length - 1 - i++] = head.y;
        }

        return curve;
    }

    private LinkedList<Boolean> dragonTurns() {
        LinkedList<Boolean> turns = new LinkedList<>();
        turns.add(true);

        for (int i = 0; i < DRAGON_RECURSIONS; i++) {
            // Create a reversed copy of turns
            LinkedList<Boolean> reversed = new LinkedList<>(turns);
            Collections.reverse(reversed);

            // Add a left turn to turns
            turns.add(true);

            // Add reflected version of reversed to turns
            for (boolean turn : reversed) {
                turns.add(!turn);
            }
        }
        return turns;
    }

    @SuppressWarnings("SuspiciousNameCombination")
    private Vector2 turn(Vector2 heading, boolean turnLeft) {
        Vector2 newHeading = new Vector2();
        if (turnLeft) {
            newHeading.x = -heading.y;
            newHeading.y = heading.x;
        } else {
            newHeading.x = heading.y;
            newHeading.y = -heading.x;
        }
        return newHeading;
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        ScreenUtils.clear(BLACK);
        spriteBatch.begin();
        shapeDrawer.path(dragonCurve, offset, dragonCurve.length, 2f, true);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeDrawerPlus shapeDrawer) {
    }

    public float[] getDragonCurve() {
        return dragonCurve;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}