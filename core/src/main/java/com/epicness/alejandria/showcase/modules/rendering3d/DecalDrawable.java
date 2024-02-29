package com.epicness.alejandria.showcase.modules.rendering3d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;

public class DecalDrawable implements ModuleDrawable {

    private final PerspectiveCamera camera;
    private final DecalBatch decalBatch;
    private final Decal decal;

    public DecalDrawable(Sprite glowSprite) {
        camera = new PerspectiveCamera(90f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate(0f, 0f, 5f);

        decalBatch = new DecalBatch(new CameraGroupStrategy(camera));

        decal = Decal.newDecal(1f, 1f, glowSprite, true);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        Gdx.gl.glClear(GL20.GL_DEPTH_BUFFER_BIT);

        decalBatch.add(decal);
        decalBatch.flush();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
    }

    public PerspectiveCamera getCamera() {
        return camera;
    }
}