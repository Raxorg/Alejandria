package com.epicness.fundamentals.stuff.shapes.bidimensional;

import static com.badlogic.gdx.graphics.Color.WHITE;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.stuff.interfaces.Buttonable;
import com.epicness.fundamentals.stuff.interfaces.ShapeDrawable;
import com.epicness.fundamentals.stuff.interfaces.Transformable;

public class RectanglePlus implements Buttonable, ShapeDrawable, Transformable {

    private final Rectangle rectangle;
    public final Color borderColor, fillColor;
    private float thickness, rotation;

    public RectanglePlus(float x, float y, float w, float h, Color borderColor, Color fillColor, float thickness, float rotation) {
        rectangle = new Rectangle(x, y, w, h);
        this.borderColor = new Color(borderColor);
        this.fillColor = new Color(fillColor);
        this.thickness = thickness;
        this.rotation = rotation;
    }

    public RectanglePlus(float x, float y, float w, float h, Color borderColor, Color fillColor, float thickness) {
        this(x, y, w, h, borderColor, fillColor, thickness, 0f);
    }

    public RectanglePlus(float x, float y, float size, Color borderColor, Color fillColor, float thickness) {
        this(x, y, size, size, borderColor, fillColor, thickness);
    }

    public RectanglePlus(float x, float y, float w, float h, Color borderColor, Color fillColor) {
        this(x, y, w, h, borderColor, fillColor, 5f);
    }

    public RectanglePlus(float x, float y, float size, Color color, float thickness) {
        this(x, y, size, size, color, color, thickness);
    }

    public RectanglePlus(float x, float y, float w, float h, Color color) {
        this(x, y, w, h, color, color);
    }

    public RectanglePlus(float x, float y, float size, Color borderColor, Color fillColor) {
        this(x, y, size, size, borderColor, fillColor);
    }

    public RectanglePlus(float x, float y, float size, Color color) {
        this(x, y, size, size, color);
    }

    public RectanglePlus(float x, float y, float w, float h) {
        this(x, y, w, h, new Color(1f, 1f, 1f, 1f));
    }

    public RectanglePlus(float x, float y, float size) {
        this(x, y, size, size);
    }

    public RectanglePlus(float size, Color borderColor, Color fillColor) {
        this(0f, 0f, size, borderColor, fillColor);
    }

    public RectanglePlus(Color borderColor, Color fillColor) {
        this(0f, 0f, 5f, 5f, borderColor, fillColor);
    }

    public RectanglePlus(Vector2 position) {
        this(position.x, position.y, 5f);
    }

    public RectanglePlus(float size) {
        this(0f, 0f, size);
    }

    public RectanglePlus(Color color) {
        this(color, color);
    }

    public RectanglePlus() {
        this(new Color(1f, 1f, 1f, 1f));
    }

    public RectanglePlus(RectanglePlus rectanglePlus) {
        this(rectanglePlus.getX(), rectanglePlus.getY(), rectanglePlus.getWidth(), rectanglePlus.getHeight(),
            rectanglePlus.borderColor, rectanglePlus.fillColor, rectanglePlus.thickness, rectanglePlus.rotation);
    }

    @Override
    public boolean contains(float x, float y) {
        return rectangle.contains(x, y);
    }

    public boolean overlaps(RectanglePlus other) {
        return rectangle.overlaps(other.rectangle);
    }

    public void drawFilled(ShapeDrawerPlus shapeDrawer) {
        shapeDrawer.filledRectangle(rectangle, fillColor, rotation);
    }

    public void drawBorder(ShapeDrawerPlus shapeDrawer) {
        shapeDrawer.rectangle(rectangle, borderColor, thickness, rotation);
    }

    @Override
    public void draw(ShapeDrawerPlus shapeDrawer) {
        drawFilled(shapeDrawer);
        drawBorder(shapeDrawer);
    }

    public void drawDebug(ShapeDrawerPlus shapeDrawer) {
        shapeDrawer.rectangle(rectangle, WHITE, 1.5f, rotation);
    }

    @Override
    public float getX() {
        return rectangle.x;
    }

    @Override
    public void translateX(float amount) {
        rectangle.x += amount;
    }

    @Override
    public float getY() {
        return rectangle.y;
    }

    @Override
    public void translateY(float amount) {
        rectangle.y += amount;
    }

    @Override
    public void stretchWidth(float amount) {
        rectangle.width += amount;
    }

    @Override
    public void stretchHeight(float amount) {
        rectangle.height += amount;
    }

    @Override
    public float getWidth() {
        return rectangle.width;
    }

    @Override
    public float getHeight() {
        return rectangle.height;
    }

    @Override
    public float getRotation() {
        return rotation;
    }

    @Override
    public void rotate(float degrees) {
        rotation += degrees;
    }

    public void setBorderColor(Color color) {
        borderColor.set(color);
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

    @Override
    public String toString() {
        return "Rectangle" + rectangle;
    }
}