package com.epicness.fundamentals.stuff.shapes.tridimensional.plane;

import com.badlogic.gdx.graphics.g3d.Model;
import com.epicness.fundamentals.stuff.shapes.tridimensional.ModelCreator;

public class PlaneCreator extends ModelCreator<PlaneProperties> {

    public PlaneCreator(float width, float height) {
        super(new PlaneProperties(width, height), 5);
    }

    @Override
    protected Model build(PlaneProperties properties) {
        float hw = properties.width / 2f;
        float hh = properties.height / 2f;
        return modelBuilder.createRect(
            -hw, -hh, 0f,   // v1
            hw, -hh, 0f,    // v2
            hw, hh, 0f,     // v3
            -hw, hh, 0f,    // v4
            1f, 0.8f, 0.2f, // normals
            properties.material,
            properties.attributes
        );
    }
}