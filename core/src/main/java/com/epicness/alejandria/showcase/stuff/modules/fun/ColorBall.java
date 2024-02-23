package com.epicness.alejandria.showcase.stuff.modules.fun;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.epicness.fundamentals.stuff.Sprited;

public class ColorBall extends Sprited {

    public final Vector2 speed;

    public ColorBall(Sprite sprite, Vector2 speed) {
        super(sprite);
        this.speed = speed;
    }
}