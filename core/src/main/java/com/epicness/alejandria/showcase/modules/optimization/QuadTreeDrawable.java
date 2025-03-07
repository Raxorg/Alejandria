package com.epicness.alejandria.showcase.modules.optimization;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.SpritePlus;
import com.epicness.fundamentals.stuff.shapes.bidimensional.RectanglePlus;

import java.util.ArrayList;

public class QuadTreeDrawable implements ModuleDrawable {

    private final ArrayList<SpritePlus> dots;
    private final DelayedRemovalArray<RectanglePlus> quads;

    public QuadTreeDrawable(Sprite dotSprite) {
        dots = new ArrayList<>();
        SpritePlus dot = null;
        for (int i = 0; i < 10; i++) {
            dot = new SpritePlus(dotSprite);
            dot.setSize(25f);
            dots.add(dot);
        }
        dot.useBilinearFilter();

        quads = new DelayedRemovalArray<>();
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
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
    public void drawDebug(ShapeDrawerPlus shapeDrawer) {

    }

    public ArrayList<SpritePlus> getDots() {
        return dots;
    }

    public DelayedRemovalArray<RectanglePlus> getQuads() {
        return quads;
    }
}