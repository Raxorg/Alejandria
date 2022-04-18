package com.epicness.alejandria.showcase.stuff.modules.pathfinding;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.alejandria.showcase.stuff.modules.pathfinding.helpers.PathfindingGrid;

public class AStarDrawable implements Drawable {

    private final PathfindingGrid grid;

    public AStarDrawable() {
        grid = new PathfindingGrid();
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        grid.draw(spriteBatch);
    }

    public PathfindingGrid getGrid() {
        return grid;
    }
}