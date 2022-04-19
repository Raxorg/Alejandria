package com.epicness.alejandria.showcase.logic.modules.viewports;

import static com.badlogic.gdx.Input.Keys.A;
import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.Input.Keys.DOWN;
import static com.badlogic.gdx.Input.Keys.LEFT;
import static com.badlogic.gdx.Input.Keys.RIGHT;
import static com.badlogic.gdx.Input.Keys.S;
import static com.badlogic.gdx.Input.Keys.UP;
import static com.badlogic.gdx.Input.Keys.W;
import static com.epicness.alejandria.showcase.constants.AdvancedSplitScreenConstants.GRID_SIZE;
import static com.epicness.alejandria.showcase.constants.AdvancedSplitScreenConstants.MAX_DISTANCE;
import static com.epicness.alejandria.showcase.constants.AdvancedSplitScreenConstants.MAX_VIEWPORT_SIZE;
import static com.epicness.alejandria.showcase.constants.AdvancedSplitScreenConstants.MIN_VIEWPORT_SIZE;
import static com.epicness.alejandria.showcase.constants.AdvancedSplitScreenConstants.PLAYER_RADIUS;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.stuff.modules.viewports.AdvancedSplitScreenDrawable;
import com.epicness.fundamentals.stuff.Circle;
import com.epicness.fundamentals.stuff.Sprited;

public class AdvancedSplitScreen extends Module {

    private AdvancedSplitScreenDrawable drawable;

    public AdvancedSplitScreen() {
        super("Advanced Split Screen");
    }

    @Override
    public void setup() {
        drawable = new AdvancedSplitScreenDrawable(sharedAssets.getSquare(), sharedAssets.getPixel());
        stuff.getShowcase().setDrawable(drawable);
    }

    @Override
    public void update(float delta) {
        handleInput(delta);
        limitPlayerPositions();
        updateCameraPositions();
        updateCameraZoom();
        limitCameraPositions();
        updateMask();
        Circle player1 = drawable.getPlayer1(), player2 = drawable.getPlayer2();
        drawable.setPlayersClose(player1.getPosition().dst(player2.getPosition()) < MAX_DISTANCE);
        Camera camera1 = drawable.getCamera1(), camera2 = drawable.getCamera2();
        drawable.setCamerasClose(camera1.position.dst(camera2.position) == 0f);
    }

    private void handleInput(float delta) {
        Circle player1 = drawable.getPlayer1(), player2 = drawable.getPlayer2();
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
        Circle player1 = drawable.getPlayer1(), player2 = drawable.getPlayer2();
        player1.setX(MathUtils.clamp(player1.getX(), PLAYER_RADIUS, GRID_SIZE - player1.getRadius()));
        player1.setY(MathUtils.clamp(player1.getY(), PLAYER_RADIUS, GRID_SIZE - player1.getRadius()));
        player2.setX(MathUtils.clamp(player2.getX(), PLAYER_RADIUS, GRID_SIZE - player2.getRadius()));
        player2.setY(MathUtils.clamp(player2.getY(), PLAYER_RADIUS, GRID_SIZE - player2.getRadius()));
    }

    private void updateCameraPositions() {
        Circle player1 = drawable.getPlayer1(), player2 = drawable.getPlayer2();
        Camera camera1 = drawable.getCamera1(), camera2 = drawable.getCamera2();
        if (drawable.arePlayersClose()) {
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
        if (!drawable.arePlayersClose()) {
            return;
        }
        Circle player1 = drawable.getPlayer1(), player2 = drawable.getPlayer2();
        float distance = player1.getPosition().dst(player2.getPosition());
        float viewportSize = MathUtils.map(0, MAX_DISTANCE, MIN_VIEWPORT_SIZE, MAX_VIEWPORT_SIZE, distance);
        viewportSize = Math.min(viewportSize, MAX_VIEWPORT_SIZE);
        Camera camera1 = drawable.getCamera1();
        camera1.viewportWidth = viewportSize;
        camera1.viewportHeight = viewportSize;
    }

    private void limitCameraPositions() {
        Camera camera1 = drawable.getCamera1(), camera2 = drawable.getCamera2();
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
        Circle player1 = drawable.getPlayer1(), player2 = drawable.getPlayer2();
        Sprited mask = drawable.getMask();
        Sprited divider = drawable.getDivider();

        float angle = MathUtils.atan2(player2.getY() - player1.getY(), player2.getX() - player1.getX());
        angle *= MathUtils.radiansToDegrees;
        angle += 90f;
        mask.setRotation(angle);
        divider.setRotation(angle);
    }

    @Override
    public void exit() {
        drawable = null;
    }
}