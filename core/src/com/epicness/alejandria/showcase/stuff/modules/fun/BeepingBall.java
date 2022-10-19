package com.epicness.alejandria.showcase.stuff.modules.fun;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.epicness.fundamentals.stuff.DualSprited;

public class BeepingBall extends DualSprited {

    public float startingX, finalX, startingY, angle, pitch;
    public boolean forward;

    public BeepingBall(Sprite ballGlow, Sprite ball) {
        super(ballGlow, ball);
        forward = true;
    }
}