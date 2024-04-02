package com.epicness.fundamentals.stuff.shapes.tridimensional.cylinder;

import static com.epicness.fundamentals.constants.Constants3D.MATERIAL_ID;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.epicness.fundamentals.constants.Constants3D;
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

    public CylinderProperties(float width, float height, float depth, float angleTo) {
        this(
            width, height, depth,
            0f, angleTo,
            10,
            new Material(MATERIAL_ID, new BlendingAttribute(), IntAttribute.createCullFace(GL20.GL_NONE)),
            Constants3D.LIGHTLESS_TEXTURED_ATTRIBUTES
        );
    }
}