package com.epicness.alejandria.showcase.stuff.modules.masking;

import com.badlogic.gdx.graphics.Color;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class SDTriangle {

    private final float x1, y1, x2, y2, x3, y3;
    private Color color1, color2, color3;

    public SDTriangle(float x1, float y1, float x2, float y2, float x3, float y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        color1 = Color.WHITE;
        color2 = Color.WHITE;
        color3 = Color.WHITE;
    }

    public void draw(ShapeDrawer shapeDrawer, boolean filled) {
        if (filled) shapeDrawer.filledTriangle(x1, y1, x2, y2, x3, y3, color1, color2, color3);
        else shapeDrawer.triangle(x1, y1, x2, y2, x3, y3, color1);
    }

    public void setColor(Color color) {
        color1 = color;
        color2 = color;
        color3 = color;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public void setColor3(Color color3) {
        this.color3 = color3;
    }
}