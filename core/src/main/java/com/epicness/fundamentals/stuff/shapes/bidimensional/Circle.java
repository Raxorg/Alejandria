package com.epicness.fundamentals.stuff.shapes.bidimensional;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.stuff.interfaces.Movable;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class Circle implements Movable {

    public float x, y, radius;
    private final Color color;
    private float thickness;

    public Circle(float x, float y, float radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        thickness = 1f;
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

    public Circle() {
        this(5f);
    }

    public void draw(ShapeDrawer shapeDrawer) {
        shapeDrawer.filledCircle(x, y, radius, color);
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.circle(x, y, radius);
    }

    public void drawBorder(ShapeDrawerPlus shapeDrawer) {
        shapeDrawer.circle(x, y, radius, thickness, color);
    }

    public float getStartX() {
        return x - radius;
    }

    public float getEndX() {
        return x + radius;
    }

    public float getStartY() {
        return y - radius;
    }

    public float getEndY() {
        return y + radius;
    }

    public Vector2 getCenter(Vector2 result) {
        return result.set(x, y);
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
    }
}