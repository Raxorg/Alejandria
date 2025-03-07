package com.epicness.alejandria.showcase.stuff.modules.fun;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.epicness.fundamentals.stuff.SpritePlus;

public class ColorBall extends SpritePlus {

    public final Vector2 speed;

    public ColorBall(Sprite sprite, Vector2 speed) {
        super(sprite);
        this.speed = speed;
    }
}