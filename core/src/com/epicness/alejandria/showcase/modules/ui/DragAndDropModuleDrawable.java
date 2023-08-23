package com.epicness.alejandria.showcase.modules.ui;

import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.LIGHT_GRASS;
import static com.epicness.fundamentals.constants.SharedConstants.WHITE_CLEAR_25;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.Sprited;

import java.util.ArrayList;
import java.util.List;

public class DragAndDropModuleDrawable implements ModuleDrawable {

    private final Sprited dropArea;
    private final List<Sprited> squares;

    public DragAndDropModuleDrawable(Sprite pixelSprite, Sprite squareSprite) {
        dropArea = new Sprited(pixelSprite);
        dropArea.setSize(450f, 120f);
        dropArea.setPosition(CAMERA_HALF_WIDTH - 225f, CAMERA_HEIGHT * 0.55f);
        dropArea.setColor(WHITE_CLEAR_25);

        squares = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Sprited square = new Sprited(squareSprite);
            square.setSize(100f);
            square.setPosition(CAMERA_HALF_WIDTH - 215f + i * 110f, CAMERA_HEIGHT * 0.35f);
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