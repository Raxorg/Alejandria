package com.epicness.fundamentals.stuff;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;

public class TiledSpriteGrid {

    private final Sprited background;
    private final Sprited[][] sprites;
    private final Rectangle bounds, scissors;

    public TiledSpriteGrid(Sprite backgroundSprite, Sprite tiledSprite, int columns, int rows, float spriteSize,
                           float width, float height) {
        background = new Sprited(backgroundSprite);
        background.setSize(width, height);
        sprites = new Sprited[columns][];
        for (int column = 0; column < columns; column++) {
            sprites[column] = new Sprited[rows];
            for (int row = 0; row < rows; row++) {
                sprites[column][row] = new Sprited(tiledSprite);
                sprites[column][row].setSize(spriteSize);
                sprites[column][row].setOriginCenter();
                sprites[column][row].setRotation(45f);
            }
        }
        bounds = new Rectangle(0f, 0f, width, height);
        scissors = new Rectangle();
        setPosition(0f, 0f);
    }

    public void draw(Camera camera, SpriteBatch spriteBatch) {
        // Scissors
        ScissorStack.calculateScissors(camera, spriteBatch.getTransformMatrix(), bounds, scissors);
        boolean pop = ScissorStack.pushScissors(scissors);
        // Background
        background.draw(spriteBatch);
        // Sprites
        for (int column = 0; column < sprites.length; column++) {
            for (int row = 0; row < sprites[column].length; row++) {
                sprites[column][row].draw(spriteBatch);
            }
        }
        // Returning to normal rendering
        spriteBatch.flush();
        if (pop) {
            ScissorStack.popScissors();
        }
    }

    public void drawDebug(Camera camera, ShapeRendererPlus shapeRenderer) {
        // Scissors
        ScissorStack.calculateScissors(camera, shapeRenderer.getTransformMatrix(), bounds, scissors);
        boolean pop = ScissorStack.pushScissors(scissors);
        // Background
        background.drawDebug(shapeRenderer);
        // Sprites
        for (int column = 0; column < sprites.length; column++) {
            for (int row = 0; row < sprites[column].length; row++) {
                sprites[column][row].drawDebug(shapeRenderer);
            }
        }
        // Returning to normal rendering
        shapeRenderer.flush();
        if (pop) {
            ScissorStack.popScissors();
        }
    }

    public void setPosition(float x, float y) {
        background.setPosition(x, y);
        float width = bounds.width;
        float height = bounds.height;
        int columns = sprites.length;
        int rows = sprites[0].length;
        float spriteSize = sprites[0][0].getBoundingRectangle().width;
        float xSpacing = (width + spriteSize - (columns * spriteSize)) / columns;
        float ySpacing = (height + spriteSize - (rows * spriteSize)) / rows;
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                Sprited sprite = sprites[column][row];
                sprite.setX(x + column * spriteSize + column * xSpacing);
                sprite.setY(y + row * spriteSize + row * ySpacing);
            }
        }
        bounds.setPosition(x, y);
    }

    public void intercalateRows() {
        float width = bounds.width;
        int columns = sprites.length;
        float spriteSize = sprites[0][0].getBoundingRectangle().width;
        float xSpacing = (width + spriteSize - (columns * spriteSize)) / columns;
        for (int column = 0; column < sprites.length; column++) {
            for (int row = 0; row < sprites[0].length; row++) {
                if (row % 2 == 0) {
                    continue;
                }
                Sprited sprite = sprites[column][row];
                sprite.translateX(spriteSize / 2f + xSpacing / 2f);
            }
        }
    }

    public void setBackgroundColor(Color color) {
        background.setColor(color);
    }

    public void randomizeColors(Color baseColor) {
        for (int column = 0; column < sprites.length; column++) {
            for (int row = 0; row < sprites[0].length; row++) {
                Color color = baseColor.cpy().lerp(Color.CLEAR, MathUtils.random(0.3f, 0.6f));
                sprites[column][row].setColor(color);
            }
        }
    }

    public Sprited[][] getSprites() {
        return sprites;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}