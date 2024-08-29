package com.epicness.alejandria.showcase.modules.rendering;

import com.epicness.alejandria.showcase.logic.Module;

public class ShapeRendering extends Module<ShapeRenderingDrawable> {

    public ShapeRendering() {
        super("Shape Rendering", "Some interesting stuff you can do with a ShapeRenderer");
    }

    @Override
    protected ShapeRenderingDrawable setup() {
        return new ShapeRenderingDrawable();
    }
}