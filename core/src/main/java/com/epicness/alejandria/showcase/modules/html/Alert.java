package com.epicness.alejandria.showcase.modules.html;

import static com.epicness.fundamentals.constants.ColorConstants.DIRT;
import static com.epicness.fundamentals.constants.ColorConstants.LIGHT_DIRT;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Rectangle;

public class Alert extends Module<AlertDrawable> {

    private NinePatch alertButton;
    private Rectangle bounds;

    public Alert() {
        super("Alert", "Shows an HTML alert with a message\n\nOnly available on the web version");
    }

    @Override
    protected AlertDrawable setup() {
        drawable = new AlertDrawable(sharedAssets.getSquare32());
        alertButton = drawable.getButton();
        bounds = drawable.getBounds();
        return drawable;
    }

    @Override
    public void mouseMoved(float x, float y) {
        alertButton.setColor(LIGHT_DIRT);
        if (bounds.contains(x, y)) {
            alertButton.setColor(DIRT);
        }
    }

    @Override
    public void touchDown(float x, float y, int button) {
        this.alertButton.setColor(LIGHT_DIRT);
        if (bounds.contains(x, y)) {
            game.getAlertSystem().alert("You invoked an HTML native alert! :D");
        }
    }

    @Override
    public void touchUp(float x, float y, int button) {
        alertButton.setColor(LIGHT_DIRT);
        if (bounds.contains(x, y)) {
            alertButton.setColor(DIRT);
        }
    }
}