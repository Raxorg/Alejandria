package com.epicness.fundamentals.stuff.shapes.bidimensional;

import static com.badlogic.gdx.math.MathUtils.degRad;
import static com.badlogic.gdx.math.MathUtils.radDeg;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.stuff.interfaces.Movable;
import com.epicness.fundamentals.stuff.interfaces.ShapeDrawable;

public class Sector implements ShapeDrawable, Movable {

    private float x, y, radians;
    public final float radius;
    private final float startAngle, thickness;
    private final Color borderColor, fillColor;
    private float line1x2, line1y2, line2x2, line2y2;

    public Sector(float x, float y, float radius, float startDegrees, float sectorDegrees,
                  Color borderColor, Color fillColor, float thickness) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.startAngle = startDegrees * degRad;
        this.radians = sectorDegrees * degRad;
        this.thickness = thickness;
        this.fillColor = new Color(fillColor);
        this.borderColor = new Color(borderColor);
        calcLines();
    }

    private void calcLines() {
        line1x2 = x + MathUtils.cos(startAngle) * radius;
        line1y2 = y + MathUtils.sin(startAngle) * radius;
        line2x2 = x + MathUtils.cos(startAngle + radians) * radius;
        line2y2 = y + MathUtils.sin(startAngle + radians) * radius;
    }

    @Override
    public void draw(ShapeDrawerPlus shapeDrawer) {
        shapeDrawer.sector(x, y, radius, startAngle, radians, fillColor);
        shapeDrawer.arc(x, y, radius, startAngle, radians, thickness, borderColor);
        shapeDrawer.line(x, y, line1x2, line1y2, borderColor, thickness);
        shapeDrawer.line(x, y, line2x2, line2y2, borderColor, thickness);
    }

    @Override
    public float getX() {
        return x - radius;
    }

    @Override
    public void translateX(float amount) {
        x += amount;
        calcLines();
    }

    @Override
    public float getY() {
        return y - radius;
    }

    @Override
    public void translateY(float amount) {
        y += amount;
        calcLines();
    }

    public float getWidth() {
        return radius * 2f;
    }

    public float getHeight() {
        return radius * 2f;
    }

    public Vector2 getSize() {
        return new Vector2(getWidth(), getHeight());
    }

    public float getDegrees() {
        return radians * radDeg;
    }

    public void setDegrees(float degrees) {
        radians = degrees * degRad;
        calcLines();
    }
}