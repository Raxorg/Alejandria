package com.epicness.fundamentals.stuff.shapes.tridimensional.cuboid;

import com.epicness.fundamentals.stuff.shapes.tridimensional.Shape3D;

public class Cuboid extends Shape3D<CuboidCreator, CuboidProperties> {

    public Cuboid(float width, float height, float depth) {
        super(new CuboidCreator(width, height, depth));
    }

    @Override
    protected void updateDebugLines() {
        for (index = 0; index < rotationVertices.length; index++) {
            extraIndex = (index + 1) % rotationVertices.length;
            debugLines[index].set(
                rotationVertices[index].x + position.x,
                rotationVertices[index].y + position.y,
                rotationVertices[index].z + position.z,
                rotationVertices[extraIndex].x + position.x,
                rotationVertices[extraIndex].y + position.y,
                rotationVertices[extraIndex].z + position.z
            );
        }
    }
}