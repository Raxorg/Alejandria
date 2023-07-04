package com.epicness.alejandria.showcase.stuff.modules.grids;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;

import java.util.HashSet;
import java.util.Set;

public class Hexagon extends Polygon {

    public final Set<Hexagon> neighbors;

    public Hexagon(float[] vertices) {
        super(vertices);
        neighbors = new HashSet<>();
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.polygon(getTransformedVertices());
    }
}