package com.epicness.alejandria.showcase.logic.modules.masking;

import static com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;

import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.modules.masking.AlphaMaskingDrawable;


public class AlphaMasking extends Module<AlphaMaskingDrawable> {

    public AlphaMasking() {
        super("Alpha Masking", "The sprite mask on the left defines the transparency of each pixel of the sprite on the right\n" +
                "Resulting in the image in the middle"
        );
    }

    @Override
    public AlphaMaskingDrawable setup() {
        sharedAssets.getWeirdShape().getTexture().setFilter(Linear, Linear);
        return new AlphaMaskingDrawable(sharedAssets.getWeirdShape(), assets.getGlow());
    }
}