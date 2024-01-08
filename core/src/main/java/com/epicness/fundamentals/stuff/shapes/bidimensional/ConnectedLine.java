package com.epicness.fundamentals.stuff.shapes.bidimensional;

import com.badlogic.gdx.math.Vector2;

public class ConnectedLine extends Line {

    private ConnectedLine next;

    public ConnectedLine(float ax, float ay, float bx, float by) {
        super(ax, ay, bx, by);
    }

    public ConnectedLine(float x, float y, float length, float angleDeg, boolean degrees) {
        super(x, y, length, angleDeg, degrees);
    }

    public ConnectedLine(float x, float y, float length) {
        super(x, y, length, 0, true);
    }

    public ConnectedLine(ConnectedLine previous, float length, float angleDeg) {
        super(previous, length, angleDeg);
        previous.next = this;
    }

    public ConnectedLine(ConnectedLine previous, float length) {
        this(previous, length, 0f);
    }

    @Override
    public void setA(Vector2 position) {
        super.setA(position);
        if (next != null) {
            next.setA(getB());
        }
    }

    @Override
    public void rotate(float degrees) {
        super.rotate(degrees);
        if (next != null) {
            next.setA(getB().cpy());
            next.rotate(degrees);
        }
    }
}