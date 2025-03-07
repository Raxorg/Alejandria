package com.epicness.alejandria.showcase;

import com.epicness.alejandria.showcase.assets.ShowcaseAssets;
import com.epicness.alejandria.showcase.logic.ShowcaseLogic;
import com.epicness.alejandria.showcase.stuff.ShowcaseStuff;
import com.epicness.fundamentals.initializer.Initializer;

public class ShowcaseInitializer extends Initializer<ShowcaseAssets, ShowcaseLogic, ShowcaseRenderer, ShowcaseStuff> {

    public ShowcaseInitializer(ShowcaseAssets showcaseAssets) {
        super(showcaseAssets, new ShowcaseLogic(), new ShowcaseRenderer(), new ShowcaseStuff());
    }
}