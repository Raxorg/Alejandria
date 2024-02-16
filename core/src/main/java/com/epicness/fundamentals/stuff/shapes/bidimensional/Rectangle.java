package com.epicness.fundamentals.stuff.shapes.bidimensional;

import com.badlogic.gdx.graphics.Color;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;

public class Rectangle extends com.badlogic.gdx.math.Rectangle {

    public final Color borderColor, fillColor;
    private float thickness;

    public Rectangle(Color borderColor, Color fillColor) {
        this.borderColor = borderColor;
        this.fillColor = fillColor;
        thickness = 5f;
    }

    public Rectangle(Color color) {
        this(color, color);
    }

    public Rectangle() {
        this(new Color(1f, 1f, 1f, 1f));
    }

    public void drawBorder(ShapeDrawerPlus shapeDrawer) {
        shapeDrawer.rectangle(this, borderColor, thickness);
    }

    public void drawFilled(ShapeDrawerPlus shapeDrawer) {
        shapeDrawer.filledRectangle(this, borderColor);
    }

    public void draw(ShapeDrawerPlus shapeDrawer) {
        shapeDrawer.filledRectangle(this, fillColor);
        shapeDrawer.rectangle(this, borderColor, thickness);
    }

    public void setColor(Color color) {
        borderColor.set(color);
        fillColor.set(color);
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
    }
}