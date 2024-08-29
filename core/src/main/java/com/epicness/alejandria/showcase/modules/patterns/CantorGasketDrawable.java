package com.epicness.alejandria.showcase.modules.patterns;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.RED;
import static com.epicness.alejandria.showcase.constants.PatternsConstants.CANTOR_COLORS;
import static com.epicness.alejandria.showcase.constants.PatternsConstants.CANTOR_STARTING_RECURSIONS;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_BUTTON_SIZE;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_STRIPE_HEIGHT;
import static com.epicness.fundamentals.constants.ColorConstants.PASTEL_RED;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Rectangle;

public class CantorGasketDrawable implements ModuleDrawable {

    private final Rectangle rect;
    private int recursions;

    public CantorGasketDrawable() {
        rect = new Rectangle(BLACK, RED.cpy());
        recursions = CANTOR_STARTING_RECURSIONS;
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();

        rect.set(SHOWCASE_BUTTON_SIZE, SHOWCASE_STRIPE_HEIGHT, SHOWCASE_SIZE, SHOWCASE_SIZE);
        rect.setFillColor(PASTEL_RED);
        rect.setThickness(6f);
        rect.draw(shapeDrawer);
        punchCantorGasket(rect.x, rect.y, rect.width, shapeDrawer, recursions);

        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
    }

    private void punchCantorGasket(float x, float y, float size, ShapeDrawerPlus shapeDrawer, int recursions) {
        if (recursions == 0) return;

        float newSize = size / 3f;
        rect.set(x + newSize, y + newSize, newSize, newSize);
        rect.setFillColor(CANTOR_COLORS[recursions % 6]);
        rect.setThickness(6f - (7f - recursions));
        rect.draw(shapeDrawer);

        for (int i = 0; i < 9; i++) {
            if (i == 4) continue;
            int z = i / 3;
            punchCantorGasket(i % 3f * newSize + x, z * newSize + y, newSize, shapeDrawer, recursions - 1);
        }
    }

    public void setRecursions(int recursions) {
        this.recursions = recursions;
    }
}