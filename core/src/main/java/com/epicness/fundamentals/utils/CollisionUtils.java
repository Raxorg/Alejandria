package com.epicness.fundamentals.utils;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.epicness.fundamentals.stuff.shapes.bidimensional.CirclePlus;
import com.epicness.fundamentals.stuff.shapes.tridimensional.Line3D;
import com.epicness.fundamentals.stuff.shapes.tridimensional.WireframeCube;
import com.epicness.fundamentals.stuff.shapes.tridimensional.cylinder.Cylinder;
import com.epicness.fundamentals.stuff.shapes.tridimensional.plane.Plane;

public class CollisionUtils {

    public static boolean overlaps(float x, float y, float width, float height, Rectangle r) {
        return x <= r.x + r.width && x + width >= r.x && y <= r.y + r.height && y + height >= r.y;
    }

    public static boolean overlaps(Rectangle a, Rectangle b) {
        return overlaps(a.x, a.y, a.width, a.height, b);
    }

    public static boolean intersects(Ray ray, Plane plane, Vector3 intersection) {
        return Intersector.intersectRayTriangles(ray, plane.getVertices(), plane.getIndices(), 3, intersection);
    }

    public static boolean intersects(Ray ray, Cylinder cylinder, Vector3 intersection) {
        return Intersector.intersectRayTriangles(ray, cylinder.getVertices(), cylinder.getIndices(), 3, intersection);
    }

    public static boolean intersects(Ray ray, WireframeCube wireframeCube, Vector3 intersection) {
        return Intersector.intersectRayOrientedBounds(ray, wireframeCube.orientedBoundingBox, intersection);
    }

    public static boolean intersects(Line3D line, WireframeCube wireframeCube, Vector3 intersection) {
        return intersects(line.getRay(), wireframeCube, intersection);
    }

    public static boolean overlapPolygonCircle(Polygon polygon, Circle circle) {
        return overlapPolygonCircle(polygon, circle.x, circle.y, circle.radius);
    }

    public static boolean overlapPolygonCircle(Polygon polygon, CirclePlus circle) {
        return overlapPolygonCircle(polygon, circle.getCenterX(), circle.getCenterY(), circle.getRadius());
    }

    /* Also returns true if the polygon contains the circle */
    private static boolean overlapPolygonCircle(Polygon polygon, float circleCenterX, float circleCenterY, float circleRadius) {
        float[] vertices = polygon.getTransformedVertices();
        Vector2 start = new Vector2();
        Vector2 end = new Vector2();
        Vector2 center = new Vector2(circleCenterX, circleCenterY);
        float squareRadius = circleRadius * circleRadius;
        /* Loop through the segments of the polygon */
        for (int i = 0; i < vertices.length; i += 2) {
            if (i == 0) {
                start.set(vertices[vertices.length - 2], vertices[vertices.length - 1]);
            } else {
                start.set(vertices[i - 2], vertices[i - 1]);
            }
            end.set(vertices[i], vertices[i + 1]);
            if (Intersector.intersectSegmentCircle(start, end, center, squareRadius)) {
                return true;
            }
        }
        return polygon.contains(center);
    }
}