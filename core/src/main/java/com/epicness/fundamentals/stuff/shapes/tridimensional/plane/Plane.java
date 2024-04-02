package com.epicness.fundamentals.stuff.shapes.tridimensional.plane;

import com.epicness.fundamentals.stuff.shapes.tridimensional.Shape3D;

public class Plane extends Shape3D<PlaneCreator, PlaneProperties> {

    public Plane(float width, float height) {
        super(new PlaneCreator(width, height));
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

    public float getWidth() {
        return properties.width;
    }

    public float getHeight() {
        return properties.height;
    }
}