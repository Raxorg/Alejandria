package com.epicness.alejandria.showcase;

import com.epicness.alejandria.showcase.assets.ShowcaseAssets;
import com.epicness.alejandria.showcase.logic.ShowcaseLogic;
import com.epicness.alejandria.showcase.stuff.ShowcaseStuff;
import com.epicness.fundamentals.Initializer;
import com.epicness.fundamentals.SharedResources;

public class ShowcaseInitializer extends Initializer {

    public ShowcaseInitializer(ShowcaseAssets showcaseAssets) {
        super(showcaseAssets);
    }

    @Override
    public void initialize(SharedResources sharedResources) {
        logic = new ShowcaseLogic(sharedResources.getLogic());
        renderer = new ShowcaseRenderer();
        stuff = new ShowcaseStuff();
        super.initialize(sharedResources);
    }
}