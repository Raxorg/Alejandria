package com.epicness.alejandria.showcase.modules.audio;

import static com.epicness.fundamentals.constants.ColorConstants.DIRT;
import static com.epicness.fundamentals.constants.ColorConstants.LIGHT_DIRT;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.grid.Cell;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Line;

public class SoundPitch extends Module<SoundPitchDrawable> {

    private Cell[][] cells;
    private Line line;
    private Sound sound;
    private Cell auxCell;

    public SoundPitch() {
        super("Sound Pitch", "Demonstrates pitch modification of short sounds\n\nClick the cells to listen");
    }

    @Override
    protected SoundPitchDrawable setup() {
        drawable = new SoundPitchDrawable(sharedAssets.getSquare32());
        cells = drawable.getCells();
        line = drawable.getLine();
        sound = sharedAssets.getBeep();
        return drawable;
    }

    @Override
    public void update(float delta) {
        float translation = 750f * delta;
        line.translateX(translation);
        if (line.getX() > cells[cells.length - 1][0].getEndX()) {
            line.setX(cells[0][0].getX());
        }
        for (int column = 0; column < cells.length; column++) {
            for (int row = 0; row < cells[column].length; row++) {
                auxCell = cells[column][row];
                if (!auxCell.getColor().equals(DIRT)) continue;
                if (line.getX() - translation < auxCell.getCenterX() && line.getX() > auxCell.getCenterX()) {
                    sound.play(1f, MathUtils.map(0, cells[column].length, 0.5f, 2f, row), 0f);
                }
            }
        }
    }

    @Override
    public void touchDown(float x, float y, int button) {
        for (int column = 0; column < cells.length; column++) {
            for (int row = 0; row < cells[column].length; row++) {
                auxCell = cells[column][row];
                if (auxCell.contains(x, y)) {
                    auxCell.setColor(auxCell.getColor().equals(DIRT) ? LIGHT_DIRT : DIRT);
                }
            }
        }
    }
}