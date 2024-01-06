package com.epicness.fundamentals.stuff.shapes.tridimensional;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;

public abstract class Shape3D<M extends ModelCreator<P>, P extends ModelProperties> {

    protected final P properties;
    private final ModelInstance modelInstance;
    protected final Vector3[] rotationVertices;
    private final float[] plainVertices;
    protected static int index, extraIndex;
    private static final Quaternion QUATERNION_HELPER = new Quaternion();
    protected final Vector3 position;
    private final short[] indices;
    protected final Line3D[] debugLines;

    public Shape3D(M modelCreator) {
        properties = modelCreator.properties;
        Model model = modelCreator.build(properties);
        modelInstance = new ModelInstance(model);
        Mesh mesh = model.meshes.first();

        float[] verticesWithUV = new float[mesh.getNumVertices() * mesh.getVertexSize() / 4];
        mesh.getVertices(verticesWithUV);
        rotationVertices = new Vector3[verticesWithUV.length / 5];
        plainVertices = new float[rotationVertices.length * 3];
        for (index = 0, extraIndex = 0; index < verticesWithUV.length; index += 5, extraIndex++) {
            rotationVertices[extraIndex] = new Vector3(
                verticesWithUV[index],
                verticesWithUV[index + 1],
                verticesWithUV[index + 2]
            );
            plainVertices[extraIndex * 3] = rotationVertices[extraIndex].x;
            plainVertices[extraIndex * 3 + 1] = rotationVertices[extraIndex].y;
            plainVertices[extraIndex * 3 + 2] = rotationVertices[extraIndex].z;
        }
        position = new Vector3();
        indices = new short[mesh.getNumIndices()];
        mesh.getIndices(indices);
        debugLines = new Line3D[rotationVertices.length];
        for (index = 0; index < debugLines.length; index++) {
            debugLines[index] = new Line3D();
        }
        updateDebugLines();
    }

    protected abstract void updateDebugLines();

    public final void draw(ModelBatch modelBatch) {
        modelBatch.render(modelInstance);
    }

    public final void draw(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(modelInstance, environment);
    }

    public void drawDebug(ModelBatch modelBatch) {
        for (int i = 0; i < debugLines.length; i++) {
            debugLines[i].draw(modelBatch);
        }
    }

    public void setSprite(Sprite sprite) {
        modelInstance.getMaterial("material").set(TextureAttribute.createDiffuse(sprite));
    }

    public float[] getVertices() {
        return plainVertices;
    }

    public final short[] getIndices() {
        return indices;
    }

    public final float getX() {
        return position.x;
    }

    public final float getY() {
        return position.y;
    }

    public final float getZ() {
        return position.z;
    }

    public final void translate(float xAmount, float yAmount, float zAmount) {
        modelInstance.transform.translate(xAmount, yAmount, zAmount);
        position.add(xAmount, yAmount, zAmount);
        for (index = 0; index < plainVertices.length; index += 3) {
            plainVertices[index] += xAmount;
            plainVertices[index + 1] += yAmount;
            plainVertices[index + 2] += zAmount;
        }
        updateDebugLines();
    }

    public final void translateX(float amount) {
        translate(amount, 0f, 0f);
    }

    public final void translateY(float amount) {
        translate(0f, amount, 0f);
    }

    public final void translateZ(float amount) {
        translate(0f, 0f, amount);
    }

    public float getYRotation() {
        return modelInstance.transform.getRotation(QUATERNION_HELPER).getAngleAround(Vector3.Y);
    }

    public final void rotate(float xDegrees, float yDegrees, float zDegrees) {
        modelInstance.transform.rotate(Vector3.X, xDegrees);
        modelInstance.transform.rotate(Vector3.Y, yDegrees);
        modelInstance.transform.rotate(Vector3.Z, zDegrees);
        for (index = 0; index < rotationVertices.length; index++) {
            rotationVertices[index].rotate(Vector3.X, xDegrees);
            rotationVertices[index].rotate(Vector3.Y, yDegrees);
            rotationVertices[index].rotate(Vector3.Z, zDegrees);
            plainVertices[index * 3] = rotationVertices[index].x + position.x;
            plainVertices[index * 3 + 1] = rotationVertices[index].y + position.y;
            plainVertices[index * 3 + 2] = rotationVertices[index].z + position.z;
        }
        updateDebugLines();
    }

    public final void rotateX(float degrees) {
        rotate(degrees, 0f, 0f);
    }

    public final void rotateY(float degrees) {
        rotate(0f, degrees, 0f);
    }

    public final void rotateZ(float degrees) {
        rotate(0f, 0f, degrees);
    }

    public final void setColor(Color color) {
        modelInstance.getMaterial("material").set(ColorAttribute.createDiffuse(color));
    }
}