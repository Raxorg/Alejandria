package com.epicness.fundamentals.stuff.shapes.bidimensional;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Triangle {

    private float x1, y1, x2, y2, x3, y3;

    public Triangle(float x1, float y1, float x2, float y2, float x3, float y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.triangle(x1, y1, x2, y2, x3, y3);
    }

    public float getX() {
        return Math.min(Math.min(x1, x2), x3);
    }

    public float getEndX() {
        return Math.max(Math.max(x1, x2), x3);
    }

    public void translateX(float amount) {
        x1 += amount;
        x2 += amount;
        x3 += amount;
    }

    public void translateY(float amount) {
        y1 += amount;
        y2 += amount;
        y3 += amount;
    }
}