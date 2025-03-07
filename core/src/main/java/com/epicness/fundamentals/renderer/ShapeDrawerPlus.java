package com.epicness.fundamentals.renderer;

import static com.badlogic.gdx.math.MathUtils.degRad;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class ShapeDrawerPlus extends ShapeDrawer {

    public ShapeDrawerPlus(SpriteBatch spriteBatch, Sprite sprite) {
        super(spriteBatch, sprite);
    }

    public void line(float x1, float y1, float x2, float y2, float lineWidth, Color color) {
        line(x1, y1, x2, y2, lineWidth, color, color);
    }

    public void circle(float x, float y, float radius, Color color) {
        circle(x, y, radius, getDefaultLineWidth(), color);
    }

    public void circle(float x, float y, float radius, float thickness, Color color) {
        float auxColor = setColor(color);
        circle(x, y, radius, thickness);
        setColor(auxColor);
    }

    public void arc(float centreX, float centreY, float radius, float startAngle, float radians, float lineWidth, Color color) {
        float auxColor = setColor(color);
        arc(centreX, centreY, radius, startAngle, radians, lineWidth);
        setColor(auxColor);
    }

    public void sector(float centreX, float centreY, float radius, float startAngle, float radians, Color color) {
        sector(centreX, centreY, radius, startAngle, radians, color, color);
    }

    public void rectangle(Rectangle rectangle, Color color, float thickness, float degrees) {
        float auxColor = setColor(color);
        rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height, thickness, degrees * degRad);
        setColor(auxColor);
    }

    public void filledRectangle(Rectangle rectangle, Color color, float degrees) {
        float auxColor = setColor(color);
        filledRectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height, degrees * degRad);
        setColor(auxColor);
    }
}