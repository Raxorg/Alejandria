package com.epicness.fundamentals.renderer;

import static com.epicness.fundamentals.assets.SharedAssetPaths.PIXEL_SPRITE;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.SharedScreen;
import com.epicness.fundamentals.stuff.SharedStuff;
import com.epicness.fundamentals.stuff.Stuff;

public abstract class Renderer<S extends Stuff<?>> {

    // Structure
    protected SharedScreen screen;
    protected SharedStuff sharedStuff;
    protected S stuff;
    // Rendering related
    protected final SpriteBatch spriteBatch;
    protected final ShapeRendererPlus shapeRenderer;
    protected final ShapeDrawerPlus shapeDrawer;

    public Renderer() {
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRendererPlus();
        shapeRenderer.setAutoShapeType(true);
        shapeDrawer = new ShapeDrawerPlus(spriteBatch, new Sprite(new Texture(PIXEL_SPRITE.fileName)));
    }

    public abstract void render();

    public void useStaticCamera() {
        spriteBatch.setProjectionMatrix(screen.getStaticCamera().combined);
        shapeRenderer.setProjectionMatrix(screen.getStaticCamera().combined);
    }

    public void useDynamicCamera() {
        spriteBatch.setProjectionMatrix(screen.getDynamicCamera().combined);
        shapeRenderer.setProjectionMatrix(screen.getDynamicCamera().combined);
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