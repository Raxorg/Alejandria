package com.epicness.alejandria.showcase.logic.meta;

import com.epicness.alejandria.showcase.assets.ShowcaseAssets;
import com.epicness.alejandria.showcase.stuff.modules.cursor.PointAtCursorDrawable;
import com.epicness.fundamentals.assets.SharedAssets;

public class ModuleManager {

    // Structure
    private SharedAssets sharedAssets;
    private ShowcaseAssets assets;
    // Modules
    private PointAtCursorDrawable pointAtCursorDrawable;

    public void init() {
        pointAtCursorDrawable = new PointAtCursorDrawable(sharedAssets.getTriangle());
    }

    public PointAtCursorDrawable getPointAtCursor() {
        return pointAtCursorDrawable;
    }

    // Structure    +
    public void setSharedAssets(SharedAssets sharedAssets) {
        this.sharedAssets = sharedAssets;
    }

    public void setAssets(ShowcaseAssets assets) {
        this.assets = assets;
    }
}