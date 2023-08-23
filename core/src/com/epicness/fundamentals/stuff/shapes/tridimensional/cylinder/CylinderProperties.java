package com.epicness.fundamentals.stuff.shapes.tridimensional.cylinder;

import com.badlogic.gdx.graphics.g3d.Material;
import com.epicness.fundamentals.stuff.shapes.tridimensional.ModelProperties;

public class CylinderProperties extends ModelProperties {

    public final float width, height, depth, angleFrom, angleTo;
    public final int divisions;
    public final Material material;
    public final long attributes;

    public CylinderProperties(float width, float height, float depth, float angleFrom, float angleTo,
                              int divisions, Material material, long attributes) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.angleFrom = angleFrom;
        this.angleTo = angleTo;
        this.divisions = divisions;
        this.material = material;
        this.attributes = attributes;
    }
}