package com.epicness.alejandria.showcase.modules.patterns;

import static com.badlogic.gdx.Input.Keys.NUM_1;
import static com.badlogic.gdx.Input.Keys.NUM_2;
import static com.badlogic.gdx.Input.Keys.NUM_3;
import static com.badlogic.gdx.Input.Keys.NUM_4;
import static com.badlogic.gdx.Input.Keys.NUM_5;
import static com.badlogic.gdx.Input.Keys.NUM_6;

import com.epicness.alejandria.showcase.logic.Module;

// TODO: 8/30/2024 OPTIMIZE, IT IS TOO SLOW!
public class CantorGasket extends Module<CantorGasketDrawable> {

    public CantorGasket() {
        super("Cantor Gasket", "This gasket pertains to Cantor apparently\n\nPress 1 through 6 for different levels of recursion");
    }

    @Override
    protected CantorGasketDrawable setup() {
        return new CantorGasketDrawable();
    }

    @Override
    public void keyDown(int keycode) {
        switch (keycode) {
            case NUM_1:
                drawable.setRecursions(1);
                break;
            case NUM_2:
                drawable.setRecursions(2);
                break;
            case NUM_3:
                drawable.setRecursions(3);
                break;
            case NUM_4:
                drawable.setRecursions(4);
                break;
            case NUM_5:
                drawable.setRecursions(5);
                break;
            case NUM_6:
                drawable.setRecursions(6);
                break;
        }
    }
}