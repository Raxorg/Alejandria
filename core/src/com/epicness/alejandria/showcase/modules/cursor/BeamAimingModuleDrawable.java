package com.epicness.alejandria.showcase.modules.cursor;

import static com.epicness.alejandria.showcase.constants.BeamAimingConstants.BEAM_THICKNESS;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_WIDTH;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;

public class BeamAimingModuleDrawable implements ModuleDrawable {

    private final Sprited triangle, beam;

    public BeamAimingModuleDrawable(Sprite triangleSprite, Sprite beamSprite) {
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
    public void draw(SpriteBatch spriteBatch, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        triangle.draw(spriteBatch);
        beam.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
        shapeRenderer.rect(triangle.getBoundingRectangle());
        shapeRenderer.rect(beam.getBoundingRectangle());
    }

    public Sprited getTriangle() {
        return triangle;
    }

    public Sprited getBeam() {
        return beam;
    }
}