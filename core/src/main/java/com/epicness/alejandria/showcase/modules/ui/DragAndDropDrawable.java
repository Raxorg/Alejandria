package com.epicness.alejandria.showcase.modules.ui;

import static com.epicness.alejandria.showcase.constants.UIConstants.DROP_AREA_HEIGHT;
import static com.epicness.alejandria.showcase.constants.UIConstants.DROP_AREA_WIDTH;
import static com.epicness.alejandria.showcase.constants.UIConstants.DROP_AREA_X;
import static com.epicness.alejandria.showcase.constants.UIConstants.DROP_AREA_Y;
import static com.epicness.alejandria.showcase.constants.UIConstants.DROP_SQUARE_SIZE;
import static com.epicness.alejandria.showcase.constants.UIConstants.STARTING_DROP_SQUARE_Y;
import static com.epicness.fundamentals.constants.SharedConstants.LIGHT_GRASS;
import static com.epicness.fundamentals.constants.SharedConstants.WHITE_CLEAR_25;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.Sprited;

import java.util.ArrayList;
import java.util.List;

public class DragAndDropDrawable implements ModuleDrawable {

    private final Sprited dropArea;
    private final List<Sprited> squares;

    public DragAndDropDrawable(Sprite pixelSprite, Sprite squareSprite) {
        dropArea = new Sprited(pixelSprite);
        dropArea.setSize(DROP_AREA_WIDTH, DROP_AREA_HEIGHT);
        dropArea.setPosition(DROP_AREA_X, DROP_AREA_Y);
        dropArea.setColor(WHITE_CLEAR_25);

        squares = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Sprited square = new Sprited(squareSprite);
            square.setSize(DROP_SQUARE_SIZE);
            square.setPosition(DROP_AREA_X + i * 110f, STARTING_DROP_SQUARE_Y);
            square.setColor(LIGHT_GRASS);
            squares.add(square);
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        dropArea.draw(spriteBatch);
        for (int i = 0; i < squares.size(); i++) {
            squares.get(i).draw(spriteBatch);
        }
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
    }

    public Sprited getDropArea() {
        return dropArea;
    }

    public List<Sprited> getSquares() {
        return squares;
    }
}