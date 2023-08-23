package com.epicness.alejandria.showcase.modules.masking;

import static com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;

import com.epicness.alejandria.showcase.logic.Module;


public class AlphaMasking extends Module<AlphaMaskingModuleDrawable> {

    public AlphaMasking() {
        super("Alpha Masking", "The sprite mask on the left defines the transparency of each pixel of the sprite on the right\n" +
                "Resulting in the image in the middle"
        );
    }

    @Override
    public AlphaMaskingModuleDrawable setup() {
        sharedAssets.getWeirdShape().getTexture().setFilter(Linear, Linear);
        return new AlphaMaskingModuleDrawable(sharedAssets.getWeirdShape(), assets.getGlow());
    }
}