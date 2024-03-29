package com.epicness.alejandria.showcase.modules.viewports;

import com.epicness.alejandria.showcase.logic.Module;

public class WideViewport extends Module<WideViewportDrawable> {

    public WideViewport() {
        super(
            "Wide viewport example",
            "Uses a viewport twice the width of the device screen\n\n" +
                "A sprite is rendered to a frame buffer at an x greater than the screen width\n\n" +
                "The entire buffer texture then gets rendered to the screen scaled and centered"
        );
    }

    @Override
    protected WideViewportDrawable setup() {
        return new WideViewportDrawable(screen.getStaticCamera(), sharedAssets.getWeirdShape());
    }
}