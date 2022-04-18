package com.epicness.alejandria.showcase.logic;

import com.epicness.alejandria.showcase.ShowcaseRenderer;
import com.epicness.alejandria.showcase.assets.ShowcaseAssets;
import com.epicness.alejandria.showcase.stuff.ShowcaseStuff;
import com.epicness.fundamentals.assets.Assets;
import com.epicness.fundamentals.logic.Logic;
import com.epicness.fundamentals.logic.LogicHandler;
import com.epicness.fundamentals.renderer.Renderer;
import com.epicness.fundamentals.stuff.Stuff;

public class ShowcaseLogicHandler extends LogicHandler {

    protected ShowcaseAssets assets;
    protected ShowcaseLogic logic;
    protected ShowcaseRenderer renderer;
    protected ShowcaseStuff stuff;

    @Override
    protected void setStructure(Assets assets, Logic logic, Renderer renderer, Stuff stuff) {
        this.assets = (ShowcaseAssets) assets;
        this.logic = (ShowcaseLogic) logic;
        this.renderer = (ShowcaseRenderer) renderer;
        this.stuff = (ShowcaseStuff) stuff;
    }
}