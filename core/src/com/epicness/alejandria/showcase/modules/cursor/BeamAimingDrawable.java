package com.epicness.alejandria.showcase.modules.cursor;

import static com.epicness.alejandria.showcase.constants.BeamAimingConstants.BEAM_THICKNESS;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_HEIGHT;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.stuff.Sprited;

public class BeamAimingDrawable implements Drawable {

    private final Sprited triangle, beam;

    public BeamAimingDrawable(Sprite triangleSprite, Sprite beamSprite) {
        triangle = new Sprited(triangleSprite);
        triangle.setOriginCenter();
        triangle.setOriginBasedPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);
        triangle.useBilinearFilter();

        beam = new Sprited(beamSprite);
        beam.setSize(BEAM_THICKNESS);
        beam.setOriginCenter();
        beam.setOriginBasedPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);
        beam.setColor(Color.BLUE);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        spriteBatch.begin();
        triangle.draw(spriteBatch);
        beam.draw(spriteBatch);
        spriteBatch.end();
    }

    public Sprited getTriangle() {
        return triangle;
    }

    public Sprited getBeam() {
        return beam;
    }
}
