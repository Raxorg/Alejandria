package com.epicness.fundamentals.stuff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;

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

    public void setY(float y) {
        this.y = y;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void translateX(float amount) {
        x += amount;
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