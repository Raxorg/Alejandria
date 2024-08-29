package com.epicness.fundamentals.stuff.shapes.tridimensional;

import static com.badlogic.gdx.graphics.GL20.GL_NONE;
import static com.epicness.fundamentals.constants.Constants3D.MATERIAL_ID;

import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;

public abstract class ModelProperties {

    public final Material material;
    public final long attributes;

    public ModelProperties(Material material, long attributes) {
        this.material = material;
        this.attributes = attributes;
    }

    protected static Material createDefaultMaterial() {
        return new Material(
            MATERIAL_ID,
            new BlendingAttribute(),
            FloatAttribute.createAlphaTest(0f),
            IntAttribute.createCullFace(GL_NONE)
        );
    }
}