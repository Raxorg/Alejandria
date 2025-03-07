package com.epicness.fundamentals.stuff.shapes.bidimensional;

import static com.badlogic.gdx.graphics.Color.WHITE;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.stuff.interfaces.Buttonable;
import com.epicness.fundamentals.stuff.interfaces.ShapeDrawable;
import com.epicness.fundamentals.stuff.interfaces.Transformable;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class CirclePlus implements Transformable, Buttonable, ShapeDrawable {

    private float x, y, radius;
    private final Color borderColor, fillColor;
    private float thickness;

    public CirclePlus(float x, float y, float radius, Color borderColor, Color fillColor, float thickness) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.borderColor = new Color(borderColor);
        this.fillColor = new Color(fillColor);
        this.thickness = thickness;
    }

    public CirclePlus(Vector2 position, float radius, Color borderColor, Color fillColor, float thickness) {
        this(position.x, position.y, radius, borderColor, fillColor, thickness);
    }

    public CirclePlus(float x, float y, float radius, Color borderColor, Color fillColor) {
        this(x, y, radius, borderColor, fillColor, 3f);
    }

    public CirclePlus(float x, float y, float radius, Color color, float thickness) {
        this(x, y, radius, color, color, thickness);
    }

    public CirclePlus(Vector2 position, float radius, Color borderColor, Color fillColor) {
        this(position.x, position.y, radius, borderColor, fillColor, 3f);
    }

    public CirclePlus(float radius, Color borderColor, Color fillColor, float thickness) {
        this(0f, 0f, radius, borderColor, fillColor, thickness);
    }

    public CirclePlus(float x, float y, float radius, Color color) {
        this(x, y, radius, color, color);
    }

    public CirclePlus(float x, float y, Color color, float thickness) {
        this(x, y, 5f, color, thickness);
    }

    public CirclePlus(float radius, Color borderColor, Color fillColor) {
        this(radius, borderColor, fillColor, 3f);
    }

    public CirclePlus(float x, float y, float radius) {
        this(x, y, radius, new Color(1f, 1f, 1f, 1f));
    }

    public CirclePlus(float x, float y, Color color) {
        this(x, y, color, 3f);
    }

    public CirclePlus(float x, float y) {
        this(x, y, 5f);
    }

    public CirclePlus(float radius, Color color) {
        this(0f, 0f, radius, color);
    }

    public CirclePlus(Vector2 position) {
        this(position.x, position.y);
    }

    public CirclePlus(float radius) {
        this(radius, new Color(1f, 1f, 1f, 1f));
    }

    public CirclePlus(Color color) {
        this(5f, color);
    }

    public CirclePlus() {
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

    public void drawDebug(ShapeDrawerPlus shapeDrawer) {
        shapeDrawer.circle(x + radius, y + radius, radius, 1.5f, WHITE);
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
        radius += amount * 0.5f;
    }

    @Override
    public void stretchHeight(float amount) {
        radius += amount * 0.5f;
    }

    @Override
    public float getWidth() {
        return radius * 2f;
    }

    @Override
    public float getHeight() {
        return radius * 2f;
    }

    public void setCenterBasedPosition(float x, float y) {
        this.x = x - radius;
        this.y = y - radius;
    }

    public void setCenterBasedPosition(Vector2 position) {
        setCenterBasedPosition(position.x, position.y);
    }

    public float getRadius() {
        return radius;
    }

    public void setRadiusCentered(float radius) {
        x -= (radius - this.radius) * 0.5f;
        y -= (radius - this.radius) * 0.5f;
        this.radius = radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
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