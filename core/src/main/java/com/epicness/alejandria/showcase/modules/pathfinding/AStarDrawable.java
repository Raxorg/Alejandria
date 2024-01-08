package com.epicness.alejandria.showcase.modules.pathfinding;

import static com.epicness.alejandria.showcase.constants.AStarConstants.GRID_COLUMNS;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.pathfinding.PathfindingGrid;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;

public class AStarDrawable implements ModuleDrawable {

    private final PathfindingGrid grid;

    public AStarDrawable(Sprite cellSprite) {
        float cellSize = CAMERA_WIDTH / GRID_COLUMNS;
        grid = new PathfindingGrid(cellSprite, cellSize);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRendererPlus shapeRenderer) {
        grid.draw(spriteBatch);
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
    }

    public PathfindingGrid getGrid() {
        return grid;
    }
}