package com.epicness.alejandria.showcase.logic;

import com.badlogic.gdx.Gdx;
import com.epicness.alejandria.showcase.modules.Welcome;
import com.epicness.alejandria.showcase.modules.animations.SpriteAnimation;
import com.epicness.alejandria.showcase.modules.animations.SpriteRotationAnimation;
import com.epicness.alejandria.showcase.modules.audio.SoundPitch;
import com.epicness.alejandria.showcase.modules.bullets.BulletSpawning;
import com.epicness.alejandria.showcase.modules.collisions.PixelPerfectCollision;
import com.epicness.alejandria.showcase.modules.cursor.BeamAiming;
import com.epicness.alejandria.showcase.modules.cursor.PointAtCursor;
import com.epicness.alejandria.showcase.modules.fun.BeepingBalls;
import com.epicness.alejandria.showcase.modules.fun.FallingSand;
import com.epicness.alejandria.showcase.modules.fun.Lasers;
import com.epicness.alejandria.showcase.modules.fun.ReactiveGrid;
import com.epicness.alejandria.showcase.modules.grids.CrossChunkSelection;
import com.epicness.alejandria.showcase.modules.grids.HexagonSelection;
import com.epicness.alejandria.showcase.modules.html.Alert;
import com.epicness.alejandria.showcase.modules.kinematics.ForwardKinematics;
import com.epicness.alejandria.showcase.modules.kinematics.InverseKinematics;
import com.epicness.alejandria.showcase.modules.lights.SimpleLights;
import com.epicness.alejandria.showcase.modules.masking.AlphaMasking;
import com.epicness.alejandria.showcase.modules.masking.Clipping;
import com.epicness.alejandria.showcase.modules.masking.LayeredMasking;
import com.epicness.alejandria.showcase.modules.masking.ShapeDrawerMasking;
import com.epicness.alejandria.showcase.modules.masking.ShapeRendererMasking;
import com.epicness.alejandria.showcase.modules.optimization.QuadTree;
import com.epicness.alejandria.showcase.modules.pathfinding.AStar;
import com.epicness.alejandria.showcase.modules.patterns.CantorGasket;
import com.epicness.alejandria.showcase.modules.patterns.DragonCurve;
import com.epicness.alejandria.showcase.modules.patterns.Phyllotaxis;
import com.epicness.alejandria.showcase.modules.patterns.Spiral;
import com.epicness.alejandria.showcase.modules.patterns.Spirograph;
import com.epicness.alejandria.showcase.modules.physics.BallPhysics;
import com.epicness.alejandria.showcase.modules.procedural.Lightning;
import com.epicness.alejandria.showcase.modules.procedural.PixmapManipulation;
import com.epicness.alejandria.showcase.modules.procedural.ProceduralSquare;
import com.epicness.alejandria.showcase.modules.rendering.FrameBuffering;
import com.epicness.alejandria.showcase.modules.rendering.ManualScreenClear;
import com.epicness.alejandria.showcase.modules.rendering.OrthographicExample;
import com.epicness.alejandria.showcase.modules.rendering.ShapeRendering;
import com.epicness.alejandria.showcase.modules.rendering3d.Decal;
import com.epicness.alejandria.showcase.modules.rendering3d.TexturedCube;
import com.epicness.alejandria.showcase.modules.shaders.InvertShader;
import com.epicness.alejandria.showcase.modules.shaders.RaymarchingShader;
import com.epicness.alejandria.showcase.modules.shaders.ShakeShader;
import com.epicness.alejandria.showcase.modules.shaders.VignetteShader;
import com.epicness.alejandria.showcase.modules.ui.DragAndDrop;
import com.epicness.alejandria.showcase.modules.ui.TextManipulation;
import com.epicness.alejandria.showcase.modules.viewports.AdvancedSplitScreen;
import com.epicness.alejandria.showcase.modules.viewports.WideViewport;
import com.epicness.fundamentals.logic.Logic;
import com.epicness.fundamentals.utils.OSUtils;

public class ShowcaseLogic extends Logic {

    private final ShowcaseHandler showcaseHandler;

    public ShowcaseLogic() {
        // Welcome
        registerHandler(new Welcome());
        // Animations
        registerHandler(new SpriteAnimation());
        registerHandler(new SpriteRotationAnimation());
        // Audio
        registerHandler(new SoundPitch());
        // Bullets
        registerHandler(new BulletSpawning());
        // Collisions
        registerHandler(new PixelPerfectCollision());
        // Cursor
        registerHandler(new BeamAiming());
        registerHandler(new PointAtCursor());
        // Fun
        registerHandler(new BeepingBalls());
        registerHandler(new FallingSand());
        registerHandler(new Lasers());
        registerHandler(new ReactiveGrid());
        // Grids
        registerHandler(new CrossChunkSelection());
        registerHandler(new HexagonSelection());
        // HTML
        if (OSUtils.isHTML()) registerHandler(new Alert());
        // Lights
        registerHandler(new SimpleLights());
        // Kinematics
        registerHandler(new ForwardKinematics());
        registerHandler(new InverseKinematics());
        // Patterns
        registerHandler(new CantorGasket());
        registerHandler(new DragonCurve());
        registerHandler(new Phyllotaxis());
        registerHandler(new Spiral());
        registerHandler(new Spirograph());
        // Physics
        registerHandler(new BallPhysics());
        // Masking
        registerHandler(new AlphaMasking());
        registerHandler(new Clipping());
        registerHandler(new LayeredMasking());
        registerHandler(new ShapeDrawerMasking());
        registerHandler(new ShapeRendererMasking());
        // Optimization
        registerHandler(new QuadTree());
        // Pathfinding
        registerHandler(new AStar());
        // Procedural
        registerHandler(new Lightning());
        registerHandler(new PixmapManipulation());
        registerHandler(new ProceduralSquare());
        // Rendering
        registerHandler(new FrameBuffering());
        registerHandler(new ManualScreenClear());
        registerHandler(new OrthographicExample());
        registerHandler(new ShapeRendering());
        // Rendering 3D
        registerHandler(new Decal());
        registerHandler(new TexturedCube());
        // Shaders
        registerHandler(new InvertShader());
        registerHandler(new RaymarchingShader());
        registerHandler(new ShakeShader());
        registerHandler(new VignetteShader());
        // UI
        registerHandler(new DragAndDrop());
        registerHandler(new TextManipulation());
        // Viewports
        registerHandler(new AdvancedSplitScreen());
        registerHandler(new WideViewport());

        registerHandler(showcaseHandler = new ShowcaseHandler());
    }

    @Override
    public void update() {
        if (Gdx.graphics.getDeltaTime() >= 0.4f) {
            return;
        }
        showcaseHandler.update();
    }
}