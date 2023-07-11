package com.epicness.alejandria.showcase.modules.viewports;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.epicness.alejandria.showcase.constants.AdvancedSplitScreenConstants.CELL_SIZE;
import static com.epicness.alejandria.showcase.constants.AdvancedSplitScreenConstants.DIVIDER_THICKNESS;
import static com.epicness.alejandria.showcase.constants.AdvancedSplitScreenConstants.GRID_COLUMNS;
import static com.epicness.alejandria.showcase.constants.AdvancedSplitScreenConstants.GRID_ROWS;
import static com.epicness.alejandria.showcase.constants.AdvancedSplitScreenConstants.GRID_SIZE;
import static com.epicness.alejandria.showcase.constants.AdvancedSplitScreenConstants.MAX_VIEWPORT_SIZE;
import static com.epicness.alejandria.showcase.constants.AdvancedSplitScreenConstants.PLAYER_RADIUS;
import static com.epicness.fundamentals.SharedConstants.BLACK_CLEAR_50;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;
import static com.epicness.fundamentals.SharedConstants.DARK_GRASS;
import static com.epicness.fundamentals.SharedConstants.DIRT;
import static com.epicness.fundamentals.SharedConstants.GRASS;
import static com.epicness.fundamentals.SharedConstants.LIGHT_DIRT;
import static com.epicness.fundamentals.SharedConstants.LIGHT_GRASS;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.renderer.ShapeBatch;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.stuff.grid.Grid;
import com.epicness.fundamentals.stuff.interfaces.Drawable;
import com.epicness.fundamentals.stuff.shapes.Circle;

public class AdvancedSplitScreenDrawable implements Drawable {

    private Grid grid;
    private Circle player1, player2;
    private Sprited mask, divider;
    private OrthographicCamera camera1, camera2, staticCamera;
    private boolean playersClose, camerasClose;

    public AdvancedSplitScreenDrawable(Sprite cellSprite, Sprite pixelSprite, OrthographicCamera staticCamera) {
        initCameras(staticCamera);
        initGrid(cellSprite);
        initPlayers();
        initMask(pixelSprite);
        initDivider(pixelSprite);
        playersClose = true;
        camerasClose = true;
    }

    private void initCameras(OrthographicCamera staticCamera) {
        camera1 = new OrthographicCamera();
        camera1.setToOrtho(false, MAX_VIEWPORT_SIZE, MAX_VIEWPORT_SIZE);
        camera2 = new OrthographicCamera();
        camera2.setToOrtho(false, MAX_VIEWPORT_SIZE, MAX_VIEWPORT_SIZE);
        this.staticCamera = staticCamera;
    }

    private void initGrid(Sprite cellSprite) {
        grid = new Grid(GRID_COLUMNS, GRID_ROWS, cellSprite);
        grid.setCellSize(CELL_SIZE);
        for (int column = 0; column < GRID_COLUMNS; column++) {
            for (int row = 0; row < GRID_ROWS; row++) {
                grid.getCells()[column][row].setColor(DARK_GRASS);
                if ((column + row) % 3 == 0) {
                    grid.getCells()[column][row].setColor(DIRT);
                } else if ((column + row) % 3 == 1) {
                    grid.getCells()[column][row].setColor(LIGHT_DIRT);
                }
            }
        }
    }

    private void initPlayers() {
        player1 = new Circle(PLAYER_RADIUS);
        player1.setPosition(GRID_SIZE / 2f, GRID_SIZE / 2f);
        player1.setColor(GRASS);
        player2 = new Circle(PLAYER_RADIUS);
        player2.setPosition(GRID_SIZE / 2f, GRID_SIZE / 2f);
        player2.setColor(LIGHT_GRASS);
    }

    private void initMask(Sprite maskSprite) {
        mask = new Sprited(maskSprite);
        mask.setOrigin(CAMERA_WIDTH, CAMERA_HEIGHT * 2f);
        mask.setSize(CAMERA_WIDTH * 2f, CAMERA_HEIGHT * 2f);
        mask.setOriginBasedPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);
        mask.rotate(90f);
        mask.setColor(BLACK_CLEAR_50);
    }

    private void initDivider(Sprite dividerSprite) {
        divider = new Sprited(dividerSprite);
        divider.setSize(CAMERA_WIDTH * 2f, DIVIDER_THICKNESS);
        divider.setOriginCenter();
        divider.setOriginBasedPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);
        divider.setColor(BLACK);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeBatch shapeBatch) {
        drawUnmasked(spriteBatch, shapeBatch);
        if (playersClose || camerasClose) {
            return;
        }
        applyMask(spriteBatch);
        drawMasked(spriteBatch, shapeBatch);
        drawUnmasked2(spriteBatch);
    }

    private void drawUnmasked(SpriteBatch spriteBatch, ShapeBatch shapeBatch) {
        spriteBatch.setProjectionMatrix(camera1.combined);
        spriteBatch.begin();
        grid.draw(spriteBatch);
        spriteBatch.end();
        spriteBatch.setProjectionMatrix(staticCamera.combined);

        shapeBatch.setProjectionMatrix(camera1.combined);
        shapeBatch.begin(Filled);
        player1.draw(shapeBatch);
        player2.draw(shapeBatch);
        shapeBatch.end();
        shapeBatch.setProjectionMatrix(staticCamera.combined);
    }

    private void applyMask(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(staticCamera.combined);
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
    }

    private void drawMasked(SpriteBatch spriteBatch, ShapeBatch shapeBatch) {
        Gdx.gl.glColorMask(true, true, true, true);
        Gdx.gl.glDepthFunc(GL20.GL_EQUAL);

        spriteBatch.setProjectionMatrix(camera2.combined);
        spriteBatch.begin();
        grid.draw(spriteBatch);
        spriteBatch.end();

        shapeBatch.setProjectionMatrix(camera2.combined);
        shapeBatch.begin(Filled);
        player1.draw(shapeBatch);
        player2.draw(shapeBatch);
        shapeBatch.end();
    }

    private void drawUnmasked2(SpriteBatch spriteBatch) {
        // 6. Back to default depth test
        Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
        spriteBatch.setProjectionMatrix(staticCamera.combined);
        spriteBatch.begin();
        divider.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeBatch shapeBatch) {
    }

    public Circle getPlayer1() {
        return player1;
    }

    public Circle getPlayer2() {
        return player2;
    }

    public Sprited getMask() {
        return mask;
    }

    public Sprited getDivider() {
        return divider;
    }

    public OrthographicCamera getCamera1() {
        return camera1;
    }

    public OrthographicCamera getCamera2() {
        return camera2;
    }

    public boolean arePlayersClose() {
        return playersClose;
    }

    public void setPlayersClose(boolean playersClose) {
        this.playersClose = playersClose;
    }

    public void setCamerasClose(boolean camerasClose) {
        this.camerasClose = camerasClose;
    }
}