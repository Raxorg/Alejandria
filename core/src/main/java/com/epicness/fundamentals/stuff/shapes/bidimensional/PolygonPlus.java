package com.epicness.fundamentals.stuff.shapes.bidimensional;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Polygon;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.stuff.interfaces.Movable;
import com.epicness.fundamentals.stuff.interfaces.Rotatable;
import com.epicness.fundamentals.stuff.interfaces.ShapeDrawable;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class PolygonPlus extends Polygon implements ShapeDrawable, Movable, Rotatable {

    private final Color borderColor, fillColor;
    private float thickness;

    public PolygonPlus(float[] vertices, float thickness, Color borderColor, Color fillColor) {
        super(vertices);
        this.borderColor = new Color(borderColor);
        this.fillColor = new Color(fillColor);
        this.thickness = thickness;
    }

    public PolygonPlus(float[] vertices, float thickness) {
        this(vertices, thickness, new Color(1f, 1f, 1f, 1f), new Color(1f, 1f, 1f, 1f));
    }

    public PolygonPlus(float[] vertices) {
        this(vertices, 3f);
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