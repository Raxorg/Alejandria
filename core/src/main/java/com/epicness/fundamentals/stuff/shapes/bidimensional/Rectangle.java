package com.epicness.fundamentals.stuff.shapes.bidimensional;

import com.badlogic.gdx.graphics.Color;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class Rectangle extends com.badlogic.gdx.math.Rectangle {

    public final Color borderColor, fillColor;
    private float thickness;

    public Rectangle(float x, float y, float w, float h, Color borderColor, Color fillColor) {
        super(x, y, w, h);
        this.borderColor = borderColor;
        this.fillColor = fillColor;
        thickness = 5f;
    }

    public Rectangle(float x, float y, float w, float h, Color color) {
        this(x, y, w, h, color, color);
    }

    public Rectangle(float x, float y, float size, Color color) {
        this(x, y, size, size, color);
    }

    public Rectangle(float x, float y, float w, float h) {
        this(x, y, w, h, new Color(1f, 1f, 1f, 1f));
    }

    public Rectangle(Color borderColor, Color fillColor) {
        this(0f, 0f, 0f, 0f, borderColor, fillColor);
    }

    public Rectangle(Color color) {
        this(color, color);
    }

    public Rectangle() {
        this(new Color(1f, 1f, 1f, 1f));
    }

    public void drawFilled(ShapeDrawer shapeDrawer) {
        shapeDrawer.filledRectangle(this, fillColor);
    }

    public void drawBorder(ShapeDrawer shapeDrawer) {
        shapeDrawer.rectangle(this, borderColor, thickness);
    }

    public void draw(ShapeDrawer shapeDrawer) {
        drawFilled(shapeDrawer);
        drawBorder(shapeDrawer);
    }

    public void setColor(Color color) {
        borderColor.set(color);
        fillColor.set(color);
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
    }
}