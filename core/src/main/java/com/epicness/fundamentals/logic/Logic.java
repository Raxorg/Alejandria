package com.epicness.fundamentals.logic;

import com.badlogic.gdx.Game;
import com.epicness.fundamentals.SharedScreen;
import com.epicness.fundamentals.assets.Assets;
import com.epicness.fundamentals.assets.SharedAssets;
import com.epicness.fundamentals.input.LogicInputHandler;
import com.epicness.fundamentals.input.SharedInput;
import com.epicness.fundamentals.renderer.Renderer;
import com.epicness.fundamentals.stuff.SharedStuff;
import com.epicness.fundamentals.stuff.Stuff;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class Logic {

    protected SharedLogic sharedLogic;
    private final List<LogicHandler> logicHandlers;

    protected Logic() {
        logicHandlers = new ArrayList<>();
    }

    public void initialLogic() {
        for (LogicHandler logicHandler : logicHandlers) {
            logicHandler.init();
            if (logicHandler instanceof LogicInputHandler)
                ((LogicInputHandler) logicHandler).register();
        }
    }

    public void restart() {
        for (int i = 0; i < logicHandlers.size(); i++) {
            logicHandlers.get(i).init();
        }
    }

    public abstract void update();

    public abstract void resize(int width, int height);

    public void pause() {
    }

    public void registerHandler(LogicHandler logicHandler) {
        logicHandlers.add(logicHandler);
    }

    public void registerHandler(int index, LogicHandler logicHandler) {
        logicHandlers.add(index, logicHandler);
    }

    public void setStructure(
        Game game,
        SharedAssets sharedAssets,
        SharedInput input,
        SharedLogic sharedLogic,
        SharedScreen screen,
        SharedStuff sharedStuff,
        Assets assets,
        Renderer renderer,
        Stuff stuff
    ) {
        this.sharedLogic = sharedLogic;
        for (LogicHandler logicHandler : logicHandlers) {
            logicHandler.setSharedStructure(game, sharedAssets, input, sharedLogic, screen, sharedStuff);
            logicHandler.setStructure(assets, this, renderer, stuff);
        }
    }

    public List<LogicHandler> getHandlers() {
        return logicHandlers;
    }

    public <H extends LogicHandler> H get(Class<H> handlerClass) {
        for (LogicHandler logicHandler : logicHandlers) {
            if (logicHandler.getClass().equals(handlerClass)) {
                return (H) logicHandler;
            }
        }
        throw new NoSuchElementException("No handler of class \"" + handlerClass.getSimpleName() + "\" registered");
    }
}