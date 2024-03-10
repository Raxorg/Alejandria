package com.epicness.alejandria.showcase.modules.patterns;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.epicness.alejandria.showcase.constants.PatternsConstants.CANTOR_RECURSIONS;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_BUTTON_SIZE;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_STRIPE_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.DARK_DIRT;
import static com.epicness.fundamentals.constants.SharedConstants.DIRT;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;

public class CantorGasketDrawable implements ModuleDrawable {

    private final Rectangle bounds;

    public CantorGasketDrawable() {
        bounds = new Rectangle(SHOWCASE_BUTTON_SIZE, SHOWCASE_STRIPE_HEIGHT, SHOWCASE_SIZE, SHOWCASE_SIZE);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        shapeRenderer.begin(Filled);
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height, DIRT);
        punchCantorGasket(bounds.x, bounds.y, bounds.width, shapeRenderer, CANTOR_RECURSIONS);
        shapeRenderer.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
    }

    private void punchCantorGasket(float x, float y, float size, ShapeRendererPlus shapeRenderer, int recursions) {
        if (recursions == 0) return;

        float newSize = size / 3f;
        shapeRenderer.rect(x + newSize, y + newSize, newSize, newSize, DARK_DIRT);

        for (int i = 0; i < 9; i++) {
            if (i == 4) continue;
            int z = i / 3;
            punchCantorGasket(i % 3f * newSize + x, z * newSize + y, newSize, shapeRenderer, recursions - 1);
        }
    }
}