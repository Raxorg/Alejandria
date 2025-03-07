package com.epicness.alejandria.showcase.modules.audio;

import static com.epicness.alejandria.showcase.constants.AudioConstants.SOUNDBOARD_ROWS;
import static com.epicness.fundamentals.constants.ColorConstants.DIRT;
import static com.epicness.fundamentals.constants.ColorConstants.LIGHT_DIRT;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.grid.Cell;
import com.epicness.fundamentals.stuff.shapes.bidimensional.CirclePlus;

public class SoundPitch extends Module<SoundPitchDrawable> {

    private Cell[][] cells;
    private Cell[] currentCells;
    private CirclePlus[] circles;
    private Sound sound;

    public SoundPitch() {
        super("Sound Pitch", "Demonstrates pitch modification of short sounds\n\nClick the cells to listen");
    }

    @Override
    protected SoundPitchDrawable setup() {
        drawable = new SoundPitchDrawable(sharedAssets.getSquare32());
        cells = drawable.getCells();
        currentCells = new Cell[SOUNDBOARD_ROWS];
        circles = drawable.getCircles();
        sound = sharedAssets.getBeep();
        return drawable;
    }

    @Override
    public void update(float delta) {
        float translation = 750f * delta;
        CirclePlus circle;
        for (int i = 0; i < circles.length; i++) {
            circle = circles[i];
            circle.translateX(translation);
            if (circle.getX() >= cells[cells.length - 1][0].getEndX()) {
                loopCircles();
                return;
            }
        }

        Cell cell;
        for (int col = 0; col < cells.length; col++) {
            for (int row = 0; row < cells[col].length; row++) {
                cell = cells[col][row];
                if (cell.contains(circles[row].getCenter())) {
                    if (currentCells[row] != cell && cell.getColor().equals(DIRT)) {
                        sound.play(1f, MathUtils.map(0, cells[col].length, 0.5f, 2f, row), 0f);
                    }
                    currentCells[row] = cell;
                }
            }
        }
    }

    private void loopCircles() {
        for (int i = 0; i < circles.length; i++) {
            circles[i].setX(cells[0][0].getX() - circles[i].getWidth());
        }
    }

    @Override
    public void touchDown(float x, float y, int button) {
        Cell cell;
        for (int column = 0; column < cells.length; column++) {
            for (int row = 0; row < cells[column].length; row++) {
                cell = cells[column][row];
                if (cell.contains(x, y)) {
                    cell.setColor(cell.getColor().equals(DIRT) ? LIGHT_DIRT : DIRT);
                }
            }
        }
    }
}