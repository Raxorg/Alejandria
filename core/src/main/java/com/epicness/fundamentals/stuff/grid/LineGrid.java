package com.epicness.fundamentals.stuff.grid;

import com.badlogic.gdx.graphics.Color;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Line;

public class LineGrid extends Grid {

    private final Line[] lines;

    public LineGrid(int columns, int rows, float spacing, float lineWidth) {
        super(columns, rows);
        float maxX = columns * spacing;
        float maxY = rows * spacing;

        int lineTotal = columns + rows + 2;
        lines = new Line[lineTotal];
        for (int column = 0; column <= columns; column++) {
            lines[column] = new Line(column * spacing, 0f, column * spacing, maxY, lineWidth);
        }
        for (int i = columns + 1; i < lineTotal; i++) {
            int row = i - columns - 1;
            lines[i] = new Line(0f, row * spacing, maxX, row * spacing, lineWidth);
        }
    }

    public void draw(ShapeDrawerPlus shapeDrawer) {
        for (int i = 0; i < lines.length; i++) {
            lines[i].draw(shapeDrawer);
        }
    }

    @Override
    public void setColor(Color color) {
        for (int i = 0; i < lines.length; i++) {
            lines[i].setColor(color);
        }
    }
}