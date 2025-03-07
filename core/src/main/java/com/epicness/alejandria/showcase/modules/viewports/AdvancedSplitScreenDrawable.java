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
import static com.epicness.fundamentals.constants.ColorConstants.BLACK_50;
import static com.epicness.fundamentals.constants.ColorConstants.DARK_GRASS;
import static com.epicness.fundamentals.constants.ColorConstants.DIRT;
import static com.epicness.fundamentals.constants.ColorConstants.GRASS;
import static com.epicness.fundamentals.constants.ColorConstants.LIGHT_DIRT;
import static com.epicness.fundamentals.constants.ColorConstants.LIGHT_GRASS;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HALF_WIDTH;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.SpritePlus;
import com.epicness.fundamentals.stuff.grid.DefaultCellGrid;
import com.epicness.fundamentals.stuff.shapes.bidimensional.CirclePlus;

public class AdvancedSplitScreenDrawable implements ModuleDrawable {

    private DefaultCellGrid grid;
    private CirclePlus player1, player2;
    private SpritePlus mask, divider;
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
        grid = new DefaultCellGrid(cellSprite, GRID_COLUMNS, GRID_ROWS);
        grid.setCellSize(CELL_SIZE);
        for (int column = 0; column < GRID_COLUMNS; column++) {
            for (int row = 0; row < GRID_ROWS; row++) {
                grid.cells[column][row].setColor(DARK_GRASS);
                if ((column + row) % 3 == 0) {
                    grid.cells[column][row].setColor(DIRT);
                } else if ((column + row) % 3 == 1) {
                    grid.cells[column][row].setColor(LIGHT_DIRT);
                }
            }
        }
    }

    private void initPlayers() {
        player1 = new CirclePlus(PLAYER_RADIUS);
        player1.setPosition(GRID_SIZE * 0.5f - PLAYER_RADIUS, GRID_SIZE * 0.5f - PLAYER_RADIUS);
        player1.setColor(GRASS);
        player2 = new CirclePlus(PLAYER_RADIUS);
        player2.setPosition(GRID_SIZE * 0.5f - PLAYER_RADIUS, GRID_SIZE * 0.5f - PLAYER_RADIUS);
        player2.setColor(LIGHT_GRASS);
    }

    private void initMask(Sprite maskSprite) {
        mask = new SpritePlus(maskSprite);
        mask.setOrigin(VIEWPORT_WIDTH, VIEWPORT_HEIGHT * 2f);
        mask.setSize(VIEWPORT_WIDTH * 2f, VIEWPORT_HEIGHT * 2f);
        mask.setOriginBasedPosition(VIEWPORT_HALF_WIDTH, VIEWPORT_HALF_HEIGHT);
        mask.rotate(90f);
        mask.setColor(BLACK_50);
    }

    private void initDivider(Sprite dividerSprite) {
        divider = new SpritePlus(dividerSprite);
        divider.setSize(VIEWPORT_WIDTH * 2f, DIVIDER_THICKNESS);
        divider.setOriginCenter();
        divider.setOriginBasedPosition(VIEWPORT_HALF_WIDTH, VIEWPORT_HALF_HEIGHT);
        divider.setColor(BLACK);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        ScreenUtils.clear(BLACK);
        drawUnmasked(spriteBatch, shapeRenderer);
        if (playersClose || camerasClose) {
            return;
        }
        applyMask(spriteBatch);
        drawMasked(spriteBatch, shapeRenderer);
        drawUnmasked2(spriteBatch);
    }

    private void drawUnmasked(SpriteBatch spriteBatch, ShapeRendererPlus shapeRenderer) {
        spriteBatch.setProjectionMatrix(camera1.combined);
        spriteBatch.begin();
        grid.draw(spriteBatch);
        spriteBatch.end();
        spriteBatch.setProjectionMatrix(staticCamera.combined);

        shapeRenderer.setProjectionMatrix(camera1.combined);
        shapeRenderer.begin(Filled);
        player1.draw(shapeRenderer);
        player2.draw(shapeRenderer);
        shapeRenderer.end();
        shapeRenderer.setProjectionMatrix(staticCamera.combined);
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

    private void drawMasked(SpriteBatch spriteBatch, ShapeRendererPlus shapeRenderer) {
        Gdx.gl.glColorMask(true, true, true, true);
        Gdx.gl.glDepthFunc(GL20.GL_EQUAL);

        spriteBatch.setProjectionMatrix(camera2.combined);
        spriteBatch.begin();
        grid.draw(spriteBatch);
        spriteBatch.end();

        shapeRenderer.setProjectionMatrix(camera2.combined);
        shapeRenderer.begin(Filled);
        player1.draw(shapeRenderer);
        player2.draw(shapeRenderer);
        shapeRenderer.end();
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
    public void drawDebug(ShapeDrawerPlus shapeDrawer) {
    }

    public CirclePlus getPlayer1() {
        return player1;
    }

    public CirclePlus getPlayer2() {
        return player2;
    }

    public SpritePlus getMask() {
        return mask;
    }

    public SpritePlus getDivider() {
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