package com.epicness.alejandria.showcase.logic;

import com.epicness.alejandria.showcase.logic.input.ShowcaseInputHandler;
import com.epicness.alejandria.showcase.logic.modules.cursor.PointAtCursor;
import com.epicness.fundamentals.logic.Logic;
import com.epicness.fundamentals.logic.SharedLogic;

public class ShowcaseLogic extends Logic {

    private final ShowcaseHandler showcaseHandler;
    private final ShowcaseInputHandler showcaseInputHandler;

    public ShowcaseLogic(SharedLogic sharedLogic) {
        super(sharedLogic);

        registerHandler(showcaseHandler = new ShowcaseHandler());
        registerHandler(showcaseInputHandler = new ShowcaseInputHandler());
        registerHandler(new PointAtCursor());
    }

    @Override
    public void initialLogic() {
        showcaseHandler.setup();
        showcaseInputHandler.setupInput();
    }

    @Override
    public void update(float delta) {
        showcaseHandler.update(delta);
    }

    public ShowcaseHandler getModuleHandler() {
        return showcaseHandler;
    }
}