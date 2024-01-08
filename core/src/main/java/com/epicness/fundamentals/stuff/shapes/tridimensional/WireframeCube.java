package com.epicness.fundamentals.stuff.shapes.tridimensional;

import static com.badlogic.gdx.graphics.Color.BLUE;
import static com.badlogic.gdx.graphics.Color.GREEN;
import static com.badlogic.gdx.graphics.Color.RED;
import static com.epicness.fundamentals.constants.Constants3D.STARTING_CUBE_BOUNDS;
import static com.epicness.fundamentals.constants.Constants3D.STARTING_CUBE_MAX;
import static com.epicness.fundamentals.constants.Constants3D.STARTING_CUBE_MIN;

import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.OrientedBoundingBox;

public class WireframeCube {

    public final OrientedBoundingBox orientedBoundingBox;
    private final Line3D[] lines;

    public WireframeCube() {
        orientedBoundingBox = new OrientedBoundingBox(STARTING_CUBE_BOUNDS);

        Vector3[] vertices = orientedBoundingBox.getVertices();

        lines = new Line3D[12];
        lines[0] = new Line3D(vertices[0], vertices[1], RED);
        lines[1] = new Line3D(vertices[1], vertices[3], RED);
        lines[2] = new Line3D(vertices[3], vertices[2], RED);
        lines[3] = new Line3D(vertices[2], vertices[0], RED);

        lines[4] = new Line3D(vertices[0], vertices[4], GREEN);
        lines[5] = new Line3D(vertices[1], vertices[5], GREEN);
        lines[6] = new Line3D(vertices[2], vertices[6], GREEN);
        lines[7] = new Line3D(vertices[3], vertices[7], GREEN);

        lines[8] = new Line3D(vertices[4], vertices[5], BLUE);
        lines[9] = new Line3D(vertices[5], vertices[7], BLUE);
        lines[10] = new Line3D(vertices[7], vertices[6], BLUE);
        lines[11] = new Line3D(vertices[6], vertices[4], BLUE);
    }

    public void draw(ModelBatch modelBatch) {
        for (int i = 0; i < lines.length; i++) {
            lines[i].draw(modelBatch);
        }
    }

    public void translate(float x, float y, float z) {
        Matrix4 transform = orientedBoundingBox.getTransform();
        transform.translate(x, y, z);
        orientedBoundingBox.setTransform(transform);
        update();
    }

    public void translateX(float x) {
        translate(x, 0f, 0f);
    }

    public void translateY(float y) {
        translate(0f, y, 0f);
    }

    public void translateZ(float z) {
        translate(0f, 0f, z);
    }

    public void setSize(float width, float height, float depth) {
        BoundingBox bounds = orientedBoundingBox.getBounds();
        bounds.min.set(STARTING_CUBE_MIN).scl(width, height, depth);
        bounds.max.set(STARTING_CUBE_MAX).scl(width, height, depth);
        orientedBoundingBox.setBounds(bounds);
        update();
    }

    public void setSize(float size) {
        setSize(size, size, size);
    }

    public void setWidth(float width) {
        BoundingBox bounds = orientedBoundingBox.getBounds();
        setSize(width, bounds.getHeight(), bounds.getDepth());
    }

    public void setHeight(float height) {
        BoundingBox bounds = orientedBoundingBox.getBounds();
        setSize(bounds.getWidth(), height, bounds.getDepth());
    }

    public void setDepth(float depth) {
        BoundingBox bounds = orientedBoundingBox.getBounds();
        setSize(bounds.getWidth(), bounds.getHeight(), depth);
    }

    public void rotateY(float degrees) {
        Matrix4 transform = orientedBoundingBox.getTransform();
        transform.rotate(Vector3.Y, degrees);
        orientedBoundingBox.setTransform(transform);
        update();
    }

    private void update() {
        Vector3[] vertices = orientedBoundingBox.getVertices();

        lines[0].set(vertices[0], vertices[1]);
        lines[1].set(vertices[1], vertices[3]);
        lines[2].set(vertices[3], vertices[2]);
        lines[3].set(vertices[2], vertices[0]);
        lines[4].set(vertices[0], vertices[4]);
        lines[5].set(vertices[1], vertices[5]);
        lines[6].set(vertices[2], vertices[6]);
        lines[7].set(vertices[3], vertices[7]);
        lines[8].set(vertices[4], vertices[5]);
        lines[9].set(vertices[5], vertices[7]);
        lines[10].set(vertices[7], vertices[6]);
        lines[11].set(vertices[6], vertices[4]);
    }
}