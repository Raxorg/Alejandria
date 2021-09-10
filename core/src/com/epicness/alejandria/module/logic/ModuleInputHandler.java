package com.epicness.alejandria.module.logic;

import com.epicness.fundamentals.input.InputHandler;

import static com.badlogic.gdx.Input.Keys.NUM_1;
import static com.badlogic.gdx.Input.Keys.NUM_2;
import static com.epicness.alejandria.ModuleID.LAYERED_MASKING;
import static com.epicness.alejandria.ModuleID.MASKING;

public class ModuleInputHandler extends InputHandler {

    @Override
    public void keyDown(int keycode) {
        ModuleLogic logic = (ModuleLogic) this.logic;
        switch (keycode) {
            case NUM_1:
                logic.getModuleHandler().changeModule(LAYERED_MASKING);
                break;
            case NUM_2:
                logic.getModuleHandler().changeModule(MASKING);
                break;
        }
    }
}