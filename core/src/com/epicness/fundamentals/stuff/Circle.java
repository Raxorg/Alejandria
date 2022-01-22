package com.epicness.fundamentals.stuff;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Circle {

    private float x, y, radius;
    private Color color;

    public Circle(float radius) {
        this.radius = radius;
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

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Vector2 getPosition() {
        return new Vector2(x, y);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void translateX(float amount) {
        x += amount;
    }

    public void translateY(float amount) {
        y += amount;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}