package com.epicness.fundamentals.stuff.grid;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.stuff.interfaces.Movable;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Drawable2D;

public abstract class VisibleAStarGrid<T extends Drawable2D & Movable> extends AStarGrid<T> implements Movable, Drawable2D {

    public VisibleAStarGrid(int cols, int rows) {
        super(cols, rows);
        traverseCells(cell -> cell.setObject(makeObject(cell.col, cell.row)));
    }

    @Override
    public void setDimensions(int cols, int rows) {
        super.setDimensions(cols, rows);
        traverseCells(cell -> cell.setObject(makeObject(cell.col, cell.row)));
    }

    protected abstract T makeObject(int col, int row);

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer) {
        traverseObjects(object -> object.draw(spriteBatch, shapeDrawer));
    }

    @Override
    public float getX() {
        return getObject(0, 0).getX();
    }

    @Override
    public void translateX(float amount) {
        traverseObjects(object -> object.translateX(amount));
    }

    @Override
    public float getY() {
        return getObject(0, 0).getY();
    }

    @Override
    public void translateY(float amount) {
        traverseObjects(object -> object.translateY(amount));
    }
}