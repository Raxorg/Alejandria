package com.epicness.fundamentals.stuff.shapes.bidimensional;

import static com.badlogic.gdx.graphics.Color.WHITE;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.epicness.fundamentals.stuff.interfaces.Movable;
import com.epicness.fundamentals.utils.AngleUtils;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class Line implements Movable {

    private final Vector2 a, b;
    private float angleDeg;
    public final float length;
    private final Color color;
    public float width;

    public Line(float ax, float ay, float bx, float by, float width, Color color) {
        a = new Vector2(ax, ay);
        b = new Vector2(bx, by);
        angleDeg = AngleUtils.degreesBetweenPoints(b, a);
        length = a.dst(b);
        this.width = width;
        this.color = color;
    }

    public Line(float ax, float ay, float bx, float by, float width) {
        this(ax, ay, bx, by, width, WHITE.cpy());
    }

    public Line(float ax, float ay, float bx, float by, Color color) {
        this(ax, ay, bx, by, 5f, color);
    }

    public Line(float ax, float ay, float bx, float by) {
        this(ax, ay, bx, by, 5f);
    }

    public Line(float x, float y, float length, float angle, boolean degrees, float width, Color color) {
        a = new Vector2(x, y);
        b = new Vector2();
        this.length = length;
        angleDeg = degrees ? angle : (angle * MathUtils.radDeg);
        calculateB();
        this.width = width;
        this.color = color;
    }

    public Line(float x, float y, float length, float angle, boolean degrees) {
        this(x, y, length, angle, degrees, 5f, WHITE.cpy());
    }

    public Line(float length, float width, Color color) {
        this(0f, 0f, length, 0f, true, width, color);
    }

    public Line(float x, float y, float length) {
        this(x, y, length, 0f, true);
    }

    public Line(float length) {
        this(0f, 0f, length);
    }

    public Line(Line line, float length, float angleDeg) {
        this(line.b.x, line.b.y, length, angleDeg, true);
    }

    public Line(Line line, float length) {
        this(line, length, 0f);
    }

    private void calculateB() {
        float deltaX = length, deltaY = length;
        deltaX *= MathUtils.cosDeg(angleDeg);
        deltaY *= MathUtils.sinDeg(angleDeg);
        b.set(a.x + deltaX, a.y + deltaY);
    }

    public void draw(ShapeDrawer shapeDrawer) {
        shapeDrawer.line(a, b, color, width);
    }

    @Override
    public void translateX(float amount) {
        a.x += amount;
        b.x += amount;
    }

    @Override
    public void translateY(float amount) {
        a.y += amount;
        b.y += amount;
    }

    @Override
    public float getX() {
        return a.x;
    }

    @Override
    public float getY() {
        return a.y;
    }

    public void follow(Vector2 target) {
        Vector2 dir = target.cpy().sub(a);
        setRotation(dir.angleDeg());
        dir.setLength(length);
        dir.scl(-1f);
        setA(target.cpy().add(dir));
    }

    public Vector2 getA() {
        return a;
    }

    public Vector2 getB() {
        return b;
    }

    public void setA(float x, float y) {
        a.set(x, y);
        calculateB();
    }

    public void setA(Vector2 position) {
        setA(position.x, position.y);
    }

    public float getAngleDeg() {
        return angleDeg;
    }

    public void rotate(float degrees) {
        angleDeg += degrees;
        if (angleDeg >= 360f) {
            angleDeg = angleDeg % 360f;
        }
        calculateB();
    }

    public void setRotation(float degrees) {
        rotate(degrees - angleDeg);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color newColor) {
        color.set(newColor);
    }
}