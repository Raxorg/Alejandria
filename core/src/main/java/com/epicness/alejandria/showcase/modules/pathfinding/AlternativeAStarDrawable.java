package com.epicness.alejandria.showcase.modules.pathfinding;

import static com.epicness.alejandria.showcase.constants.AStarConstants.CM_A_STAR_GRID_CELL_SIZE;
import static com.epicness.alejandria.showcase.constants.AStarConstants.CM_A_STAR_GRID_COLUMNS;
import static com.epicness.alejandria.showcase.constants.AStarConstants.CM_A_STAR_GRID_ROWS;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_STRIPE_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.DARK_DIRT;
import static com.epicness.fundamentals.utils.TextUtils.copyOf;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.alejandria.showcase.stuff.modules.pathfinding.AlternativeAStarGrid;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;

public class AlternativeAStarDrawable implements ModuleDrawable {

    private final AlternativeAStarGrid grid;

    public AlternativeAStarDrawable(Sprite square32, BitmapFont font) {
        font.getData().setScale(0.8f);
        BitmapFont smallerFont = copyOf(font);
        smallerFont.getData().setScale(0.4f);
        grid = new AlternativeAStarGrid(CM_A_STAR_GRID_COLUMNS, CM_A_STAR_GRID_ROWS, CM_A_STAR_GRID_CELL_SIZE, square32, font, smallerFont);
        grid.translateY(SHOWCASE_STRIPE_HEIGHT);
        for (int c = 0; c < grid.cols; c++) {
            for (int r = 0; r < grid.rows; r++) {
                grid.getCell(c, r).setColor(DARK_DIRT);
            }
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        grid.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
    }

    public AlternativeAStarGrid getGrid() {
        return grid;
    }
}