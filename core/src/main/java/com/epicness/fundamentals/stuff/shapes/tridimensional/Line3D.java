package com.epicness.fundamentals.stuff.shapes.tridimensional;

import static com.badlogic.gdx.graphics.Color.RED;
import static com.badlogic.gdx.graphics.GL20.GL_LINES;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.ColorUnpacked;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Position;
import static com.epicness.fundamentals.constants.Constants3D.MATERIAL_ID;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

public class Line3D {

    private final ModelInstance lineInstance;
    private final float[] vertices;
    private final Vector3 start, end;
    private final ColorAttribute colorAttribute;

    public Line3D(float x1, float y1, float z1, float x2, float y2, float z2, Color color) {
        start = new Vector3(x1, y1, z1);
        end = new Vector3(x2, y2, z2);
        colorAttribute = ColorAttribute.createDiffuse(color);

        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();
        Material material = new Material(MATERIAL_ID, colorAttribute);
        MeshPartBuilder meshPartBuilder = modelBuilder.part("line", GL_LINES, Position | ColorUnpacked, material);
        meshPartBuilder.line(start, end);
        Model lineModel = modelBuilder.end();

        lineInstance = new ModelInstance(lineModel);
        vertices = new float[14];
    }

    public Line3D(float x1, float y1, float z1, float x2, float y2, float z2) {
        this(x1, y1, z1, x2, y2, z2, RED);
    }

    public Line3D(Vector3 start, Vector3 end, Color color) {
        this(start.x, start.y, start.z, end.x, end.y, end.z, color);
    }

    public Line3D(Vector3 start, Vector3 end) {
        this(start, end, RED);
    }

    public Line3D() {
        this(0f, 0f, 0f, 1f, 1f, 1f);
    }

    public void set(float x1, float y1, float z1, float x2, float y2, float z2) {
        start.set(x1, y1, z1);
        end.set(x2, y2, z2);
        lineInstance.model.meshes.first().getVertices(vertices);
        vertices[0] = x1;
        vertices[1] = y1;
        vertices[2] = z1;
        vertices[7] = x2;
        vertices[8] = y2;
        vertices[9] = z2;
        lineInstance.model.meshes.first().setVertices(vertices);
    }

    public void set(Vector3 v1, Vector3 v2) {
        set(v1.x, v1.y, v1.z, v2.x, v2.y, v2.z);
    }

    public void setColor(Color color) {
        colorAttribute.color.set(color);
        lineInstance.getMaterial(MATERIAL_ID).set(colorAttribute);
    }

    public void draw(ModelBatch modelBatch) {
        modelBatch.render(lineInstance);
    }

    public Ray getRay() {
        return new Ray(start, end.cpy().sub(start));
    }
}