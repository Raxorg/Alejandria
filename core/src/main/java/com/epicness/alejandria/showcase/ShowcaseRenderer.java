package com.epicness.alejandria.showcase;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_BACKGROUND_COLOR;
import static com.epicness.alejandria.showcase.logic.ShowcaseHandler.debug;

import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.stuff.ShowcaseStuff;
import com.epicness.fundamentals.renderer.Renderer;

public class ShowcaseRenderer extends Renderer<ShowcaseStuff> {

    public boolean clearScreen = true;

    @Override
    public void render() {
        if (clearScreen) ScreenUtils.clear(SHOWCASE_BACKGROUND_COLOR);

        stuff.getShowcase().draw(spriteBatch, shapeRenderer);

        spriteBatch.begin();
        stuff.getTitle().draw(spriteBatch);
        stuff.getBottomStripe().draw(spriteBatch);
        stuff.getPrevious().draw(spriteBatch);
        stuff.getGitHubButton().draw(spriteBatch);
        stuff.getInfoButton().draw(spriteBatch);
        stuff.getNext().draw(spriteBatch);
        stuff.getInformation().draw(spriteBatch);
        spriteBatch.end();

        if (debug) renderDebug();
    }

    public void renderDebug() {
        shapeRenderer.begin();
        stuff.getShowcase().drawDebug(shapeRenderer);
        shapeRenderer.end();
    }
}