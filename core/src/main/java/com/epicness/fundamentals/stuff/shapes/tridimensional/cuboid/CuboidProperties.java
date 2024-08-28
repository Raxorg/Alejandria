package com.epicness.fundamentals.stuff.shapes.tridimensional.cuboid;

import com.badlogic.gdx.graphics.g3d.Material;
import com.epicness.fundamentals.constants.Constants3D;
import com.epicness.fundamentals.stuff.shapes.tridimensional.ModelProperties;

public class CuboidProperties extends ModelProperties {

    public final float width, height, depth;


    public CuboidProperties(float width, float height, float depth, Material material, long attributes) {
        super(material, attributes);
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public CuboidProperties(float width, float height, float depth) {
        this(
            width, height, depth,
            createDefaultMaterial(),
            Constants3D.LIGHTED_TEXTURED_ATTRIBUTES
        );
    }
}