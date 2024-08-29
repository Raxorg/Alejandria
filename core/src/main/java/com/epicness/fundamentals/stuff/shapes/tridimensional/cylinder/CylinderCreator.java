package com.epicness.fundamentals.stuff.shapes.tridimensional.cylinder;

import com.badlogic.gdx.graphics.g3d.Model;
import com.epicness.fundamentals.stuff.shapes.tridimensional.ModelCreator;

public class CylinderCreator extends ModelCreator<CylinderProperties> {

    public CylinderCreator(float width, float height, float depth, float angleTo) {
        super(new CylinderProperties(width, height, depth, angleTo), 5);
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