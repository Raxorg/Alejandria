package com.epicness.alejandria.module.stuff.modules.masking;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.epicness.alejandria.ModuleID.LAYERED_MASKING;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;
import static com.epicness.fundamentals.SharedConstants.GRASS;
import static com.epicness.fundamentals.SharedConstants.LIGHT_GRASS;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.module.stuff.modules.Module;
import com.epicness.fundamentals.stuff.Circle;
import com.epicness.fundamentals.stuff.DualSprited;
import com.epicness.fundamentals.stuff.grid.Grid;

import java.util.ArrayList;

public class LayeredMasking extends Module {

    // Module specific
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private Texture weirdShape, square32, square32Inverted, pixel;
    private Sprite mask;
    private Grid gridA, gridB;
    private Circle circle1, circle2;
    private ArrayList<DualSprited> shapes;

    private static final int GRID_COLUMNS = 10, GRID_ROWS = 10;
    private static final float SHAPE_SIZE = 100f;

    public LayeredMasking() {
        super(LAYERED_MASKING);
    }

    @Override
    public void setup() {
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        Gdx.gl.glLineWidth(3f);

        weirdShape = new Texture(Gdx.files.internal("images/shared/weirdShape.png"));
        square32 = new Texture(Gdx.files.internal("images/shared/square32.png"));
        square32Inverted = new Texture(Gdx.files.internal("images/shared/square32Inverted.png"));
        pixel = new Texture(Gdx.files.internal("images/shared/pixel.png"));

        mask = new Sprite(weirdShape);
        mask.setOrigin(CAMERA_WIDTH, CAMERA_HEIGHT * 2f);
        mask.setOriginBasedPosition(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f);
        mask.setSize(CAMERA_WIDTH * 2f, CAMERA_HEIGHT * 2f);
        mask.setColor(Color.BLUE);

        Sprite cellSprite = new Sprite(square32);
        gridA = new Grid(GRID_COLUMNS, GRID_ROWS, cellSprite);
        gridA.setCellSize(100f);
        for (int column = 0; column < GRID_COLUMNS; column++) {
            for (int row = 0; row < GRID_ROWS; row++) {
                Color color = (column + row) % 2 == 0 ? GRASS : LIGHT_GRASS;
                gridA.getCells()[column][row].setColor(color);
            }
        }

        cellSprite = new Sprite(square32Inverted);
        gridB = new Grid(GRID_COLUMNS, GRID_ROWS, cellSprite);
        gridB.setCellSize(100f);
        for (int column = 0; column < GRID_COLUMNS; column++) {
            for (int row = 0; row < GRID_ROWS; row++) {
                Color color = (column + row) % 2 == 0 ? GRASS : LIGHT_GRASS;
                gridB.getCells()[column][row].setColor(color);
            }
        }

        circle1 = new Circle(CAMERA_HEIGHT / 4f);
        circle1.setPosition(CAMERA_WIDTH / 2f, 750f);
        circle1.setColor(Color.BLACK);

        circle2 = new Circle(CAMERA_HEIGHT / 4f);
        circle2.setPosition(CAMERA_WIDTH / 2f, 250f);
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
    public void update(float delta) {
        mask.rotate(delta * 5f);
        for (int i = 0; i < shapes.size(); i++) {
            DualSprited shape = shapes.get(i);
            shape.translateY(delta * 30f);
            if (shape.getY() >= CAMERA_HEIGHT) {
                shape.setY(-SHAPE_SIZE);
            }
        }

        circle1.translateX(delta * 100f);
        if (circle1.getX() - circle1.getRadius() >= CAMERA_WIDTH) {
            circle1.setX(-circle1.getRadius());
        }

        circle2.translateX(-delta * 100f);
        if (circle2.getX() + circle2.getRadius() <= 0f) {
            circle2.setX(CAMERA_WIDTH + circle2.getRadius());
        }
    }

    @Override
    public void draw() {
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
    public void dispose() {
        weirdShape.dispose();
        square32.dispose();
        square32Inverted.dispose();
        pixel.dispose();
    }
}