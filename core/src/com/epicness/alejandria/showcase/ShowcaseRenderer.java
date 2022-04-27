package com.epicness.alejandria.showcase;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.stuff.ShowcaseStuff;
import com.epicness.fundamentals.renderer.Renderer;

public class ShowcaseRenderer extends Renderer {

    @Override
    public void render() {
        ShowcaseStuff stuff = (ShowcaseStuff) this.stuff;

        ScreenUtils.clear(Color.CLEAR);
        stuff.getShowcase().draw(spriteBatch, shapeRenderer);
    }
}