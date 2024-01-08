package com.epicness.fundamentals.stuff.shapes.tridimensional;

import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Position;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.TextureCoordinates;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.CylinderShapeBuilder;

public class ModelBuilderPlus extends ModelBuilder {

    public Model createCylinder(float width, float height, float depth, int divisions, int primitiveType,
                                final Material material, final long attributes, float angleFrom, float angleTo,
                                boolean close) {
        begin();
        MeshPartBuilder meshPartBuilder = part("cylinder", primitiveType, attributes, material);
        CylinderShapeBuilder.build(meshPartBuilder, width, height, depth, divisions, angleFrom, angleTo, close);
        return end();
    }

    @Override
    public Model createCylinder(float width, float height, float depth, int divisions, final Material material,
                                final long attributes, float angleFrom, float angleTo) {
        return createCylinder(width, height, depth, divisions, GL20.GL_TRIANGLES, material, attributes,
            angleFrom, angleTo, false);
    }

    public Model createCylinder(float width, float height, float depth, int divisions, final Material material,
                                float angleFrom, float angleTo) {
        return createCylinder(width, height, depth, divisions, material, Position | TextureCoordinates, angleFrom, angleTo);
    }

    public Model createCylinder(float width, float height, float depth, int divisions, final Material material) {
        return createCylinder(width, height, depth, divisions, material, 0f, 360f);
    }
}