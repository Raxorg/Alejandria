package com.epicness.alejandria.showcase.logic;

import com.epicness.alejandria.showcase.logic.input.ShowcaseInputHandler;
import com.epicness.alejandria.showcase.logic.modules.animations.SpriteAnimation;
import com.epicness.alejandria.showcase.logic.modules.animations.SpriteRotationAnimation;
import com.epicness.alejandria.showcase.logic.modules.bullets.BulletSpawning;
import com.epicness.alejandria.showcase.logic.modules.collisions.PixelPerfectCollision;
import com.epicness.alejandria.showcase.logic.modules.cursor.PointAtCursor;
import com.epicness.alejandria.showcase.logic.modules.masking.AlphaMasking;
import com.epicness.alejandria.showcase.logic.modules.masking.Clipping;
import com.epicness.alejandria.showcase.logic.modules.masking.LayeredMasking;
import com.epicness.alejandria.showcase.logic.modules.masking.ShapeDrawerMasking;
import com.epicness.alejandria.showcase.logic.modules.masking.ShapeRendererMasking;
import com.epicness.alejandria.showcase.logic.modules.pathfinding.AStar;
import com.epicness.alejandria.showcase.logic.modules.procedural.ProceduralSquare;
import com.epicness.alejandria.showcase.logic.modules.rendering.FrameBuffering;
import com.epicness.alejandria.showcase.logic.modules.rendering.OrthographicExample;
import com.epicness.alejandria.showcase.logic.modules.shaders.InvertShader;
import com.epicness.alejandria.showcase.logic.modules.shaders.ShakeShader;
import com.epicness.alejandria.showcase.logic.modules.shaders.VignetteShader;
import com.epicness.alejandria.showcase.logic.modules.viewports.AdvancedSplitScreen;
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
        registerHandler(new Clipping());
        registerHandler(new LayeredMasking());
        registerHandler(new ShapeDrawerMasking());
        registerHandler(new ShapeRendererMasking());
        // Pathfinding
        registerHandler(new AStar());
        // Procedural
        registerHandler(new ProceduralSquare());
        // Rendering
        registerHandler(new FrameBuffering());
        registerHandler(new OrthographicExample());
        // Shaders
        registerHandler(new InvertShader());
        registerHandler(new ShakeShader());
        registerHandler(new VignetteShader());
        // Viewports
        registerHandler(new AdvancedSplitScreen());
    }

    @Override
    public void update(float delta) {
        showcaseHandler.update(delta);
    }
}