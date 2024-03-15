package com.epicness.alejandria.showcase.modules.procedural;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.CLEAR;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.alejandria.showcase.stuff.modules.procedural.LightningBolt;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;

public class LightningDrawable implements ModuleDrawable {

    private final DelayedRemovalArray<LightningBolt> bolts;
    private final Sprite flash;

    public LightningDrawable(Sprite pixel) {
        bolts = new DelayedRemovalArray<>();
        flash = new Sprite(pixel);
        flash.setSize(CAMERA_WIDTH, CAMERA_HEIGHT);
        flash.setColor(CLEAR);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        ScreenUtils.clear(BLACK);
        spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        spriteBatch.begin();
        for (int i = 0; i < bolts.size; i++) {
            bolts.get(i).draw(spriteBatch);
        }
        flash.draw(spriteBatch);
        spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {

    }

    public DelayedRemovalArray<LightningBolt> getBolts() {
        return bolts;
    }

    public Sprite getFlash() {
        return flash;
    }
}