package com.epicness.alejandria.showcase.logic;

import com.badlogic.gdx.graphics.Color;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.logic.modules.animations.SpriteAnimation;
import com.epicness.alejandria.showcase.logic.modules.animations.SpriteRotationAnimation;
import com.epicness.alejandria.showcase.logic.modules.cursor.PointAtCursor;
import com.epicness.fundamentals.stuff.Sprited;

import java.util.ArrayList;
import java.util.List;

public class ShowcaseHandler extends ShowcaseLogicHandler {

    // Logic
    private List<Module> modules;
    private Module currentModule;

    @Override
    public void init() {
        modules = new ArrayList<>();
        modules.add((Module) logic.getHandler(SpriteAnimation.class));
        modules.add((Module) logic.getHandler(SpriteRotationAnimation.class));
        modules.add((Module) logic.getHandler(PointAtCursor.class));

        changeModule(0);
    }

    public void update(float delta) {
        currentModule.update(delta);
    }

    public void mouseMoved(float x, float y) {
        Sprited previous = stuff.getShowcase().getPrevious();
        Sprited next = stuff.getShowcase().getNext();
        previous.setColor(Color.WHITE);
        next.setColor(Color.WHITE);
        if (previous.contains(x, y)) {
            previous.setColor(Color.CHARTREUSE);
        } else if (next.contains(x, y)) {
            next.setColor(Color.CHARTREUSE);
        }
    }

    public void touchDown(float x, float y) {
        Sprited previous = stuff.getShowcase().getPrevious();
        Sprited next = stuff.getShowcase().getNext();
        int currentIndex = modules.indexOf(currentModule);
        if (previous.contains(x, y)) {
            changeModule(currentIndex == 0 ? modules.size() - 1 : currentIndex - 1);
        } else if (next.contains(x, y)) {
            changeModule(currentIndex == modules.size() - 1 ? 0 : currentIndex + 1);
        }
    }

    private void changeModule(int index) {
        if (currentModule != null) {
            currentModule.exit();
        }
        currentModule = modules.get(index);
        stuff.getShowcase().setDrawable(currentModule.setup());
        stuff.getShowcase().setTitle(currentModule.getTitle());
    }
}