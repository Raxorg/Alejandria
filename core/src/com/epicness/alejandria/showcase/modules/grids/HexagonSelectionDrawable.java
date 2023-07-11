package com.epicness.alejandria.showcase.modules.grids;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.epicness.alejandria.showcase.stuff.modules.grids.Hexagon;
import com.epicness.fundamentals.renderer.ShapeBatch;
import com.epicness.fundamentals.stuff.interfaces.Drawable;

import java.util.Arrays;

public class HexagonSelectionDrawable implements Drawable {

    private final Hexagon[][] hexagons;
    public static final int COLUMNS = 9, ROWS = 6;

    public HexagonSelectionDrawable() {
        float size = 96f;
        float[] vertices = new float[12];
        vertices[0] = 0f;
        vertices[1] = size / 2f;

        vertices[2] = size / 4f;
        vertices[3] = size;

        vertices[4] = size * 0.75f;
        vertices[5] = size;

        vertices[6] = size;
        vertices[7] = size / 2f;

        vertices[8] = size * 0.75f;
        vertices[9] = 0f;

        vertices[10] = size / 4f;
        vertices[11] = 0f;

        hexagons = new Hexagon[COLUMNS][];
        Vector2 centroid = new Vector2();
        for (int column = 0; column < COLUMNS; column++) {
            hexagons[column] = new Hexagon[ROWS];
            for (int row = 0; row < ROWS; row++) {
                float[] verts = Arrays.copyOf(vertices, vertices.length);
                for (int i = 0; i < verts.length; i += 2) {
                    verts[i] += 165f + column * size * 0.75f;
                    float yOffset = column % 2 == 0 ? 0 : size * 0.5f;
                    verts[i + 1] += 185f + row * size + yOffset;
                }
                Hexagon hexagon = new Hexagon(verts);
                hexagon.setOrigin(hexagon.getCentroid(centroid).x, centroid.y);
                hexagons[column][row] = hexagon;
            }
        }
        for (int column = 0; column < COLUMNS; column++) {
            for (int row = 0; row < ROWS; row++) {
                Hexagon hexagon = hexagons[column][row];
                float discriminator = column % 2 == 0 ? 1f : -1f;
                for (int c = column - 1; c <= column + 1; c++) {
                    for (int r = row - 1; r <= row + 1; r++) {
                        if (c < 0 || c == COLUMNS || r < 0 || r == ROWS) continue;
                        if ((c == column - 1 && r == row + discriminator)
                                || (c == column + 1 && r == row + discriminator)) continue;
                        if (hexagons[c][r] == hexagon) continue;
                        hexagon.neighbors.add(hexagons[c][r]);
                    }
                }
            }
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeBatch shapeBatch) {
        shapeBatch.begin();
        for (int column = 0; column < COLUMNS; column++) {
            for (int row = 0; row < ROWS; row++) {
                hexagons[column][row].draw(shapeBatch);
            }
        }
        shapeBatch.end();
    }

    @Override
    public void drawDebug(ShapeBatch shapeBatch) {
        for (int column = 0; column < COLUMNS; column++) {
            for (int row = 0; row < ROWS; row++) {
                hexagons[column][row].draw(shapeBatch);
            }
        }
    }

    public Hexagon[][] getHexagons() {
        return hexagons;
    }
}