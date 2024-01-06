package com.epicness.fundamentals.stuff.shapes.tridimensional.plane;

import com.badlogic.gdx.graphics.g3d.Material;
import com.epicness.fundamentals.stuff.shapes.tridimensional.ModelProperties;

public class PlaneProperties extends ModelProperties {

    public final float width, height;
    public final Material material;
    public final long attributes;

    public PlaneProperties(float width, float height, Material material, long attributes) {
        this.width = width;
        this.height = height;
        this.material = material;
        this.attributes = attributes;
    }
}