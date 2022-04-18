package com.epicness.alejandria.showcase.stuff.modules.pathfinding.helpers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class PathfindingCell {

    private final int column, row;
    private final List<PathfindingCell> neighbors;
    private PathfindingCell previous;
    private final Sprite sprite;
    private float distance, distanceFromStart;

    public PathfindingCell(int column, int row, Sprite cellSprite, float size) {
        this.column = column;
        this.row = row;
        neighbors = new ArrayList<>();
        sprite = new Sprite(cellSprite);
        sprite.setSize(size, size);
    }

    public void draw(SpriteBatch spriteBatch) {
        sprite.draw(spriteBatch);
    }

    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    public void setColor(Color color) {
        sprite.setColor(color);
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public List<PathfindingCell> getNeighbors() {
        return neighbors;
    }

    public PathfindingCell getPrevious() {
        return previous;
    }

    public void setPrevious(PathfindingCell previous) {
        this.previous = previous;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getDistanceFromStart() {
        return distanceFromStart;
    }

    public void setDistanceFromStart(float distanceFromStart) {
        this.distanceFromStart = distanceFromStart;
    }
}
