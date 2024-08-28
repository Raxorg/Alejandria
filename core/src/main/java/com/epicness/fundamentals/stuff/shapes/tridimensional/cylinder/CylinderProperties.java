package com.epicness.fundamentals.stuff.shapes.tridimensional.cylinder;

import com.badlogic.gdx.graphics.g3d.Material;
import com.epicness.fundamentals.constants.Constants3D;
import com.epicness.fundamentals.stuff.shapes.tridimensional.ModelProperties;

public class CylinderProperties extends ModelProperties {

    public final float width, height, depth, angleFrom, angleTo;
    public final int divisions;

    public CylinderProperties(float width, float height, float depth, float angleFrom, float angleTo,
                              int divisions, Material material, long attributes) {
        super(material, attributes);
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.angleFrom = angleFrom;
        this.angleTo = angleTo;
        this.divisions = divisions;
    }

    public CylinderProperties(float width, float height, float depth, float angleTo) {
        this(
            width, height, depth,
            0f, angleTo,
            10,
            createDefaultMaterial(),
            Constants3D.LIGHTLESS_TEXTURED_ATTRIBUTES
        );
    }
}