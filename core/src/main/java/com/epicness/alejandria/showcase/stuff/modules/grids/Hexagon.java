package com.epicness.alejandria.showcase.stuff.modules.grids;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;

import java.util.HashSet;
import java.util.Set;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class Hexagon extends Polygon {

    public final Set<Hexagon> neighbors;
    public final Color color;
    private float thickness;

    public Hexagon(float[] vertices) {
        super(vertices);
        neighbors = new HashSet<>();
        color = new Color(1f, 1f, 1f, 1f);
        thickness = 3f;
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.polygon(getTransformedVertices());
    }

    public void draw(ShapeDrawer shapeDrawer) {
        shapeDrawer.setColor(color);
        shapeDrawer.polygon(this, thickness);
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
    }
}