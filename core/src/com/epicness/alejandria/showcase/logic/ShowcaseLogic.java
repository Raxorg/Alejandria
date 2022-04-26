package com.epicness.alejandria.showcase.logic;

import com.epicness.alejandria.showcase.logic.input.ShowcaseInputHandler;
import com.epicness.alejandria.showcase.logic.modules.animations.SpriteAnimation;
import com.epicness.alejandria.showcase.logic.modules.animations.SpriteRotationAnimation;
import com.epicness.alejandria.showcase.logic.modules.bullets.BulletSpawning;
import com.epicness.alejandria.showcase.logic.modules.collisions.PixelPerfectCollision;
import com.epicness.alejandria.showcase.logic.modules.cursor.PointAtCursor;
import com.epicness.alejandria.showcase.logic.modules.masking.AlphaMasking;
import com.epicness.alejandria.showcase.logic.modules.masking.AnimatedBackgrounds;
import com.epicness.fundamentals.logic.Logic;
import com.epicness.fundamentals.logic.SharedLogic;

public class ShowcaseLogic extends Logic {

    private final ShowcaseHandler showcaseHandler;

    public ShowcaseLogic(SharedLogic sharedLogic) {
        super(sharedLogic);

        registerHandler(showcaseHandler = new ShowcaseHandler());
        registerHandler(new ShowcaseInputHandler());
        // Animations
        registerHandler(new SpriteAnimation());
        registerHandler(new SpriteRotationAnimation());
        // Bullets
        registerHandler(new BulletSpawning());
        // Collisions
        registerHandler(new PixelPerfectCollision());
        // Cursor
        registerHandler(new PointAtCursor());
        // Masking
        registerHandler(new AlphaMasking());
        registerHandler(new AnimatedBackgrounds());
    }

    @Override
    public void update(float delta) {
        showcaseHandler.update(delta);
    }
}