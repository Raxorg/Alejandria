package com.epicness.alejandria.showcase.stuff.modules.procedural.helpers;

import com.badlogic.gdx.graphics.Color;

public class Pixel {

    private float size;
    private Color color;

    public Pixel(Color color) {
        this.color = color;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}