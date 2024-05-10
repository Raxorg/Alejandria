package com.epicness.alejandria.showcase.modules.pathfinding;

import static com.epicness.alejandria.showcase.constants.AStarConstants.A_STAR_GRID_CELL_SIZE;
import static com.epicness.alejandria.showcase.constants.AStarConstants.A_STAR_GRID_COLUMNS;
import static com.epicness.alejandria.showcase.constants.AStarConstants.A_STAR_GRID_ROWS;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_STRIPE_HEIGHT;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.alejandria.showcase.stuff.modules.pathfinding.PathfindingGrid;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;

public class AStarDrawable implements ModuleDrawable {

    private final PathfindingGrid grid;

    public AStarDrawable(Sprite cellSprite) {
        grid = new PathfindingGrid(cellSprite, A_STAR_GRID_CELL_SIZE, A_STAR_GRID_COLUMNS, A_STAR_GRID_ROWS);
        grid.setY(SHOWCASE_STRIPE_HEIGHT);
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

    public PathfindingGrid getGrid() {
        return grid;
    }
}