package com.epicness.alejandria.showcase.stuff.modules.animations;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Line;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class LinedBall {

    private final Line line;
    public final float radius;
    public final float speed;
    public final Color color;
    public float timer;
    public final Vector2 lastTrackedPosition;

    public LinedBall(float radius, float length, float speed, Color color) {
        line = new Line(length, 2.5f, color);
        this.radius = radius;
        this.speed = speed;
        this.color = color;
        lastTrackedPosition = new Vector2();
    }

    public void draw(ShapeDrawer shapeDrawer) {
        line.draw(shapeDrawer);
        shapeDrawer.filledCircle(
            line.getPosition().x + line.length * MathUtils.cosDeg(line.getAngleDeg()),
            line.getPosition().y + line.length * MathUtils.sinDeg(line.getAngleDeg()),
            radius,
            color
        );
    }

    public Vector2 getCenter() {
        return line.getA();
    }

    public void setCenter(float x, float y) {
        line.setA(x, y);
    }

    public void setCenter(Vector2 center) {
        setCenter(center.x, center.y);
    }

    public Vector2 getEnd() {
        return line.getB();
    }

    public void rotate(float degrees) {
        line.rotate(degrees);
    }

    public float getLength() {
        return line.length;
    }
}