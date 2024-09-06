package com.epicness.fundamentals.renderer;

import static com.epicness.fundamentals.assets.SharedAssetPaths.SPRITESNEAREST_ATLAS;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_WIDTH;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.epicness.fundamentals.SharedScreen;
import com.epicness.fundamentals.stuff.SharedStuff;
import com.epicness.fundamentals.stuff.Stuff;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Drawable2D;

public abstract class Renderer<S extends Stuff<?>> {

    // Structure
    protected SharedScreen screen;
    protected SharedStuff sharedStuff;
    protected S stuff;
    // Rendering related
    protected final ExtendViewport viewport;
    protected final SpriteBatch spriteBatch;
    protected final ShapeRendererPlus shapeRenderer;
    protected final ShapeDrawerPlus shapeDrawer;

    public Renderer() {
        viewport = new ExtendViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRendererPlus();
        shapeRenderer.setAutoShapeType(true);
        shapeDrawer = new ShapeDrawerPlus(spriteBatch, new TextureAtlas(SPRITESNEAREST_ATLAS.fileName).createSprite("pixel"));
    }

    public abstract void render();

    public void useCamera(Camera camera) {
        viewport.setCamera(camera);
        viewport.apply();
        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    public void useStaticCamera() {
        useCamera(screen.getStaticCamera());
    }

    public void useDynamicCamera() {
        useCamera(screen.getDynamicCamera());
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public ShapeRendererPlus getShapeRenderer() {
        return shapeRenderer;
    }

    public ShapeDrawerPlus getShapeDrawer() {
        return shapeDrawer;
    }

    protected void drawArray(Array<? extends Drawable2D> array) {
        for (int i = 0; i < array.size; i++) {
            array.get(i).draw(spriteBatch, shapeDrawer);
        }
    }

    public ExtendViewport getViewport() {
        return viewport;
    }

    // Structure
    public void setScreen(SharedScreen screen) {
        this.screen = screen;
    }

    public void setSharedStuff(SharedStuff sharedStuff) {
        this.sharedStuff = sharedStuff;
    }

    public void setStuff(S stuff) {
        this.stuff = stuff;
    }
}