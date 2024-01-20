package com.epicness.fundamentals.stuff.shapes.bidimensional;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.fundamentals.stuff.interfaces.Movable;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class Circle implements Movable {

    public float x, y, radius;
    private final Color color;

    public Circle(float x, float y, float radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
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

    public void drawContour(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color.cpy().set(1 - color.r, 1 - color.g, 1 - color.b, 1f));
        shapeRenderer.set(Line);
        shapeRenderer.circle(x, y, radius);
    }

    public float getCenterX() {
        return x;
    }

    public float getEndX() {
        return x + radius;
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
}