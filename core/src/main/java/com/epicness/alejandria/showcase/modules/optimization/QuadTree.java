package com.epicness.alejandria.showcase.modules.optimization;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.epicness.alejandria.showcase.constants.OptimizationConstants.COLOR_MAP;
import static com.epicness.alejandria.showcase.constants.OptimizationConstants.DOT_SIZE;
import static com.epicness.alejandria.showcase.constants.OptimizationConstants.INITIAL_SIZE;
import static com.epicness.alejandria.showcase.constants.OptimizationConstants.MARGIN;
import static com.epicness.alejandria.showcase.constants.OptimizationConstants.QUAD_THICKNESS;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_X;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_Y;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.SnapshotArray;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.SpritePlus;
import com.epicness.fundamentals.stuff.shapes.bidimensional.RectanglePlus;

import java.util.ArrayList;

public class QuadTree extends Module<QuadTreeDrawable> {

    private ArrayList<SpritePlus> dots;
    private DelayedRemovalArray<RectanglePlus> quads;
    private SnapshotArray<RectanglePlus> quadsToCheck;
    private DelayedRemovalArray<SpritePlus> dotsToCheck;
    // Memory optimization
    private Vector2 dotCenter;
    private Color color;
    private float[] hsv;

    public QuadTree() {
        super(
            "QuadTree",
            "Organizes entities into quads so you don't have to check collisions or distances " +
                "between all your game objects, only those in close proximity\n\n" +
                "Press space to randomize the example"
        );
    }

    @Override
    protected QuadTreeDrawable setup() {
        drawable = new QuadTreeDrawable(sharedAssets.getDot());
        dots = drawable.getDots();
        quads = drawable.getQuads();
        quadsToCheck = new SnapshotArray<>();
        dotsToCheck = new DelayedRemovalArray<>();
        dotCenter = new Vector2();
        color = new Color();
        hsv = new float[3];
        buildQuadTree();
        return drawable;
    }

    private void buildQuadTree() {
        quadsToCheck.clear();
        dotsToCheck.clear();
        randomizeDots();
        spawnInitialQuad();
    }

    private void randomizeDots() {
        SpritePlus dot;
        for (int i = 0; i < dots.size(); i++) {
            dot = dots.get(i);
            dot.setX(MathUtils.random(
                SHOWCASE_X + QUAD_THICKNESS * 0.5f + MARGIN,
                SHOWCASE_X + SHOWCASE_SIZE - DOT_SIZE - QUAD_THICKNESS * 0.5f - MARGIN));
            dot.setY(MathUtils.random(
                SHOWCASE_Y + QUAD_THICKNESS * 0.5f + MARGIN,
                SHOWCASE_Y + SHOWCASE_SIZE - DOT_SIZE - QUAD_THICKNESS * 0.5f - MARGIN));
            dotsToCheck.add(dot);
        }
    }

    private void spawnInitialQuad() {
        color.set(COLOR_MAP.get(INITIAL_SIZE));
        RectanglePlus quad = new RectanglePlus(color.cpy().lerp(BLACK, 0.4f), color.cpy());
        quad.setPosition(SHOWCASE_X + MARGIN, SHOWCASE_Y + MARGIN);
        quad.setSize(INITIAL_SIZE, INITIAL_SIZE);
        quads.add(quad);
        quadsToCheck.add(quad);
        checkQuads();
        quad.setPosition(SHOWCASE_X + QUAD_THICKNESS * 0.5f, SHOWCASE_Y + QUAD_THICKNESS * 0.5f);
        quad.setSize(SHOWCASE_SIZE - QUAD_THICKNESS, SHOWCASE_SIZE - QUAD_THICKNESS);
    }

    private void checkQuads() {
        RectanglePlus quad;
        SpritePlus dot;
        int count;
        quadsToCheck.begin();
        for (int i = 0; i < quadsToCheck.size; i++) {
            quad = quadsToCheck.get(i);
            count = 0;
            for (int j = 0; j < dotsToCheck.size; j++) {
                dot = dotsToCheck.get(j);
                if (quad.contains(dot.getCenter(dotCenter))) {
                    if (++count == 2) {
                        quadsToCheck.removeValue(quad, true);
                        divideQuad(quad);
                        break;
                    }
                }
            }
            quadsToCheck.removeValue(quad, true);
        }
        quadsToCheck.end();
        if (!quadsToCheck.isEmpty()) {
            checkQuads();
        }
    }

    private void divideQuad(RectanglePlus quad) {
        color.set(COLOR_MAP.get(quad.getWidth() * 0.5f));
        hsv = color.toHsv(hsv);
        hsv[1] = Math.min(1f, hsv[1] + 0.5f);
        hsv[2] = Math.min(0.75f, hsv[2] - 0.25f);
        Color borderColor = color.cpy().fromHsv(hsv);
        RectanglePlus[] newQuads = new RectanglePlus[4];
        for (int i = 0; i < 4; i++) {
            newQuads[i] = new RectanglePlus(borderColor, color.cpy());
            newQuads[i].setSize(quad.getWidth() * 0.5f);
            newQuads[i].setPosition(quad.getX(), quad.getY());
            newQuads[i].translateX((i % 2) * quad.getWidth() * 0.5f);
            newQuads[i].translateY(i >= 2 ? quad.getHeight() * 0.5f : 0);
        }
        quads.addAll(newQuads);
        quadsToCheck.addAll(newQuads);
    }

    @Override
    public void keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            buildQuadTree();
        }
    }
}