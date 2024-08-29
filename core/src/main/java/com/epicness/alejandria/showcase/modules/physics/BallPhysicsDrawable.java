package com.epicness.alejandria.showcase.modules.physics;

import static com.epicness.alejandria.showcase.constants.PhysicsConstants.FLOOR_X1;
import static com.epicness.alejandria.showcase.constants.PhysicsConstants.FLOOR_X2;
import static com.epicness.alejandria.showcase.constants.PhysicsConstants.FLOOR_Y1;
import static com.epicness.alejandria.showcase.constants.PhysicsConstants.FLOOR_Y2;
import static com.epicness.alejandria.showcase.constants.PhysicsConstants.GRAVITY;
import static com.epicness.alejandria.showcase.constants.PhysicsConstants.PHYSICS_FLOOR_X1;
import static com.epicness.alejandria.showcase.constants.PhysicsConstants.PHYSICS_FLOOR_X2;
import static com.epicness.alejandria.showcase.constants.PhysicsConstants.PHYSICS_FLOOR_Y1;
import static com.epicness.alejandria.showcase.constants.PhysicsConstants.PHYSICS_FLOOR_Y2;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Circle;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Line;
import com.epicness.fundamentals.utils.Box2DFactory;

public class BallPhysicsDrawable implements ModuleDrawable {

    private final Box2DDebugRenderer debugRenderer;
    private final Matrix4 physicsProjectionMatrix;
    private final World world;
    private final Array<Body> circleBodies;
    private final Array<Circle> circles;
    private final Line floor;

    public BallPhysicsDrawable(Matrix4 physicsProjectionMatrix) {
        debugRenderer = new Box2DDebugRenderer();

        this.physicsProjectionMatrix = physicsProjectionMatrix;
        world = new World(new Vector2(0, GRAVITY), true);
        circleBodies = new Array<>();
        circles = new Array<>();

        Box2DFactory.createThinWall(
            world,
            PHYSICS_FLOOR_X1, PHYSICS_FLOOR_Y1,
            PHYSICS_FLOOR_X2, PHYSICS_FLOOR_Y2,
            0f
        );

        floor = new Line(FLOOR_X1, FLOOR_Y1, FLOOR_X2, FLOOR_Y2);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.begin();
        for (int i = 0; i < circles.size; i++) {
            circles.get(i).draw(shapeDrawer);
        }
        floor.draw(shapeDrawer);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
        debugRenderer.render(world, physicsProjectionMatrix);
    }

    public World getWorld() {
        return world;
    }

    public Array<Body> getCircleBodies() {
        return circleBodies;
    }

    public Array<Circle> getCircles() {
        return circles;
    }
}