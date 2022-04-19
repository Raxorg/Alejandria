package com.epicness.alejandria.showcase.stuff;

import com.epicness.alejandria.showcase.assets.ShowcaseAssets;
import com.epicness.fundamentals.stuff.Stuff;

public class ShowcaseStuff extends Stuff {

    private Showcase showcase;

    @Override
    public void initializeStuff() {
        ShowcaseAssets assets = (ShowcaseAssets) this.assets;
        showcase = new Showcase(sharedAssets.getPixel(), assets.getBigPixelFont(), assets.getArrow());
    }

    public Showcase getShowcase() {
        return showcase;
    }
}