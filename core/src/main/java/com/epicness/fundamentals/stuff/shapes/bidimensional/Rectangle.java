package com.epicness.fundamentals.stuff.shapes.bidimensional;

import com.badlogic.gdx.graphics.Color;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;

public class Rectangle extends com.badlogic.gdx.math.Rectangle {

    public final Color color;
    public final float thickness = 5f;

    public Rectangle() {
        color = new Color(1f, 1f, 1f, 1f);
    }

    public Rectangle(Color color) {
        this.color = color;
    }

    public void draw(ShapeDrawerPlus shapeDrawer) {
        shapeDrawer.rectangle(this, color, thickness);
    }

    public void setColor(Color color) {
        this.color.set(color);
    }
}