package com.epicness.fundamentals.utils;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.StaticBody;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Methods to create Box2D shapes.
 */
public class Box2DFactory {

    /**
     * Creates a circle object with the given position and radius. Restitution defaults to 0.6.
     */
    public static Body createCircle(World world, float x, float y, float radius, BodyType type) {
        CircleShape shape = new CircleShape();
        shape.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.6f;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.setType(type);

        shape.dispose();
        return body;
    }

    /**
     * Creates a wall by constructing a rectangle whose corners are (xMin,yMin) and (xMax,yMax),
     * and rotating the box counterclockwise through the given angle. Restitution defaults to 0.
     */
    public static Body createWall(World world, float xMin, float yMin, float xMax, float yMax, float angle) {
        return createWall(world, xMin, yMin, xMax, yMax, angle, 0f);
    }

    /**
     * Creates a wall by constructing a rectangle whose corners are (xMin,yMin) and (xMax,yMax),
     * and rotating the box counterclockwise through the given angle, with specified restitution.
     */
    public static Body createWall(World world, float xMin, float yMin, float xMax, float yMax, float angle, float restitution) {
        float cx = (xMin + xMax) / 2;
        float cy = (yMin + yMax) / 2;
        float hx = Math.abs((xMax - xMin) / 2);
        float hy = Math.abs((yMax - yMin) / 2);
        PolygonShape wallShape = new PolygonShape();
        // Don't set the angle here; instead call setTransform on the body below. This allows future
        // calls to setTransform to adjust the rotation as expected.
        wallShape.setAsBox(hx, hy, new Vector2(0f, 0f), 0f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = wallShape;
        fixtureDef.density = 1.0f;
        if (restitution > 0) fixtureDef.restitution = restitution;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(cx, cy);

        Body wall = world.createBody(bodyDef);
        wall.createFixture(fixtureDef);
        wall.setType(StaticBody);
        wall.setTransform(cx, cy, angle);

        return wall;
    }

    /**
     * Creates a segment-like thin wall with 0.05 thickness going from (x1,y1) to (x2,y2)
     */
    public static Body createThinWall(World world, float x1, float y1, float x2, float y2, float restitution) {
        // Determine center point and rotation angle for createWall.
        float cx = (x1 + x2) / 2;
        float cy = (y1 + y2) / 2;
        float angle = (float) Math.atan2(y2 - y1, x2 - x1);
        float mag = (float) Math.hypot(y2 - y1, x2 - x1);
        return createWall(world, cx - mag / 2, cy - 0.05f, cx + mag / 2, cy + 0.05f, angle, restitution);
    }
}