package com.epicness.fundamentals.stuff.shapes.tridimensional.cylinder;

import com.epicness.fundamentals.stuff.shapes.tridimensional.Shape3D;

public class Cylinder extends Shape3D<CylinderCreator, CylinderProperties> {

    public Cylinder(float width, float height, float depth, float angleTo) {
        super(new CylinderCreator(width, height, depth, angleTo));
    }

    @Override
    protected void updateDebugLines() {
        for (index = 0; index < rotationVertices.length / 2; index++) {
            extraIndex = (index + 2) % rotationVertices.length;
            debugLines[index].set(
                rotationVertices[index].x + position.x,
                rotationVertices[index].y + position.y,
                rotationVertices[index].z + position.z,
                rotationVertices[extraIndex].x + position.x,
                rotationVertices[extraIndex].y + position.y,
                rotationVertices[extraIndex].z + position.z
            );
        }
        for (index = rotationVertices.length / 2; index < rotationVertices.length - 2; index++) {
            extraIndex = (index + 2) % rotationVertices.length;
            debugLines[index].set(
                rotationVertices[index].x + position.x,
                rotationVertices[index].y + position.y,
                rotationVertices[index].z + position.z,
                rotationVertices[extraIndex].x + position.x,
                rotationVertices[extraIndex].y + position.y,
                rotationVertices[extraIndex].z + position.z
            );
        }
        index = rotationVertices.length - 2;
        extraIndex = (index + 1) % rotationVertices.length;
        debugLines[index].set(
            rotationVertices[index].x + position.x,
            rotationVertices[index].y + position.y,
            rotationVertices[index].z + position.z,
            rotationVertices[extraIndex].x + position.x,
            rotationVertices[extraIndex].y + position.y,
            rotationVertices[extraIndex].z + position.z
        );
        index = rotationVertices.length - 1;
        debugLines[index].set(
            rotationVertices[0].x + position.x,
            rotationVertices[0].y + position.y,
            rotationVertices[0].z + position.z,
            rotationVertices[1].x + position.x,
            rotationVertices[1].y + position.y,
            rotationVertices[1].z + position.z
        );
    }

    public float getWidth() {
        return properties.width;
    }

    public float getHeight() {
        return properties.height;
    }

    public float getDepth() {
        return properties.depth;
    }
}