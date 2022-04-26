package com.epicness.alejandria.showcase.stuff.modules.pathfinding;

import static com.epicness.alejandria.showcase.constants.AStarConstants.GRID_COLUMNS;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.alejandria.showcase.stuff.modules.pathfinding.helpers.PathfindingGrid;

public class AStarDrawable implements Drawable {

    private final PathfindingGrid grid;

    public AStarDrawable(Sprite cellSprite) {
        float cellSize = CAMERA_WIDTH / GRID_COLUMNS;
        grid = new PathfindingGrid(cellSprite, cellSize);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        grid.draw(spriteBatch);
    }

    public PathfindingGrid getGrid() {
        return grid;
    }
}