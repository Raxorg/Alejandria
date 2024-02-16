package com.epicness.alejandria.showcase.modules.masking;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.epicness.alejandria.showcase.constants.LayeredMaskingConstants.GRID_COLUMNS;
import static com.epicness.alejandria.showcase.constants.LayeredMaskingConstants.GRID_ROWS;
import static com.epicness.alejandria.showcase.constants.LayeredMaskingConstants.SHAPE_SIZE;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;
import static com.epicness.fundamentals.constants.SharedConstants.GRASS;
import static com.epicness.fundamentals.constants.SharedConstants.LIGHT_GRASS;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.DualSprited;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.stuff.grid.DefaultCellBuilder;
import com.epicness.fundamentals.stuff.grid.DefaultCellGrid;
import com.epicness.fundamentals.stuff.grid.DefaultCellGridBuilder;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Circle;

import java.util.ArrayList;
import java.util.List;

public class LayeredMaskingDrawable implements ModuleDrawable {

    private final Sprited mask;
    private final DefaultCellGrid gridA, gridB;
    private final Circle circle1, circle2;
    private final List<DualSprited> shapes;

    public LayeredMaskingDrawable(Sprite weirdShape, Sprite square32, Sprite square32Inverted, Sprite pixel) {
        mask = new Sprited(pixel);
        mask.setOrigin(CAMERA_WIDTH, CAMERA_HEIGHT * 2f);
        mask.setOriginBasedPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);
        mask.setSize(CAMERA_WIDTH * 2f, CAMERA_HEIGHT * 2f);
        mask.setColor(Color.BLUE);

        Sprite cellSprite = new Sprite(square32);
        DefaultCellGridBuilder builder = new DefaultCellGridBuilder(
            new DefaultCellBuilder().sprite(cellSprite))
            .columns(GRID_COLUMNS)
            .rows(GRID_ROWS);
        gridA = new DefaultCellGrid(builder);
        gridA.setCellSize(100f);
        for (int column = 0; column < GRID_COLUMNS; column++) {
            for (int row = 0; row < GRID_ROWS; row++) {
                Color color = (column + row) % 2 == 0 ? GRASS : LIGHT_GRASS;
                gridA.getCells()[column][row].setColor(color);
            }
        }


        builder.getCellBuilder().sprite(square32Inverted);
        gridB = new DefaultCellGrid(builder);
        gridB.setCellSize(100f);
        for (int column = 0; column < GRID_COLUMNS; column++) {
            for (int row = 0; row < GRID_ROWS; row++) {
                Color color = (column + row) % 2 == 0 ? GRASS : LIGHT_GRASS;
                gridB.getCells()[column][row].setColor(color);
            }
        }

        circle1 = new Circle(CAMERA_HALF_HEIGHT / 2f);
        circle1.setPosition(CAMERA_HALF_WIDTH, (CAMERA_HALF_HEIGHT / 2f) * 3f);
        circle1.setColor(Color.BLACK);

        circle2 = new Circle(CAMERA_HALF_HEIGHT / 2f);
        circle2.setPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT / 2f);
        circle2.setColor(Color.BLACK);

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
            shape.setPosition(SHAPE_SIZE * i, CAMERA_HEIGHT * 0.375f);
            shape.setSize(SHAPE_SIZE);
            shape.setColor(Color.PURPLE);
            shapes.add(shape);
        }

        for (int i = 0; i < GRID_COLUMNS; i++) {
            DualSprited shape = new DualSprited(backgroundSprite, foregroundSprite);
            shape.setPosition(SHAPE_SIZE * i, CAMERA_HEIGHT * 0.75f);
            shape.setSize(SHAPE_SIZE);
            shape.setColor(Color.GREEN);
            shapes.add(shape);
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRendererPlus shapeRenderer) {
        drawUnmasked(spriteBatch);
        drawMask(spriteBatch, shapeRenderer);
        drawMasked(spriteBatch, shapeRenderer);
        drawMask2(spriteBatch, shapeRenderer);
        drawMasked2(spriteBatch, shapeRenderer);
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

    private void drawMask(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
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

        spriteBatch.end();

        shapeRenderer.begin(Filled);
        circle1.draw(shapeRenderer);
        shapeRenderer.end();
    }

    private void drawMasked(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        Gdx.gl.glColorMask(true, true, true, true);
        Gdx.gl.glDepthFunc(GL20.GL_EQUAL);
        spriteBatch.begin();
        gridB.draw(spriteBatch);
        for (DualSprited shape : shapes) {
            shape.drawForeground(spriteBatch);
        }
        spriteBatch.end();
        shapeRenderer.begin(Filled);
        circle1.drawContour(shapeRenderer);
        shapeRenderer.end();
    }

    private void drawMask2(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
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

        spriteBatch.end();

        shapeRenderer.begin(Filled);
        circle2.draw(shapeRenderer);
        shapeRenderer.end();
    }

    private void drawMasked2(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        Gdx.gl.glColorMask(true, true, true, true);
        Gdx.gl.glDepthFunc(GL20.GL_EQUAL);
        spriteBatch.begin();
        gridA.draw(spriteBatch);
        for (DualSprited shape : shapes) {
            shape.drawBackground(spriteBatch);
        }
        spriteBatch.end();
        shapeRenderer.begin(Filled);
        circle2.drawContour(shapeRenderer);
        shapeRenderer.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
    }

    public Sprited getMask() {
        return mask;
    }

    public List<DualSprited> getShapes() {
        return shapes;
    }

    public Circle getCircle1() {
        return circle1;
    }

    public Circle getCircle2() {
        return circle2;
    }
}