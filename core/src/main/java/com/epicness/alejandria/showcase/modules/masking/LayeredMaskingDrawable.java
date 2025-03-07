package com.epicness.alejandria.showcase.modules.masking;

import static com.badlogic.gdx.graphics.Color.BLUE;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.epicness.alejandria.showcase.constants.MaskingConstants.GRID_COLUMNS;
import static com.epicness.alejandria.showcase.constants.MaskingConstants.GRID_ROWS;
import static com.epicness.alejandria.showcase.constants.MaskingConstants.LAYERED_CIRCLE_RADIUS;
import static com.epicness.alejandria.showcase.constants.MaskingConstants.SHAPE_SIZE;
import static com.epicness.fundamentals.constants.ColorConstants.GRASS;
import static com.epicness.fundamentals.constants.ColorConstants.LIGHT_GRASS;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.DualSprited;
import com.epicness.fundamentals.stuff.SpritePlus;
import com.epicness.fundamentals.stuff.grid.DefaultCellGrid;
import com.epicness.fundamentals.stuff.shapes.bidimensional.CirclePlus;

import java.util.ArrayList;
import java.util.List;

public class LayeredMaskingDrawable implements ModuleDrawable {

    private final SpritePlus mask;
    private final DefaultCellGrid gridA, gridB;
    private final CirclePlus circle1, circle2;
    private final List<DualSprited> shapes;

    public LayeredMaskingDrawable(Sprite weirdShape, Sprite square32, Sprite square32Inverted, Sprite pixel) {
        mask = new SpritePlus(pixel);
        mask.setOrigin(VIEWPORT_WIDTH, VIEWPORT_HEIGHT * 2f);
        mask.setOriginBasedPosition(VIEWPORT_HALF_WIDTH, VIEWPORT_HALF_HEIGHT);
        mask.setSize(VIEWPORT_WIDTH * 2f, VIEWPORT_HEIGHT * 2f);
        mask.setColor(BLUE);

        Sprite cellSprite = new Sprite(square32);

        gridA = new DefaultCellGrid(cellSprite, GRID_COLUMNS, GRID_ROWS);
        gridA.setCellSize(100f);
        for (int column = 0; column < GRID_COLUMNS; column++) {
            for (int row = 0; row < GRID_ROWS; row++) {
                Color color = (column + row) % 2 == 0 ? GRASS : LIGHT_GRASS;
                gridA.cells[column][row].setColor(color);
            }
        }

        gridB = new DefaultCellGrid(square32Inverted, GRID_COLUMNS, GRID_ROWS);
        gridB.setCellSize(100f);
        Color color;
        for (int column = 0; column < GRID_COLUMNS; column++) {
            for (int row = 0; row < GRID_ROWS; row++) {
                color = (column + row) % 2 == 0 ? GRASS : LIGHT_GRASS;
                gridB.cells[column][row].setColor(color);
            }
        }

        circle1 = new CirclePlus(LAYERED_CIRCLE_RADIUS, WHITE);
        circle1.setPosition(VIEWPORT_HALF_WIDTH - LAYERED_CIRCLE_RADIUS, VIEWPORT_HALF_HEIGHT);
        circle1.setThickness(3f);

        circle2 = new CirclePlus(LAYERED_CIRCLE_RADIUS, WHITE);
        circle2.setX(VIEWPORT_HALF_WIDTH - LAYERED_CIRCLE_RADIUS);
        circle2.setThickness(3f);

        Sprite backgroundSprite = new Sprite(pixel);
        Sprite foregroundSprite = new Sprite(weirdShape);
        shapes = new ArrayList<>();
        for (int i = 0; i < GRID_COLUMNS; i++) {
            DualSprited shape = new DualSprited(backgroundSprite, foregroundSprite);
            shape.setX(SHAPE_SIZE * i);
            shape.setSize(SHAPE_SIZE);
            shape.setColor(Color.ORANGE);
            shapes.add(shape);
        }

        for (int i = 0; i < GRID_COLUMNS; i++) {
            DualSprited shape = new DualSprited(backgroundSprite, foregroundSprite);
            shape.setPosition(SHAPE_SIZE * i, VIEWPORT_HEIGHT * 0.375f);
            shape.setSize(SHAPE_SIZE);
            shape.setColor(Color.PURPLE);
            shapes.add(shape);
        }

        for (int i = 0; i < GRID_COLUMNS; i++) {
            DualSprited shape = new DualSprited(backgroundSprite, foregroundSprite);
            shape.setPosition(SHAPE_SIZE * i, VIEWPORT_HEIGHT * 0.75f);
            shape.setSize(SHAPE_SIZE);
            shape.setColor(Color.GREEN);
            shapes.add(shape);
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        drawUnmasked(spriteBatch);
        drawMask(spriteBatch, shapeDrawer);
        drawMasked(spriteBatch, shapeDrawer);
        drawMask2(spriteBatch, shapeDrawer);
        drawMasked2(spriteBatch, shapeDrawer);
        // Back to default depth test
        Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
    }

    private void drawUnmasked(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        gridA.draw(spriteBatch);
        for (DualSprited shape : shapes) {
            shape.drawBackground(spriteBatch);
        }
        spriteBatch.end();
    }

    private void drawMask(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer) {
        // 1. Clear our depth buffer with 1.0
        Gdx.gl.glClear(GL20.GL_DEPTH_BUFFER_BIT);

        // 2. Set the function to LESS
        Gdx.gl.glDepthFunc(GL20.GL_LESS);

        // 3. Enable depth writing
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);

        // 4. Enable depth writing, disable RGBA color writing
        Gdx.gl.glColorMask(false, false, false, false);

        // 5. Render mask elements
        spriteBatch.begin();
        Gdx.gl.glDepthMask(true);

        mask.draw(spriteBatch);

        circle1.draw(shapeDrawer);
        spriteBatch.end();
    }

    private void drawMasked(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer) {
        Gdx.gl.glColorMask(true, true, true, true);
        Gdx.gl.glDepthFunc(GL20.GL_EQUAL);
        spriteBatch.begin();
        gridB.draw(spriteBatch);
        for (DualSprited shape : shapes) {
            shape.drawForeground(spriteBatch);
        }
        circle1.drawBorder(shapeDrawer);
        spriteBatch.end();
    }

    private void drawMask2(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer) {
        // 1. Clear our depth buffer with 1.0
        Gdx.gl.glClear(GL20.GL_DEPTH_BUFFER_BIT);

        // 2. Set the function to LESS
        Gdx.gl.glDepthFunc(GL20.GL_LESS);

        // 3. Enable depth writing
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);

        // 4. Enable depth writing, disable RGBA color writing
        Gdx.gl.glColorMask(false, false, false, false);

        // 5. Render mask elements
        spriteBatch.begin();
        Gdx.gl.glDepthMask(true);

        //stuff.getMask().draw(spriteBatch);

        circle2.draw(shapeDrawer);
        spriteBatch.end();
    }

    private void drawMasked2(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer) {
        Gdx.gl.glColorMask(true, true, true, true);
        Gdx.gl.glDepthFunc(GL20.GL_EQUAL);
        spriteBatch.begin();
        gridA.draw(spriteBatch);
        for (DualSprited shape : shapes) {
            shape.drawBackground(spriteBatch);
        }
        circle2.drawBorder(shapeDrawer);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeDrawerPlus shapeDrawer) {
    }

    public SpritePlus getMask() {
        return mask;
    }

    public List<DualSprited> getShapes() {
        return shapes;
    }

    public CirclePlus getCircle1() {
        return circle1;
    }

    public CirclePlus getCircle2() {
        return circle2;
    }
}