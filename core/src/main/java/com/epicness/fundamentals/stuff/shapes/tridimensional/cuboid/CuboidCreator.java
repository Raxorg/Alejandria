package com.epicness.fundamentals.stuff.shapes.tridimensional.cuboid;

import com.badlogic.gdx.graphics.g3d.Model;
import com.epicness.fundamentals.stuff.shapes.tridimensional.ModelCreator;

public class CuboidCreator extends ModelCreator<CuboidProperties> {

    protected CuboidCreator(float width, float height, float depth) {
        super(new CuboidProperties(width, height, depth), 6);
    }

    @Override
    protected Model build(CuboidProperties properties) {
        return modelBuilder.createBox(
            properties.width,
            properties.height,
            properties.depth,
            properties.material,
            properties.attributes
        );
    }
}