package com.epicness.alejandria.showcase.modules.procedural;

import static com.badlogic.gdx.Input.Buttons.LEFT;
import static com.epicness.alejandria.showcase.constants.ProceduralConstants.BOLT_COLOR;
import static com.epicness.alejandria.showcase.constants.ProceduralConstants.MAX_BRANCHES;
import static com.epicness.alejandria.showcase.constants.ProceduralConstants.MIN_BRANCHES;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_HALF_SIZE;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_WIDTH;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.alejandria.showcase.stuff.modules.procedural.LightningBolt;

public class Lightning extends Module<LightningDrawable> {

    private DelayedRemovalArray<LightningBolt> bolts;
    private Sprite flash;
    private Color auxColor;
    private float flashProgress;

    public Lightning() {
        super("Lightning", "Click to spawn lightning bolts\n\nUses NinePatches\n\nFlash has a chance to trigger");
    }

    @Override
    protected LightningDrawable setup() {
        drawable = new LightningDrawable(sharedAssets.getPixel());
        bolts = drawable.getBolts();
        flash = drawable.getFlash();
        auxColor = new Color();
        flashProgress = 1f;
        float randomAngle = MathUtils.random(360f);
        float x = CAMERA_HALF_WIDTH + MathUtils.cosDeg(randomAngle) * SHOWCASE_HALF_SIZE;
        float y = CAMERA_HALF_HEIGHT + MathUtils.sinDeg(randomAngle) * SHOWCASE_HALF_SIZE;
        touchDown(x, y, LEFT);
        return drawable;
    }

    @Override
    public void update(float delta) {
        LightningBolt bolt;
        bolts.begin();
        for (int i = 0; i < bolts.size; i++) {
            bolt = bolts.get(i);
            bolt.getColor().a = Math.max(bolt.getColor().a - delta, 0f);
            if (bolt.getColor().a == 0f) {
                bolts.removeValue(bolt, true);
            }
        }
        bolts.end();
        flashProgress = Math.min(flashProgress + delta * 4f, 1f);
        auxColor.set(1f, 1f, 1f, 0.5f - Interpolation.bounceIn.apply(flashProgress));
        flash.setColor(auxColor);
    }

    @Override
    public void touchDown(float x, float y, int button) {
        createBranchedLightning(x, y);
        flashProgress = MathUtils.randomBoolean() ? 0f : 1f;
        assets.getLoudThunder().play();
    }

    private void createBranchedLightning(float x, float y) {
        LightningBolt main = new LightningBolt(assets.getBoltSegment(), CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT, x, y);
        main.setColor(BOLT_COLOR);
        bolts.add(main);
        Vector2 mainVec = new Vector2(x - CAMERA_HALF_WIDTH, y - CAMERA_HALF_HEIGHT);

        int numBranches = MathUtils.random(MIN_BRANCHES, MAX_BRANCHES);
        float[] fractions = new float[numBranches];
        for (int i = 0; i < numBranches; i++) {
            fractions[i] = MathUtils.random();
        }

        float angle = 30f;
        for (int i = 0; i < fractions.length; i++) {
            Vector2 branchStart = main.getPoint(fractions[i]);
            Vector2 branchVector = new Vector2(mainVec).scl(1 - fractions[i]);
            branchVector.rotateDeg(angle);
            angle = -angle;
            branchVector.add(branchStart);
            LightningBolt subBolt = new LightningBolt(assets.getBoltSegment(), branchStart, branchVector);
            subBolt.setColor(BOLT_COLOR);
            bolts.add(subBolt);
        }
    }
}