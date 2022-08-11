package com.epicness.alejandria.showcase.logic.modules.cursor;

import static com.badlogic.gdx.Input.Keys.A;
import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.Input.Keys.S;
import static com.badlogic.gdx.Input.Keys.W;
import static com.epicness.alejandria.showcase.constants.BeamAimingConstants.TRIANGLE_SPEED;

import com.badlogic.gdx.Gdx;
import com.epicness.alejandria.showcase.logic.input.BeamAimingInput;
import com.epicness.alejandria.showcase.logic.input.ShowcaseInputHandler;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.alejandria.showcase.stuff.modules.cursor.BeamAimingDrawable;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.utils.AngleUtils;

public class BeamAiming extends Module {

    private BeamAimingDrawable drawable;
    // Logic
    private float lastCursorX, lastCursorY;

    public BeamAiming() {
        super("Beam Aiming", "Demonstration of rotating a sprite around a movable object aiming towards the cursor");
    }

    @Override
    public Drawable setup() {
        logic.handler(ShowcaseInputHandler.class).setModuleInputHandler(
                logic.handler(BeamAimingInput.class)
        );

        return drawable = new BeamAimingDrawable(sharedAssets.getTriangle(), sharedAssets.getPixel());
    }

    @Override
    public void update(float delta) {
        Sprited triangle = drawable.getTriangle();
        Sprited beam = drawable.getBeam();
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
        Sprited triangle = drawable.getTriangle();
        float triangleX = triangle.getOriginBasedX();
        float triangleY = triangle.getOriginBasedY();
        float rotation = AngleUtils.degreesBetweenPoints(lastCursorX, lastCursorY, triangleX, triangleY);
        triangle.setRotation(rotation - 90f);
        // Beam
        Sprited beam = drawable.getBeam();
        float beamX = beam.getOriginBasedX();
        float beamY = beam.getOriginBasedY();
        rotation = AngleUtils.degreesBetweenPoints(lastCursorX, lastCursorY, beamX, beamY);
        beam.setRotation(rotation);
        float distance = beam.getOriginBasedCenter().dst(lastCursorX, lastCursorY);
        beam.setWidth(distance + beam.getHeight() / 2f);
    }

    public void mouseMoved(float cursorX, float cursorY) {
        lastCursorX = cursorX;
        lastCursorY = cursorY;
    }

    @Override
    public void exit() {
        drawable = null;
        logic.handler(ShowcaseInputHandler.class).setModuleInputHandler(null);
    }
}
