package com.epicness.alejandria.showcase.modules.cursor;

import static com.badlogic.gdx.Input.Keys.A;
import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.Input.Keys.S;
import static com.badlogic.gdx.Input.Keys.W;
import static com.epicness.alejandria.showcase.constants.BeamAimingConstants.TRIANGLE_SPEED;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.SpritePlus;
import com.epicness.fundamentals.utils.AngleUtils;

public class BeamAiming extends Module<BeamAimingDrawable> {

    private float lastCursorX = VIEWPORT_HALF_WIDTH, lastCursorY = VIEWPORT_HALF_HEIGHT;
    private Vector2 beamCenter;

    public BeamAiming() {
        super(
            "Beam Aiming",
            "The sprite in blue has dynamic width\n\n" +
                "Rotation is calculated so that one end of it is anchored to the triangle " +
                "and the other end to the cursor\n\n" +
                "WASD to move the triangle"
        );
    }

    @Override
    public BeamAimingDrawable setup() {
        beamCenter = new Vector2();
        return new BeamAimingDrawable(sharedAssets.getTriangle(), sharedAssets.getPixel());
    }

    @Override
    public void update(float delta) {
        SpritePlus triangle = drawable.getTriangle();
        SpritePlus beam = drawable.getBeam();
        float translation = TRIANGLE_SPEED * delta;
        if (Gdx.input.isKeyPressed(W)) {
            triangle.translateY(translation);
            beam.translateY(translation);
        }
        if (Gdx.input.isKeyPressed(A)) {
            triangle.translateX(-translation);
            beam.translateX(-translation);
        }
        if (Gdx.input.isKeyPressed(S)) {
            triangle.translateY(-translation);
            beam.translateY(-translation);
        }
        if (Gdx.input.isKeyPressed(D)) {
            triangle.translateX(translation);
            beam.translateX(translation);
        }
        updateBeam();
    }

    private void updateBeam() {
        // Triangle
        SpritePlus triangle = drawable.getTriangle();
        float triangleX = triangle.getOriginBasedX();
        float triangleY = triangle.getOriginBasedY();
        float rotation = AngleUtils.degreesBetweenPoints(lastCursorX, lastCursorY, triangleX, triangleY);
        triangle.setRotation(rotation - 90f);
        // Beam
        SpritePlus beam = drawable.getBeam();
        float beamX = beam.getOriginBasedX();
        float beamY = beam.getOriginBasedY();
        rotation = AngleUtils.degreesBetweenPoints(lastCursorX, lastCursorY, beamX, beamY);
        beam.setRotation(rotation);
        float distance = beam.getOriginBasedCenter(beamCenter).dst(lastCursorX, lastCursorY);
        beam.setWidth(distance + beam.getHeight() * 0.5f);
    }

    @Override
    public void mouseMoved(float cursorX, float cursorY) {
        lastCursorX = cursorX;
        lastCursorY = cursorY;
    }
}