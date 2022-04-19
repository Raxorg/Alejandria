package com.epicness.alejandria.showcase.logic.modules.rendering;

import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.alejandria.showcase.stuff.modules.rendering.OrthographicExampleDrawable;

public class OrthographicExample extends Module {

    private OrthographicExampleDrawable drawable;

    public OrthographicExample() {
        super("Orthographic Example");
    }

    @Override
    public Drawable setup() {
        return drawable = new OrthographicExampleDrawable(sharedAssets.getWeirdShape());
    }

    @Override
    public void exit() {
        drawable = null;
    }
}