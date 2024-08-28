package com.epicness.alejandria.showcase.modules.fun;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.BLUE;
import static com.badlogic.gdx.graphics.Color.CYAN;
import static com.epicness.fundamentals.constants.ColorConstants.GRASS;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_WIDTH;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.alejandria.showcase.stuff.modules.fun.Laser;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;

public class LasersDrawable implements ModuleDrawable {

    private final Laser laser1, laser2, laser3, laser4;

    public LasersDrawable(Sprite beamStart, Sprite beamMid, Sprite beamEnd,
                          Sprite beamStartGlow, Sprite beamMidGlow, Sprite beamEndGlow) {
        laser1 = new Laser(beamStart, beamMid, beamEnd, beamStartGlow, beamMidGlow, beamEndGlow);
        laser1.setPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);
        laser1.setHeight(400f);

        laser2 = new Laser(beamStart, beamMid, beamEnd, beamStartGlow, beamMidGlow, beamEndGlow);
        laser2.setPosition(CAMERA_HALF_WIDTH / 2f, CAMERA_HALF_HEIGHT);
        laser2.rotate(45f);
        laser2.setColor(CYAN);

        laser3 = new Laser(beamStart, beamMid, beamEnd, beamStartGlow, beamMidGlow, beamEndGlow);
        laser3.setPosition(CAMERA_HALF_WIDTH * 1.5f, CAMERA_HALF_HEIGHT * 1.5f);
        laser3.setHeight(500f);
        laser3.rotate(180f);
        laser3.setColor(GRASS);

        laser4 = new Laser(beamStart, beamMid, beamEnd, beamStartGlow, beamMidGlow, beamEndGlow);
        laser4.setPosition(CAMERA_HALF_WIDTH / 2f, CAMERA_HALF_HEIGHT / 2f);
        laser4.setHeight(500f);
        laser4.rotate(270f);
        laser4.setColor(BLUE);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        ScreenUtils.clear(BLACK);
        spriteBatch.begin();
        laser1.draw(spriteBatch);
        laser2.draw(spriteBatch);
        laser3.draw(spriteBatch);
        laser4.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
        laser1.drawDebug(shapeRenderer);
        laser2.drawDebug(shapeRenderer);
        laser3.drawDebug(shapeRenderer);
        laser4.drawDebug(shapeRenderer);
    }

    public Laser getLaser1() {
        return laser1;
    }
}