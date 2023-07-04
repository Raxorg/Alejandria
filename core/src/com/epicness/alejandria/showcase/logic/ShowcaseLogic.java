package com.epicness.alejandria.showcase.logic;

import com.epicness.alejandria.showcase.logic.input.ModuleInputAdapter;
import com.epicness.alejandria.showcase.logic.input.ShowcaseInputHandler;
import com.epicness.alejandria.showcase.modules.animations.SpriteAnimation;
import com.epicness.alejandria.showcase.modules.animations.SpriteRotationAnimation;
import com.epicness.alejandria.showcase.modules.bullets.BulletSpawning;
import com.epicness.alejandria.showcase.modules.collisions.PixelPerfectCollision;
import com.epicness.alejandria.showcase.modules.cursor.BeamAiming;
import com.epicness.alejandria.showcase.modules.cursor.PointAtCursor;
import com.epicness.alejandria.showcase.modules.fun.BeepingBalls;
import com.epicness.alejandria.showcase.modules.grids.CrossChunkSelection;
import com.epicness.alejandria.showcase.modules.grids.HexagonSelection;
import com.epicness.alejandria.showcase.modules.kinematics.ForwardKinematics;
import com.epicness.alejandria.showcase.modules.kinematics.InverseKinematics;
import com.epicness.alejandria.showcase.modules.masking.AlphaMasking;
import com.epicness.alejandria.showcase.modules.masking.Clipping;
import com.epicness.alejandria.showcase.modules.masking.LayeredMasking;
import com.epicness.alejandria.showcase.modules.masking.ShapeDrawerMasking;
import com.epicness.alejandria.showcase.modules.masking.ShapeRendererMasking;
import com.epicness.alejandria.showcase.modules.pathfinding.AStar;
import com.epicness.alejandria.showcase.modules.patterns.Phyllotaxis;
import com.epicness.alejandria.showcase.modules.procedural.ProceduralSquare;
import com.epicness.alejandria.showcase.modules.rendering.FrameBuffering;
import com.epicness.alejandria.showcase.modules.rendering.OrthographicExample;
import com.epicness.alejandria.showcase.modules.rendering3d.Decal;
import com.epicness.alejandria.showcase.modules.rendering3d.TexturedCube;
import com.epicness.alejandria.showcase.modules.shaders.InvertShader;
import com.epicness.alejandria.showcase.modules.shaders.ShakeShader;
import com.epicness.alejandria.showcase.modules.shaders.VignetteShader;
import com.epicness.alejandria.showcase.modules.viewports.AdvancedSplitScreen;
import com.epicness.fundamentals.logic.Logic;

public class ShowcaseLogic extends Logic {

    private final ShowcaseHandler showcaseHandler;

    public ShowcaseLogic() {
        // Shared
        registerHandler(new ModuleInputAdapter());
        // Animations
        registerHandler(new SpriteAnimation());
        registerHandler(new SpriteRotationAnimation());
        // Bullets
        registerHandler(new BulletSpawning());
        // Collisions
        registerHandler(new PixelPerfectCollision());
        // Cursor
        registerHandler(new BeamAiming());
        registerHandler(new PointAtCursor());
        // Fun
        registerHandler(new BeepingBalls());
        // Grids
        registerHandler(new CrossChunkSelection());
        registerHandler(new HexagonSelection());
        // Kinematics
        registerHandler(new ForwardKinematics());
        registerHandler(new InverseKinematics());
        // Patterns
        registerHandler(new Phyllotaxis());
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
        // Rendering 3D
        registerHandler(new Decal());
        registerHandler(new TexturedCube());
        // Shaders
        registerHandler(new InvertShader());
        registerHandler(new ShakeShader());
        registerHandler(new VignetteShader());
        // Viewports
        registerHandler(new AdvancedSplitScreen());

        registerHandler(showcaseHandler = new ShowcaseHandler());
        registerHandler(new ShowcaseInputHandler());
    }

    @Override
    public void update(float delta) {
        if (delta >= 0.4f) {
            return;
        }
        showcaseHandler.update(delta);
    }
}