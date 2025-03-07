package com.epicness.fundamentals.logic.behaviors;

import com.epicness.fundamentals.stuff.SpritePlus;
import com.epicness.fundamentals.stuff.TiledSpriteGrid;

public class TiledGridPanner {

    private TiledSpriteGrid tiledSpriteGrid;
    private float maxX, maxY, xSpeed, ySpeed;
    private boolean enabled;

    public void setup(TiledSpriteGrid tiledSpriteGrid, float xSpeed, float ySpeed) {
        this.tiledSpriteGrid = tiledSpriteGrid;
        maxX = tiledSpriteGrid.getBounds().x + tiledSpriteGrid.getBounds().width;
        maxY = tiledSpriteGrid.getBounds().y + tiledSpriteGrid.getBounds().height;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        enabled = true;
    }

    public void update(float delta) {
        if (!enabled) {
            return;
        }
        SpritePlus[][] sprites = tiledSpriteGrid.getSprites();
        for (int column = 0; column < sprites.length; column++) {
            for (int row = 0; row < sprites[column].length; row++) {
                moveSprite(sprites[column][row], delta);
            }
        }
    }

    private void moveSprite(SpritePlus sprite, float delta) {
        sprite.translateX(xSpeed * delta);
        sprite.translateY(ySpeed * delta);

        float differenceX = sprite.getBoundingRectangle().x - maxX;
        if (differenceX > 0f) {
            sprite.setX(tiledSpriteGrid.getBounds().x - sprite.getBoundingRectangle().width + differenceX);
            sprite.translateX((sprite.getBoundingRectangle().width - sprite.getWidth()) * 0.5f);
        }

        float differenceY = sprite.getBoundingRectangle().y - maxY;
        if (differenceY > 0f) {
            sprite.setY(tiledSpriteGrid.getBounds().y - sprite.getBoundingRectangle().height + differenceY);
            sprite.translateY((sprite.getBoundingRectangle().height - sprite.getHeight()) * 0.5f);
        }
    }
}