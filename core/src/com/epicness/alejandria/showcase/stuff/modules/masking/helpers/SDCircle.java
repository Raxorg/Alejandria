package com.epicness.alejandria.showcase.stuff.modules.masking.helpers;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class SDCircle {

    private float centerX;
    private final float centerY, radius;

    public SDCircle(float centerX, float centerY, float radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public void draw(ShapeDrawer shapeDrawer, boolean filled) {
        if (filled) shapeDrawer.filledCircle(centerX, centerY, radius);
        else shapeDrawer.circle(centerX, centerY, radius);
    }

    public float getCenterX() {
        return centerX;
    }

    public void translateX(float amount) {
        centerX += amount;
    }

    public float getRadius() {
        return radius;
    }
}