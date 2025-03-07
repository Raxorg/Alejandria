package com.epicness.alejandria.showcase.modules.physics;

import static com.badlogic.gdx.Input.Buttons.LEFT;
import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.DynamicBody;
import static com.epicness.alejandria.showcase.constants.PhysicsConstants.CIRCLE_RADIUS;
import static com.epicness.alejandria.showcase.constants.PhysicsConstants.PHYSICS_CIRCLE_RADIUS;
import static com.epicness.alejandria.showcase.constants.PhysicsConstants.PHYSICS_SCALE_FACTOR;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.BASIC_COLORS;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.shapes.bidimensional.CirclePlus;
import com.epicness.fundamentals.utils.Box2DFactory;

public class BallPhysics extends Module<BallPhysicsDrawable> {

    private World world;
    private Array<Body> circleBodies;
    private Array<CirclePlus> circles;
    private Vector2 circleAuxPosition;

    public BallPhysics() {
        super("Ball Physics", "Click to spawn balls!\n\nUses Box2d");
    }

    @Override
    protected BallPhysicsDrawable setup() {
        drawable = new BallPhysicsDrawable();
        Box2D.init();
        world = drawable.getWorld();
        circleBodies = drawable.getCircleBodies();
        circles = drawable.getCircles();
        circleAuxPosition = new Vector2();
        touchDown(VIEWPORT_HALF_WIDTH, VIEWPORT_HALF_HEIGHT, LEFT);
        return drawable;
    }

    @Override
    public void update(float delta) {
        world.step(delta, 6, 2);
        for (int i = 0; i < circleBodies.size; i++) {
            circleAuxPosition.set(circleBodies.get(i).getPosition()).scl(PHYSICS_SCALE_FACTOR);
            circleAuxPosition.sub(CIRCLE_RADIUS, CIRCLE_RADIUS);
            circles.get(i).setPosition(circleAuxPosition);
        }
    }

    @Override
    public void touchDown(float x, float y, int button) {
        circleBodies.add(Box2DFactory.createCircle(
            world,
            x / PHYSICS_SCALE_FACTOR,
            y / PHYSICS_SCALE_FACTOR,
            PHYSICS_CIRCLE_RADIUS,
            DynamicBody
        ));
        CirclePlus circle = new CirclePlus(x, y, CIRCLE_RADIUS, BLACK, BASIC_COLORS[circles.size % 5]);
        circle.setThickness(7.5f);
        circles.add(circle);
    }
}