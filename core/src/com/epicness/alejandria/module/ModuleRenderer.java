package com.epicness.alejandria.module;

import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.module.stuff.ModuleStuff;
import com.epicness.fundamentals.renderer.Renderer;

public class ModuleRenderer extends Renderer {

    @Override
    public void render() {
        ModuleStuff stuff = (ModuleStuff) this.stuff;

        ScreenUtils.clear(0f, 0f, 0f, 0f);
        stuff.getModuleStorage().draw(spriteBatch, shapeRenderer);
    }
}