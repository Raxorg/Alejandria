package com.epicness.alejandria.showcase.modules.kinematics;

import static com.epicness.alejandria.showcase.constants.KinematicsConstants.FK_LINES;

import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.shapes.bidimensional.ConnectedLine;

public class ForwardKinematics extends Module<ForwardKinematicsDrawable> {

    private float[] times;

    public ForwardKinematics() {
        super(
            "Forward Kinematics",
            "Wiggly! :3\n\n" +
                "This thing is made out of many interconnected segments"
        );
    }

    @Override
    protected ForwardKinematicsDrawable setup() {
        times = new float[FK_LINES];
        for (int i = 0; i < times.length; i++) {
            times[i] = i * 20f;
        }
        return new ForwardKinematicsDrawable();
    }

    @Override
    public void update(float delta) {
        ConnectedLine[] lines = drawable.getLines();
        // Wiggle
        for (int i = 0; i < lines.length; i++) {
            times[i] += 180f * delta;
            float rotation = MathUtils.sinDeg(times[i]);    // Range is -1 to 1
            float angleMargin = 25f;                        // Looks better
            // The closer to the root the less range of movement
            float min = MathUtils.map(0, FK_LINES - 1, 90f + angleMargin, 180f - angleMargin, i);
            float max = MathUtils.map(0, FK_LINES - 1, 90f - angleMargin, 0f + angleMargin, i);
            rotation = MathUtils.map(-1f, 1f, min, max, rotation);
            lines[i].setRotation(rotation);
        }
    }
}