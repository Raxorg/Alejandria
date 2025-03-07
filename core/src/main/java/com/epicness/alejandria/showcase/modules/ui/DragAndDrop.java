package com.epicness.alejandria.showcase.modules.ui;

import static com.badlogic.gdx.graphics.Color.ORANGE;
import static com.epicness.fundamentals.constants.ColorConstants.LIGHT_GRASS;
import static com.epicness.fundamentals.constants.ColorConstants.WHITE_25;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.SpritePlus;

import java.util.List;

public class DragAndDrop extends Module<DragAndDropDrawable> {

    private SpritePlus dropArea;
    private List<SpritePlus> squares;
    private Vector2 pivot;
    private SpritePlus draggedSquare;

    public DragAndDrop() {
        super("Drag And Drop", "Cross platform friendly drag and drop");
    }

    @Override
    protected DragAndDropDrawable setup() {
        drawable = new DragAndDropDrawable(sharedAssets.getPixel(), sharedAssets.getSquare32());
        dropArea = drawable.getDropArea();
        squares = drawable.getSquares();
        pivot = new Vector2();
        return drawable;
    }

    @Override
    public void mouseMoved(float x, float y) {
        clearSquares();
        for (int i = 0; i < squares.size(); i++) {
            SpritePlus square = squares.get(i);
            if (square.contains(x, y)) {
                clearSquares();
                square.setColor(ORANGE);
            }
        }
    }

    private void clearSquares() {
        for (int i = 0; i < squares.size(); i++) {
            squares.get(i).setColor(LIGHT_GRASS);
        }
    }

    @Override
    public void touchDown(float x, float y, int button) {
        for (int i = 0; i < squares.size(); i++) {
            SpritePlus square = squares.get(i);
            if (square.contains(x, y)) {
                pivot.set(x, y);
                draggedSquare = square;
            }
        }
    }

    @Override
    public void touchDragged(float x, float y) {
        if (draggedSquare == null) return;

        draggedSquare.translate(x - pivot.x, y - pivot.y);
        pivot.set(x, y);

        dropArea.setColor(WHITE_25);
        if (dropArea.getBoundingRectangle().overlaps(draggedSquare.getBoundingRectangle())) {
            dropArea.setColor(LIGHT_GRASS);
        }
    }

    @Override
    public void touchUp(float x, float y, int button) {
        if (dropArea.getColor().equals(LIGHT_GRASS)) {
            float dropX = MathUtils.clamp(
                draggedSquare.getX(),
                dropArea.getX() + 10f,
                dropArea.getX() + dropArea.getWidth() - draggedSquare.getWidth() - 10f);
            draggedSquare.setPosition(dropX, dropArea.getY() + 10f);
            mouseMoved(x, y);
        }
        dropArea.setColor(WHITE_25);
        draggedSquare = null;
    }
}