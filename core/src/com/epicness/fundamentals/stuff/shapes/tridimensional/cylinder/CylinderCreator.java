package com.epicness.fundamentals.stuff.shapes.tridimensional.cylinder;

import com.badlogic.gdx.graphics.g3d.Model;
import com.epicness.fundamentals.stuff.shapes.tridimensional.ModelCreator;

public class CylinderCreator extends ModelCreator<CylinderProperties> {

    public CylinderCreator(CylinderProperties properties) {
        super(properties);
    }

    @Override
    protected Model build(CylinderProperties properties) {
        return modelBuilder.createCylinder(
            properties.width,
            properties.height,
            properties.depth,
            properties.divisions,
            properties.material,
            properties.angleFrom,
            properties.angleTo
        );
    }
}