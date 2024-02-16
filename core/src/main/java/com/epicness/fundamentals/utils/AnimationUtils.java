package com.epicness.fundamentals.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class AnimationUtils {

    public static Sprite[] split(Texture texture, int spriteWidth, int spriteHeight) {
        int width = texture.getWidth(), height = texture.getHeight();
        int columns = width / spriteWidth, rows = height / spriteHeight;
        int x, y = 0;

        Sprite[] sprites = new Sprite[rows * columns];
        for (int row = 0; row < rows; row++, y += spriteHeight) {
            x = 0;
            for (int column = 0; column < columns; column++, x += spriteWidth) {
                sprites[row * columns + column] = new Sprite(texture, x, y, spriteWidth, spriteHeight);
            }
        }

        return sprites;
    }
}