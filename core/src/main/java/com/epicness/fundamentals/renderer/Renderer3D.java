package com.epicness.fundamentals.renderer;

import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.epicness.fundamentals.stuff.Stuff;

public abstract class Renderer3D<S extends Stuff<?>> extends Renderer<S> {

    // Structure
    protected final PerspectiveCamera perspectiveCamera;
    protected final ModelBatch modelBatch;

    public Renderer3D() {
        perspectiveCamera = new PerspectiveCamera(67f, CAMERA_WIDTH, CAMERA_HEIGHT);
        perspectiveCamera.far = 1000f;

        modelBatch = new ModelBatch();
    }

    public PerspectiveCamera getPerspectiveCamera() {
        return perspectiveCamera;
    }
}