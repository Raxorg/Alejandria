package com.epicness.fundamentals.stuff.shapes.tridimensional.plane;

import static com.epicness.fundamentals.constants.Constants3D.LIGHTLESS_TEXTURED_ATTRIBUTES;

import com.badlogic.gdx.graphics.g3d.Material;
import com.epicness.fundamentals.stuff.shapes.tridimensional.ModelProperties;

public class PlaneProperties extends ModelProperties {

    public final float width, height;

    public PlaneProperties(float width, float height, Material material, long attributes) {
        super(material, attributes);
        this.width = width;
        this.height = height;
    }

    public PlaneProperties(float width, float height) {
        this(
            width, height,
            createDefaultMaterial(),
            LIGHTLESS_TEXTURED_ATTRIBUTES
        );
    }
}