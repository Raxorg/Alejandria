package com.epicness.alejandria.showcase.logic.input;

import static com.badlogic.gdx.Input.Keys.NUM_1;
import static com.badlogic.gdx.Input.Keys.NUM_2;

import com.epicness.alejandria.showcase.logic.modules.pathfinding.AStar;

public class AStarInput extends ModuleInput<AStar> {

    @Override
    protected Class<AStar> setup() {
        return AStar.class;
    }

    @Override
    public void keyDown(int keycode) {
        switch (keycode) {
            case NUM_1:
                module.initialize();
                break;
            case NUM_2:
                module.toggleInterval();
                break;
        }
    }
}