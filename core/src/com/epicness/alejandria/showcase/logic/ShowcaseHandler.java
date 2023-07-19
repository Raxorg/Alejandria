package com.epicness.alejandria.showcase.logic;

import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.Input.Keys.G;
import static com.badlogic.gdx.Input.Keys.I;
import static com.badlogic.gdx.Input.Keys.LEFT;
import static com.badlogic.gdx.Input.Keys.RIGHT;
import static com.badlogic.gdx.graphics.Color.CHARTREUSE;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.epicness.fundamentals.SharedConstants.BLACK_CLEAR_75;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.epicness.fundamentals.logic.LogicHandler;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.stuff.SpritedText;

import java.util.ArrayList;
import java.util.List;

public class ShowcaseHandler extends ShowcaseLogicHandler {

    private List<Module<?>> modules;
    private Module<?> currentModule;
    private boolean showingInformation;
    public static boolean debug;
    // Stuff
    private Sprited previous, gitHub, info, next;

    @Override
    public void init() {
        input.clearInputHandlers();

        previous = stuff.getPrevious();
        gitHub = stuff.getGitHubButton();
        info = stuff.getInfoButton();
        next = stuff.getNext();

        modules = new ArrayList<>();
        for (int i = 0; i < logic.getHandlers().size(); i++) {
            LogicHandler<?, ?, ?, ?> handler = logic.getHandlers().get(i);
            if (handler instanceof Module) {
                modules.add((Module<?>) handler);
            }
        }
        changeModule(0);
        showingInformation = false;
        debug = false;
    }

    public void update(float delta) {
        currentModule.update(delta);
    }

    @Override
    public void mouseMoved(float x, float y) {
        previous.setColor(WHITE);
        gitHub.setColor(WHITE);
        info.setColor(WHITE);
        next.setColor(WHITE);
        if (previous.contains(x, y)) previous.setColor(CHARTREUSE);
        else if (info.contains(x, y)) info.setColor(CHARTREUSE);
        else if (gitHub.contains(x, y)) gitHub.setColor(CHARTREUSE);
        else if (next.contains(x, y)) next.setColor(CHARTREUSE);
    }

    @Override
    public void touchUp(float x, float y) {
        int currentIndex = modules.indexOf(currentModule);
        if (previous.contains(x, y))
            changeModule(currentIndex == 0 ? modules.size() - 1 : currentIndex - 1);
        else if (next.contains(x, y))
            changeModule(currentIndex == modules.size() - 1 ? 0 : currentIndex + 1);
        else if (gitHub.contains(x, y))
            openGitHub();
        else if (info.contains(x, y))
            toggleInformation();
        else
            hideInformation();
    }

    @Override
    public void keyDown(int keycode) {
        int currentIndex = modules.indexOf(currentModule);
        switch (keycode) {
            case LEFT:
                changeModule(currentIndex == 0 ? modules.size() - 1 : currentIndex - 1);
                return;
            case RIGHT:
                changeModule(currentIndex == modules.size() - 1 ? 0 : currentIndex + 1);
                return;
            case I:
                toggleInformation();
                return;
            case G:
                openGitHub();
                return;
            case D:
                toggleDebug();
                break;
        }
    }

    private void changeModule(int index) {
        if (currentModule != null) {
            currentModule.exitModule();
        }
        currentModule = modules.get(index);
        stuff.getShowcase().setModuleDrawable(currentModule.setupModule());
        stuff.getTitle().setText(currentModule.title);
        stuff.getInformation().setText(currentModule.information);
        hideInformation();
    }

    public void openGitHub() {
        Gdx.net.openURI(currentModule.gitHubPath);
    }

    public void toggleInformation() {
        if (showingInformation) {
            hideInformation();
        } else {
            showInformation();
        }
    }

    private void showInformation() {
        SpritedText information = stuff.getInformation();
        if (information.getBackgroundColor().equals(BLACK_CLEAR_75)) {
            hideInformation();
            return;
        }
        information.setBackgroundColor(BLACK_CLEAR_75);
        information.setTextColor(WHITE);
        showingInformation = true;
    }

    private void hideInformation() {
        SpritedText information = stuff.getInformation();
        information.setBackgroundColor(Color.CLEAR);
        information.setTextColor(Color.CLEAR);
        showingInformation = false;
    }

    public void toggleDebug() {
        debug = !debug;
    }
}