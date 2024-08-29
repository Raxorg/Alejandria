package com.epicness.fundamentals.stuff.shapes.bidimensional;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.stuff.interfaces.Buttonable;
import com.epicness.fundamentals.stuff.interfaces.ShapeDrawable;
import com.epicness.fundamentals.stuff.interfaces.Transformable;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class Circle implements Transformable, Buttonable, ShapeDrawable {

    public final float radius;
    private float x, y;
    private final Color borderColor, fillColor;
    private float thickness;

    public Circle(float x, float y, float radius, Color borderColor, Color fillColor, float thickness) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.borderColor = borderColor;
        this.fillColor = fillColor;
        this.thickness = thickness;
    }

    public Circle(float x, float y, float radius, Color borderColor, Color fillColor) {
        this(x, y, radius, borderColor, fillColor, 3f);
    }

    public Circle(float x, float y, float radius, Color color, float thickness) {
        this(x, y, radius, color, color, thickness);
    }

    public Circle(float radius, Color borderColor, Color fillColor) {
        this(0f, 0f, radius, borderColor, fillColor);
    }

    public Circle(float x, float y, float radius, Color color) {
        this(x, y, radius, color, color);
    }

    public Circle(float x, float y, float radius) {
        this(x, y, radius, new Color(1f, 1f, 1f, 1f));
    }

    public Circle(float x, float y, Color color) {
        this(x, y, 5f, color);
    }

    public Circle(float x, float y) {
        this(x, y, 5f);
    }

    public Circle(float radius, Color color) {
        this(0f, 0f, radius, color);
    }

    public Circle(float radius) {
        this(radius, new Color(1f, 1f, 1f, 1f));
    }

    public Circle(Color color) {
        this(5f, color);
    }

    public Circle() {
        this(5f);
    }

    public void drawFilled(ShapeDrawer shapeDrawer) {
        shapeDrawer.filledCircle(x + radius, y + radius, radius, fillColor);
    }

    public void drawBorder(ShapeDrawerPlus shapeDrawer) {
        shapeDrawer.circle(x + radius, y + radius, radius, thickness, borderColor);
    }

    @Override
    public void draw(ShapeDrawerPlus shapeDrawer) {
        drawFilled(shapeDrawer);
        drawBorder(shapeDrawer);
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(borderColor);
        shapeRenderer.circle(x + radius, y + radius, radius);
    }

    @Override
    public boolean contains(float x, float y) {
        x = this.x - x + radius;
        y = this.y - y + radius;
        return x * x + y * y <= radius * radius;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void translateX(float amount) {
        x += amount;
    }

    @Override
    public void translateY(float amount) {
        y += amount;
    }

    @Override
    public float getRotation() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void rotate(float degrees) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void stretchWidth(float amount) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void stretchHeight(float amount) {
        throw new UnsupportedOperationException();
    }

    @Override
    public float getWidth() {
        return radius * 2f;
    }

    @Override
    public float getHeight() {
        return radius * 2f;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color color) {
        borderColor.set(color);
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color color) {
        fillColor.set(color);
    }

    public void setColor(Color color) {
        borderColor.set(color);
        fillColor.set(color);
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
    }
}