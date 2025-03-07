package com.epicness.alejandria.showcase.modules.ui;

import static com.epicness.alejandria.showcase.constants.UIConstants.DROP_AREA_HEIGHT;
import static com.epicness.alejandria.showcase.constants.UIConstants.DROP_AREA_WIDTH;
import static com.epicness.alejandria.showcase.constants.UIConstants.DROP_AREA_X;
import static com.epicness.alejandria.showcase.constants.UIConstants.DROP_AREA_Y;
import static com.epicness.alejandria.showcase.constants.UIConstants.DROP_SQUARE_SIZE;
import static com.epicness.alejandria.showcase.constants.UIConstants.STARTING_DROP_SQUARE_Y;
import static com.epicness.fundamentals.constants.ColorConstants.LIGHT_GRASS;
import static com.epicness.fundamentals.constants.ColorConstants.WHITE_25;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.SpritePlus;

import java.util.ArrayList;
import java.util.List;

public class DragAndDropDrawable implements ModuleDrawable {

    private final SpritePlus dropArea;
    private final List<SpritePlus> squares;

    public DragAndDropDrawable(Sprite pixelSprite, Sprite squareSprite) {
        dropArea = new SpritePlus(pixelSprite);
        dropArea.setSize(DROP_AREA_WIDTH, DROP_AREA_HEIGHT);
        dropArea.setPosition(DROP_AREA_X, DROP_AREA_Y);
        dropArea.setColor(WHITE_25);

        squares = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            SpritePlus square = new SpritePlus(squareSprite);
            square.setSize(DROP_SQUARE_SIZE);
            square.setPosition(DROP_AREA_X + i * 110f, STARTING_DROP_SQUARE_Y);
            square.setColor(LIGHT_GRASS);
            squares.add(square);
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        dropArea.draw(spriteBatch);
        for (int i = 0; i < squares.size(); i++) {
            squares.get(i).draw(spriteBatch);
        }
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeDrawerPlus shapeDrawer) {
    }

    public SpritePlus getDropArea() {
        return dropArea;
    }

    public List<SpritePlus> getSquares() {
        return squares;
    }
}