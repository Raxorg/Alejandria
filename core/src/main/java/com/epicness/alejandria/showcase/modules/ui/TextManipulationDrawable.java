package com.epicness.alejandria.showcase.modules.ui;

import static com.epicness.alejandria.showcase.constants.UIConstants.TEXT_MANIPULATION_BASE_TEXT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_WIDTH;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.Text;

public class TextManipulationDrawable implements ModuleDrawable {

    private final Text text;

    public TextManipulationDrawable(BitmapFont font) {
        text = new Text(font);
        text.setText(TEXT_MANIPULATION_BASE_TEXT);
        text.setY(VIEWPORT_HALF_HEIGHT);
        text.setWidth(VIEWPORT_WIDTH);
        text.hAlignCenter();
        text.setVerticallyCentered(true);
        text.setScale(5f);
        text.setTruncate(null);
        text.setWrap(true);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        text.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeDrawerPlus shapeDrawer) {
        text.drawDebug(shapeDrawer);
    }

    public Text getText() {
        return text;
    }
}