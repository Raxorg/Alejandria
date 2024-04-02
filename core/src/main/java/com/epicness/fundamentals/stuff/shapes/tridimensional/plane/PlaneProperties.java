package com.epicness.fundamentals.stuff.shapes.tridimensional.plane;

import static com.epicness.fundamentals.constants.Constants3D.LIGHTLESS_TEXTURED_ATTRIBUTES;
import static com.epicness.fundamentals.constants.Constants3D.MATERIAL_ID;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
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

    public PlaneProperties(float width, float height) {
        this(
            width, height,
            new Material(MATERIAL_ID, new BlendingAttribute(), IntAttribute.createCullFace(GL20.GL_NONE)),
            LIGHTLESS_TEXTURED_ATTRIBUTES
        );
    }
}