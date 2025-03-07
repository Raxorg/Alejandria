package com.epicness.alejandria.showcase.modules.procedural;

import static com.badlogic.gdx.graphics.Pixmap.Format.RGBA8888;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_X;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_Y;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.PixmapTextureData;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;

public class PixmapManipulationDrawable implements ModuleDrawable {

    private final Pixmap pixmap;
    private final Texture texture;
    private final PixmapTextureData pixmapTextureData;

    public PixmapManipulationDrawable() {
        pixmap = new Pixmap((int) SHOWCASE_SIZE, (int) SHOWCASE_SIZE, RGBA8888);
        texture = new Texture(pixmap);
        pixmapTextureData = new PixmapTextureData(pixmap, null, false, false);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        spriteBatch.draw(texture, SHOWCASE_X, SHOWCASE_Y);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeDrawerPlus shapeDrawer) {

    }

    public Texture getTexture() {
        return texture;
    }

    public Pixmap getPixmap() {
        return pixmap;
    }

    public PixmapTextureData getPixmapTextureData() {
        return pixmapTextureData;
    }
}