package com.epicness.alejandria.showcase.stuff;

import com.epicness.fundamentals.stuff.Stuff;

public class ShowcaseStuff extends Stuff {

    private Showcase showcase;

    @Override
    public void initializeStuff() {
        showcase = new Showcase(sharedAssets.getPixel(), sharedAssets.getPixelFont());
    }

    public Showcase getShowcase() {
        return showcase;
    }
}