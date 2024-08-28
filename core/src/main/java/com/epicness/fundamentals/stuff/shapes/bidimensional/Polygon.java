package com.epicness.fundamentals.stuff.shapes.bidimensional;

import com.badlogic.gdx.graphics.Color;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.stuff.interfaces.Movable;
import com.epicness.fundamentals.stuff.interfaces.ShapeDrawable;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class Polygon extends com.badlogic.gdx.math.Polygon implements ShapeDrawable, Movable {

    private final Color borderColor, fillColor;
    private float thickness;

    public Polygon(float[] vertices) {
        super(vertices);
        borderColor = new Color(1f, 1f, 1f, 1f);
        fillColor = new Color(1f, 1f, 1f, 1f);
        thickness = 3f;
    }

    public void drawFilled(ShapeDrawer shapeDrawer) {
        float oldColor = shapeDrawer.setColor(fillColor);
        shapeDrawer.filledPolygon(this);
        shapeDrawer.setColor(oldColor);
    }

    public void drawBorder(ShapeDrawer shapeDrawer) {
        float oldColor = shapeDrawer.setColor(borderColor);
        shapeDrawer.polygon(this, thickness);
        shapeDrawer.setColor(oldColor);
    }

    @Override
    public void draw(ShapeDrawerPlus shapeDrawer) {
        drawFilled(shapeDrawer);
        drawBorder(shapeDrawer);
    }

    @Override
    public void translateX(float amount) {
        super.translate(amount, 0f);
    }

    @Override
    public void translateY(float amount) {
        super.translate(0f, amount);
    }

    public void setBorderColor(Color color) {
        borderColor.set(color);
    }

    public void setFillColor(Color color) {
        fillColor.set(color);
    }

    public void setColor(Color color) {
        setBorderColor(color);
        setFillColor(color);
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
    }
}