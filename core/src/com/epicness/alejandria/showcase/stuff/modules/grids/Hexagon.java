package com.epicness.alejandria.showcase.stuff.modules.grids;

import static com.badlogic.gdx.graphics.Color.WHITE;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;

import java.util.HashSet;
import java.util.Set;

public class Hexagon extends Polygon {

    public final Set<Hexagon> neighbors;
    public final Color color;

    public Hexagon(float[] vertices) {
        super(vertices);
        neighbors = new HashSet<>();
        color = WHITE;
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.polygon(getTransformedVertices());
    }
}