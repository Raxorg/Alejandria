package com.epicness.alejandria.showcase.logic.modules.masking;

import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.modules.masking.AlphaMaskingDrawable;


public class AlphaMasking extends Module<AlphaMaskingDrawable> {

    public AlphaMasking() {
        super("Alpha Masking", "The mask defines the transparency of each pixel of the sprite");
    }

    @Override
    public AlphaMaskingDrawable setup() {
        drawable = new AlphaMaskingDrawable(sharedAssets.getWeirdShape(), assets.getGlow());
        return drawable;
    }
}