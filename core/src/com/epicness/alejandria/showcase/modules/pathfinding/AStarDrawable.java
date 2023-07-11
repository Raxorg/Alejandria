package com.epicness.alejandria.showcase.modules.pathfinding;

import static com.epicness.alejandria.showcase.constants.AStarConstants.GRID_COLUMNS;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.pathfinding.PathfindingGrid;
import com.epicness.fundamentals.renderer.ShapeBatch;
import com.epicness.fundamentals.stuff.interfaces.Drawable;

public class AStarDrawable implements Drawable {

    private final PathfindingGrid grid;

    public AStarDrawable(Sprite cellSprite) {
        float cellSize = CAMERA_WIDTH / GRID_COLUMNS;
        grid = new PathfindingGrid(cellSprite, cellSize);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeBatch shapeBatch) {
        grid.draw(spriteBatch);
    }

    @Override
    public void drawDebug(ShapeBatch shapeBatch) {
    }

    public PathfindingGrid getGrid() {
        return grid;
    }
}