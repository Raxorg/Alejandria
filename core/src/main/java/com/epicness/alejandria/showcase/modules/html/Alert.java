package com.epicness.alejandria.showcase.modules.html;

import static com.epicness.fundamentals.constants.SharedConstants.DIRT;
import static com.epicness.fundamentals.constants.SharedConstants.LIGHT_DIRT;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Rectangle;

public class Alert extends Module<AlertDrawable> {

    private NinePatch button;
    private Rectangle bounds;

    public Alert() {
        super("Alert", "Shows an HTML alert with a message\n\nOnly available on the web version");
    }

    @Override
    protected AlertDrawable setup() {
        drawable = new AlertDrawable(sharedAssets.getSquare32());
        button = drawable.getButton();
        bounds = drawable.getBounds();
        return drawable;
    }

    @Override
    public void mouseMoved(float x, float y) {
        button.setColor(LIGHT_DIRT);
        if (bounds.contains(x, y)) {
            button.setColor(DIRT);
        }
    }

    @Override
    public void touchDown(float x, float y, int button) {
        this.button.setColor(LIGHT_DIRT);
        if (bounds.contains(x, y)) {
            game.getAlertSystem().alert("You invoked an HTML native alert! :D");
        }
    }

    @Override
    public void touchUp(float x, float y) {
        button.setColor(LIGHT_DIRT);
        if (bounds.contains(x, y)) {
            button.setColor(DIRT);
        }
    }
}