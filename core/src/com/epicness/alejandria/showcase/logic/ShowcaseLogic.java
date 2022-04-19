package com.epicness.alejandria.showcase.logic;

import com.epicness.alejandria.showcase.logic.input.ShowcaseInputHandler;
import com.epicness.alejandria.showcase.logic.modules.animations.SpriteAnimation;
import com.epicness.alejandria.showcase.logic.modules.animations.SpriteRotationAnimation;
import com.epicness.alejandria.showcase.logic.modules.cursor.PointAtCursor;
import com.epicness.fundamentals.logic.Logic;
import com.epicness.fundamentals.logic.SharedLogic;

public class ShowcaseLogic extends Logic {

    private final ShowcaseHandler showcaseHandler;

    public ShowcaseLogic(SharedLogic sharedLogic) {
        super(sharedLogic);

        registerHandler(showcaseHandler = new ShowcaseHandler());
        registerHandler(new ShowcaseInputHandler());
        // Modules
        registerHandler(new SpriteAnimation());
        registerHandler(new SpriteRotationAnimation());
        registerHandler(new PointAtCursor());
    }

    @Override
    public void update(float delta) {
        showcaseHandler.update(delta);
    }
}