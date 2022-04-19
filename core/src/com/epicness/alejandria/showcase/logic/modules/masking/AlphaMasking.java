package com.epicness.alejandria.showcase.logic.modules.masking;

import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.alejandria.showcase.stuff.modules.masking.AlphaMaskingDrawable;


public class AlphaMasking extends Module {

    private AlphaMaskingDrawable drawable;

    public AlphaMasking() {
        super("Alpha Masking");
    }

    @Override
    public Drawable setup() {
        return drawable = new AlphaMaskingDrawable(sharedAssets.getWeirdShape(), assets.getGlow());
    }

    @Override
    public void exit() {
        drawable = null;
    }
}