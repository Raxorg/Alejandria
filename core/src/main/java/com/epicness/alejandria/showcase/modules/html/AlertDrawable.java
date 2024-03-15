package com.epicness.alejandria.showcase.modules.html;

import static com.epicness.alejandria.showcase.constants.HTMLConstants.ALERT_BUTTON_HEIGHT;
import static com.epicness.alejandria.showcase.constants.HTMLConstants.ALERT_BUTTON_THICKNESS;
import static com.epicness.alejandria.showcase.constants.HTMLConstants.ALERT_BUTTON_WIDTH;
import static com.epicness.alejandria.showcase.constants.HTMLConstants.ALERT_BUTTON_X;
import static com.epicness.alejandria.showcase.constants.HTMLConstants.ALERT_BUTTON_Y;
import static com.epicness.fundamentals.constants.SharedConstants.LIGHT_DIRT;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Rectangle;

public class AlertDrawable implements ModuleDrawable {

    private final NinePatch button;
    private final Rectangle bounds;

    public AlertDrawable(Sprite buttonSprite) {
        button = new NinePatch(buttonSprite, 1, 1, 1, 1);
        button.setTopHeight(ALERT_BUTTON_THICKNESS);
        button.setRightWidth(ALERT_BUTTON_THICKNESS);
        button.setBottomHeight(ALERT_BUTTON_THICKNESS);
        button.setLeftWidth(ALERT_BUTTON_THICKNESS);
        button.setColor(LIGHT_DIRT);

        bounds = new Rectangle(ALERT_BUTTON_X, ALERT_BUTTON_Y, ALERT_BUTTON_WIDTH, ALERT_BUTTON_HEIGHT);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        button.draw(spriteBatch, ALERT_BUTTON_X, ALERT_BUTTON_Y, ALERT_BUTTON_WIDTH, ALERT_BUTTON_HEIGHT);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
        shapeRenderer.rect(bounds);
    }

    public NinePatch getButton() {
        return button;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}