package com.epicness.alejandria.showcase.stuff.modules.optimization;

import com.badlogic.gdx.graphics.Color;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Rectangle;

public class Quad extends Rectangle {

    public final Color fillColor;

    public Quad(Color borderColor, Color fillColor) {
        super(borderColor);
        this.fillColor = fillColor;
    }

    public Quad(Color fillColor) {
        this.fillColor = fillColor;
    }

    @Override
    public void draw(ShapeDrawerPlus shapeDrawer) {
        shapeDrawer.filledRectangle(this, fillColor);
        super.draw(shapeDrawer);
    }
}