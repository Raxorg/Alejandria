package com.epicness.alejandria.showcase.modules.grids;

import static com.epicness.alejandria.showcase.modules.grids.HexagonSelectionDrawable.COLUMNS;
import static com.epicness.alejandria.showcase.modules.grids.HexagonSelectionDrawable.ROWS;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.alejandria.showcase.stuff.modules.grids.Hexagon;

import java.util.HashSet;
import java.util.Set;

public class HexagonSelection extends Module<HexagonSelectionDrawable> {

    private int radius;

    public HexagonSelection() {
        super("Hexagon selection", "Select a hexagon\npress 1 to 4 to change the radius");
    }

    @Override
    protected HexagonSelectionDrawable setup() {
        radius = 1;
        Gdx.gl.glLineWidth(3f);
        return new HexagonSelectionDrawable();
    }

    @Override
    public void touchDown(float x, float y) {
        Hexagon[][] hexagons = drawable.getHexagons();
        Hexagon hexagon;
        for (int column = 0; column < COLUMNS; column++) {
            for (int row = 0; row < ROWS; row++) {
                hexagon = hexagons[column][row];
                if (hexagon.contains(x, y)) {
                    for (Hexagon neighbor : collectHexagons(hexagon)) {
                        float scale = neighbor.getScaleX() == 0.5f ? 1f : 0.5f;
                        neighbor.setScale(scale, scale);
                    }
                    return;
                }
            }
        }
    }

    @Override
    public void keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.NUM_1:
                radius = 1;
                break;
            case Input.Keys.NUM_2:
                radius = 2;
                break;
            case Input.Keys.NUM_3:
                radius = 3;
                break;
            case Input.Keys.NUM_4:
                radius = 4;
                break;
        }
    }

    @Override
    protected void exit() {
        Gdx.gl.glLineWidth(1f);
    }

    private Set<Hexagon> collectHexagons(Hexagon startingHexagon) {
        Set<Hexagon> hexagons = new HashSet<>();
        Set<Hexagon> newHexagons = new HashSet<>();
        hexagons.add(startingHexagon);
        newHexagons.add(startingHexagon);
        for (int i = 0; i < radius; i++) {
            newHexagons.clear();
            for (Hexagon hexagon : hexagons) {
                newHexagons.addAll(hexagon.neighbors);
            }
            hexagons.addAll(newHexagons);
        }
        return hexagons;
    }
}
