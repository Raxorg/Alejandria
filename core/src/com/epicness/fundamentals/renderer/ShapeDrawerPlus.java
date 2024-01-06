package com.epicness.fundamentals.renderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class ShapeDrawerPlus extends ShapeDrawer {

    public ShapeDrawerPlus(SpriteBatch spriteBatch, Sprite sprite) {
        super(spriteBatch, sprite);
    }

    public void line(float x1, float y1, float x2, float y2, float lineWidth, Color color) {
        line(x1, y1, x2, y2, lineWidth, color, color);
    }
}