package com.epicness.alejandria.showcase.modules.optimization;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Rectangle;

import java.util.ArrayList;

public class QuadTreeDrawable implements ModuleDrawable {

    private final ShapeDrawerPlus shapeDrawer;
    private final ArrayList<Sprited> dots;
    private final DelayedRemovalArray<Rectangle> quads;

    public QuadTreeDrawable(ShapeDrawerPlus shapeDrawer, Sprite dotSprite) {
        this.shapeDrawer = shapeDrawer;

        dots = new ArrayList<>();
        Sprited dot = null;
        for (int i = 0; i < 10; i++) {
            dot = new Sprited(dotSprite);
            dot.setSize(25f);
            dots.add(dot);
        }
        dot.useBilinearFilter();

        quads = new DelayedRemovalArray<>();
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        for (int i = 0; i < quads.size; i++) {
            quads.get(i).draw(shapeDrawer);
        }
        for (int i = 0; i < dots.size(); i++) {
            dots.get(i).draw(spriteBatch);
        }
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {

    }

    public ArrayList<Sprited> getDots() {
        return dots;
    }

    public DelayedRemovalArray<Rectangle> getQuads() {
        return quads;
    }
}