package com.epicness.fundamentals.renderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.SharedScreen;
import com.epicness.fundamentals.stuff.Stuff;

public abstract class Renderer {

    // Structure
    protected SharedScreen screen;
    protected Stuff stuff;
    // Rendering related
    protected final SpriteBatch spriteBatch;

    public Renderer() {
        spriteBatch = new SpriteBatch();
    }

    public void setProjectionMatrix() {
        spriteBatch.setProjectionMatrix(screen.getStaticCamera().combined);
    }

    public abstract void render();

    // Structure
    public void setScreen(SharedScreen screen) {
        this.screen = screen;
    }

    public void setStuff(Stuff stuff) {
        this.stuff = stuff;
    }
}