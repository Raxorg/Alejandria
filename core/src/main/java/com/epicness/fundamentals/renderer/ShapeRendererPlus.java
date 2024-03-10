package com.epicness.fundamentals.renderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class ShapeRendererPlus extends ShapeRenderer {

    public void rect(Rectangle rectangle) {
        rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public void rect(float x, float y, float width, float height, Color color) {
        rect(x, y, width, height, color, color, color, color);
    }
}