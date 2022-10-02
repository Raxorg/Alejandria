package com.epicness.alejandria.showcase.logic;

import static com.epicness.fundamentals.SharedConstants.BLACK_CLEAR_25;

import com.badlogic.gdx.graphics.Color;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.fundamentals.logic.LogicHandler;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.stuff.SpritedText;

import java.util.ArrayList;
import java.util.List;

public class ShowcaseHandler extends ShowcaseLogicHandler {

    // Logic
    private List<Module<?>> modules;
    private Module<?> currentModule;

    @Override
    public void init() {
        modules = new ArrayList<>();
        for (int i = 0; i < logic.getHandlers().size(); i++) {
            LogicHandler<?, ?, ?, ?> handler = logic.getHandlers().get(i);
            if (handler instanceof Module) {
                modules.add((Module<?>) handler);
            }
        }
        changeModule(0);
    }

    public void update(float delta) {
        currentModule.update(delta);
    }

    public void mouseMoved(float x, float y) {
        Sprited previous = stuff.getShowcase().getPrevious();
        Sprited info = stuff.getShowcase().getInfoButton();
        Sprited next = stuff.getShowcase().getNext();
        previous.setColor(Color.WHITE);
        info.setColor(Color.WHITE);
        next.setColor(Color.WHITE);
        if (previous.contains(x, y)) {
            previous.setColor(Color.CHARTREUSE);
        } else if (info.contains(x, y)) {
            info.setColor(Color.CHARTREUSE);
        } else if (next.contains(x, y)) {
            next.setColor(Color.CHARTREUSE);
        }
    }

    public void touchDown(float x, float y) {
        Sprited previous = stuff.getShowcase().getPrevious();
        Sprited info = stuff.getShowcase().getInfoButton();
        Sprited next = stuff.getShowcase().getNext();
        int currentIndex = modules.indexOf(currentModule);
        if (previous.contains(x, y)) {
            changeModule(currentIndex == 0 ? modules.size() - 1 : currentIndex - 1);
        } else if (next.contains(x, y)) {
            changeModule(currentIndex == modules.size() - 1 ? 0 : currentIndex + 1);
        } else if (info.contains(x, y)) {
            showInformation();
        } else {
            hideInformation();
        }
    }

    public void keyDown(boolean left) {
        int currentIndex = modules.indexOf(currentModule);
        if (left) {
            changeModule(currentIndex == 0 ? modules.size() - 1 : currentIndex - 1);
        } else {
            changeModule(currentIndex == modules.size() - 1 ? 0 : currentIndex + 1);
        }
    }

    private void changeModule(int index) {
        if (currentModule != null) {
            currentModule.exitModule();
        }
        currentModule = modules.get(index);
        stuff.getShowcase().setDrawable(currentModule.setup());
        stuff.getShowcase().setTitle(currentModule.getTitle());
        stuff.getShowcase().getInformation().setText(currentModule.getInformation());
        hideInformation();
    }

    private void showInformation() {
        SpritedText information = stuff.getShowcase().getInformation();
        if (information.getBackgroundColor().toFloatBits() == BLACK_CLEAR_25.toFloatBits()) {
            hideInformation();
            return;
        }
        information.setBackgroundColor(BLACK_CLEAR_25);
        information.setTextColor(Color.WHITE);
    }

    private void hideInformation() {
        SpritedText information = stuff.getShowcase().getInformation();
        information.setBackgroundColor(Color.CLEAR);
        information.setTextColor(Color.CLEAR);
    }
}