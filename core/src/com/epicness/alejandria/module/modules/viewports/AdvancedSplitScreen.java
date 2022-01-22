package com.epicness.alejandria.module.modules.viewports;

import static com.badlogic.gdx.Input.Keys.A;
import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.Input.Keys.DOWN;
import static com.badlogic.gdx.Input.Keys.LEFT;
import static com.badlogic.gdx.Input.Keys.RIGHT;
import static com.badlogic.gdx.Input.Keys.S;
import static com.badlogic.gdx.Input.Keys.UP;
import static com.badlogic.gdx.Input.Keys.W;
import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.epicness.alejandria.Constants.INITIAL_WINDOW_SIZE;
import static com.epicness.fundamentals.SharedConstants.BLACK_CLEAR_50;
import static com.epicness.fundamentals.SharedConstants.DARK_GRASS;
import static com.epicness.fundamentals.SharedConstants.DIRT;
import static com.epicness.fundamentals.SharedConstants.GRASS;
import static com.epicness.fundamentals.SharedConstants.LIGHT_DIRT;
import static com.epicness.fundamentals.SharedConstants.LIGHT_GRASS;
import static com.epicness.fundamentals.SharedConstants.PIXEL_PATH;
import static com.epicness.fundamentals.SharedConstants.SQUARE_32_PATH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.ModuleID;
import com.epicness.alejandria.module.modules.Module;
import com.epicness.fundamentals.stuff.Circle;
import com.epicness.fundamentals.stuff.grid.Grid;

public class AdvancedSplitScreen extends Module {

    private static final float MIN_VIEWPORT_SIZE = INITIAL_WINDOW_SIZE / 2f;
    private static final float MAX_VIEWPORT_SIZE = INITIAL_WINDOW_SIZE;
    private static final float MAX_DISTANCE = MAX_VIEWPORT_SIZE / 2f;

    private static final float GRID_SIZE = INITIAL_WINDOW_SIZE * 2f;
    private static final int GRID_COLUMNS = 20, GRID_ROWS = 20;
    private static final float CELL_SIZE = GRID_SIZE / GRID_COLUMNS;

    private static final float PLAYER_RADIUS = 20;

    private OrthographicCamera camera1, camera2, camera3;
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;

    private Sprite cellSprite;
    private Grid grid;

    private Circle player1, player2;

    private Sprite mask, divider;

    private boolean playersClose, camerasClose;

    public AdvancedSplitScreen() {
        super(ModuleID.ADVANCED_SPLIT_SCREEN);
    }

    @Override
    public void setup() {
        initCameras();
        initGrid();
        initPlayers();
        initMask();
        initDivider();
    }

    private void initCameras() {
        camera1 = new OrthographicCamera();
        camera1.setToOrtho(false, MAX_VIEWPORT_SIZE, MAX_VIEWPORT_SIZE);
        camera2 = new OrthographicCamera();
        camera2.setToOrtho(false, MAX_VIEWPORT_SIZE, MAX_VIEWPORT_SIZE);
        camera3 = new OrthographicCamera();
        camera3.setToOrtho(false, INITIAL_WINDOW_SIZE, INITIAL_WINDOW_SIZE);
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
    }

    private void initGrid() {
        cellSprite = new Sprite(new Texture(Gdx.files.internal(SQUARE_32_PATH)));
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

    private void initMask() {
        mask = new Sprite(new Texture(Gdx.files.internal(PIXEL_PATH)));
        mask.setOrigin(INITIAL_WINDOW_SIZE, INITIAL_WINDOW_SIZE * 2f);
        mask.setSize(INITIAL_WINDOW_SIZE * 2f, INITIAL_WINDOW_SIZE * 2f);
        mask.setOriginBasedPosition(INITIAL_WINDOW_SIZE / 2f, INITIAL_WINDOW_SIZE / 2f);
        mask.rotate(90f);
        mask.setColor(BLACK_CLEAR_50);
    }

    private void initDivider() {
        divider = new Sprite(new Texture(Gdx.files.internal(PIXEL_PATH)));
        divider.setSize(INITIAL_WINDOW_SIZE * 2f, CELL_SIZE / 5f);
        divider.setOriginCenter();
        divider.setOriginBasedPosition(INITIAL_WINDOW_SIZE / 2f, INITIAL_WINDOW_SIZE / 2f);
        divider.setColor(BLACK);
    }

    @Override
    public void update(float delta) {
        handleInput(delta);
        limitPlayerPositions();
        updateCameraPositions();
        updateCameraZoom();
        limitCameraPositions();
        updateMask();
        playersClose = player1.getPosition().dst(player2.getPosition()) < MAX_DISTANCE;
        camerasClose = camera1.position.dst(camera2.position) == 0f;
    }

    private void handleInput(float delta) {
        float translation = 300f * delta;
        if (Gdx.input.isKeyPressed(W)) {
            player1.translateY(translation);
        }
        if (Gdx.input.isKeyPressed(A)) {
            player1.translateX(-translation);
        }
        if (Gdx.input.isKeyPressed(S)) {
            player1.translateY(-translation);
        }
        if (Gdx.input.isKeyPressed(D)) {
            player1.translateX(translation);
        }
        if (Gdx.input.isKeyPressed(UP)) {
            player2.translateY(translation);
        }
        if (Gdx.input.isKeyPressed(LEFT)) {
            player2.translateX(-translation);
        }
        if (Gdx.input.isKeyPressed(DOWN)) {
            player2.translateY(-translation);
        }
        if (Gdx.input.isKeyPressed(RIGHT)) {
            player2.translateX(translation);
        }
    }

    private void limitPlayerPositions() {
        player1.setX(MathUtils.clamp(player1.getX(), PLAYER_RADIUS, GRID_SIZE - player1.getRadius()));
        player1.setY(MathUtils.clamp(player1.getY(), PLAYER_RADIUS, GRID_SIZE - player1.getRadius()));
        player2.setX(MathUtils.clamp(player2.getX(), PLAYER_RADIUS, GRID_SIZE - player2.getRadius()));
        player2.setY(MathUtils.clamp(player2.getY(), PLAYER_RADIUS, GRID_SIZE - player2.getRadius()));
    }

    private void updateCameraPositions() {
        if (playersClose) {
            float averageX = (player1.getX() + player2.getX()) / 2f;
            float averageY = (player1.getY() + player2.getY()) / 2f;
            camera1.position.x = averageX;
            camera1.position.y = averageY;
            camera2.position.x = averageX;
            camera2.position.y = averageY;
        } else {
            float angle = MathUtils.atan2(player2.getY() - player1.getY(), player2.getX() - player1.getX());
            angle *= MathUtils.radiansToDegrees;
            float distanceX = MathUtils.cosDeg(angle) * MAX_DISTANCE;
            float distanceY = MathUtils.sinDeg(angle) * MAX_DISTANCE;
            camera1.position.x = player1.getX() + distanceX / 2f;
            camera1.position.y = player1.getY() + distanceY / 2f;
            camera2.position.x = player2.getX() - distanceX / 2f;
            camera2.position.y = player2.getY() - distanceY / 2f;
        }
    }

    private void updateCameraZoom() {
        if (!playersClose) {
            return;
        }
        float distance = player1.getPosition().dst(player2.getPosition());
        float viewportSize = MathUtils.map(0, MAX_DISTANCE, MIN_VIEWPORT_SIZE, MAX_VIEWPORT_SIZE, distance);
        viewportSize = Math.min(viewportSize, MAX_VIEWPORT_SIZE);
        camera1.viewportWidth = viewportSize;
        camera1.viewportHeight = viewportSize;
    }

    private void limitCameraPositions() {
        float minCameraPosition = camera1.viewportWidth / 2f;
        float maxCameraPosition = GRID_SIZE - camera1.viewportWidth / 2f;
        camera1.position.x = MathUtils.clamp(camera1.position.x, minCameraPosition, maxCameraPosition);
        camera1.position.y = MathUtils.clamp(camera1.position.y, minCameraPosition, maxCameraPosition);
        camera1.update();

        minCameraPosition = camera2.viewportWidth / 2f;
        maxCameraPosition = GRID_SIZE - camera2.viewportWidth / 2f;
        camera2.position.x = MathUtils.clamp(camera2.position.x, minCameraPosition, maxCameraPosition);
        camera2.position.y = MathUtils.clamp(camera2.position.y, minCameraPosition, maxCameraPosition);
        camera2.update();
    }

    private void updateMask() {
        float angle = MathUtils.atan2(player2.getY() - player1.getY(), player2.getX() - player1.getX());
        angle *= MathUtils.radiansToDegrees;
        angle += 90f;
        mask.setRotation(angle);
        divider.setRotation(angle);
    }

    @Override
    public void draw() {
        drawUnmasked();
        if (playersClose || camerasClose) {
            return;
        }
        applyMask();
        drawMasked();
        drawUnmasked2();
    }

    private void drawUnmasked() {
        spriteBatch.begin();
        spriteBatch.setProjectionMatrix(camera1.combined);
        grid.draw(spriteBatch);
        spriteBatch.end();

        shapeRenderer.setProjectionMatrix(camera1.combined);
        shapeRenderer.begin(Filled);
        player1.draw(shapeRenderer);
        player2.draw(shapeRenderer);
        shapeRenderer.end();
    }

    private void applyMask() {
        spriteBatch.setProjectionMatrix(camera3.combined);
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

    private void drawMasked() {
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

    private void drawUnmasked2() {
        // 6. Back to default depth test
        Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
        spriteBatch.setProjectionMatrix(camera3.combined);
        spriteBatch.begin();
        divider.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        cellSprite.getTexture().dispose();
        divider.getTexture().dispose();
        mask.getTexture().dispose();
    }
}