package com.epicness.alejandria.showcase.stuff.modules.grids;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class RectangleCell extends Rectangle {

    public Color color;

    public RectangleCell(float x, float y, float size, Color color) {
        super(x, y, size, size);
        this.color = color;
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rect(x, y, width, height);
    }
}