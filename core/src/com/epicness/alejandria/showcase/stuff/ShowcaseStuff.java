package com.epicness.alejandria.showcase.stuff;

import com.epicness.alejandria.showcase.assets.ShowcaseAssets;
import com.epicness.fundamentals.stuff.Stuff;

public class ShowcaseStuff extends Stuff<ShowcaseAssets> {

    private Showcase showcase;

    @Override
    public void initializeStuff() {
        showcase = new Showcase(
                sharedAssets.getPixel(),
                assets.getBigPixelFont(),
                assets.getArrow(),
                assets.getInfo());
    }

    public Showcase getShowcase() {
        return showcase;
    }
}