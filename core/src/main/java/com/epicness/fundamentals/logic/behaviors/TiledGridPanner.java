package com.epicness.fundamentals.logic.behaviors;

import com.epicness.fundamentals.stuff.Sprited;
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
        Sprited[][] sprites = tiledSpriteGrid.getSprites();
        for (int column = 0; column < sprites.length; column++) {
            for (int row = 0; row < sprites[column].length; row++) {
                moveSprite(sprites[column][row], delta);
            }
        }
    }

    private void moveSprite(Sprited sprited, float delta) {
        sprited.translateX(xSpeed * delta);
        sprited.translateY(ySpeed * delta);

        float differenceX = sprited.getBoundingRectangle().x - maxX;
        if (differenceX > 0f) {
            sprited.setX(tiledSpriteGrid.getBounds().x - sprited.getBoundingRectangle().width + differenceX);
            sprited.translateX((sprited.getBoundingRectangle().width - sprited.getWidth()) / 2f);
        }

        float differenceY = sprited.getBoundingRectangle().y - maxY;
        if (differenceY > 0f) {
            sprited.setY(tiledSpriteGrid.getBounds().y - sprited.getBoundingRectangle().height + differenceY);
            sprited.translateY((sprited.getBoundingRectangle().height - sprited.getHeight()) / 2f);
        }
    }
}