package com.epicness.alejandria.showcase.modules.viewports;

import static com.badlogic.gdx.Input.Keys.A;
import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.Input.Keys.H;
import static com.badlogic.gdx.Input.Keys.J;
import static com.badlogic.gdx.Input.Keys.K;
import static com.badlogic.gdx.Input.Keys.S;
import static com.badlogic.gdx.Input.Keys.U;
import static com.badlogic.gdx.Input.Keys.W;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.epicness.alejandria.showcase.constants.AdvancedSplitScreenConstants.GRID_SIZE;
import static com.epicness.alejandria.showcase.constants.AdvancedSplitScreenConstants.MAX_DISTANCE;
import static com.epicness.alejandria.showcase.constants.AdvancedSplitScreenConstants.MAX_VIEWPORT_SIZE;
import static com.epicness.alejandria.showcase.constants.AdvancedSplitScreenConstants.MIN_VIEWPORT_SIZE;
import static com.epicness.alejandria.showcase.constants.AdvancedSplitScreenConstants.PLAYER_SPEED;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Circle;

public class AdvancedSplitScreen extends Module<AdvancedSplitScreenDrawable> {

    private Circle player1, player2;
    private Camera camera1, camera2;

    public AdvancedSplitScreen() {
        super(
            "Advanced Split Screen",
            "This split screen on steroids uses masking techniques\n\n" +
                "WASD and UHJK to move the dots"
        );
    }

    @Override
    public AdvancedSplitScreenDrawable setup() {
        drawable = new AdvancedSplitScreenDrawable(sharedAssets.getSquare32(), sharedAssets.getPixel(), screen.getStaticCamera());
        player1 = drawable.getPlayer1();
        player2 = drawable.getPlayer2();
        camera1 = drawable.getCamera1();
        camera2 = drawable.getCamera2();
        renderer.getViewport().setMaxWorldWidth(VIEWPORT_WIDTH);
        renderer.getViewport().setMaxWorldHeight(VIEWPORT_HEIGHT);
        renderer.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return drawable;
    }

    @Override
    public void update(float delta) {
        handleInput(delta);
        limitPlayerPositions();
        updateCameraPositions();
        updateCameraZoom();
        limitCameraPositions();
        updateMask();
        updateCloseState();
    }

    private void handleInput(float delta) {
        float translation = PLAYER_SPEED * delta;
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
        if (Gdx.input.isKeyPressed(U)) {
            player2.translateY(translation);
        }
        if (Gdx.input.isKeyPressed(H)) {
            player2.translateX(-translation);
        }
        if (Gdx.input.isKeyPressed(J)) {
            player2.translateY(-translation);
        }
        if (Gdx.input.isKeyPressed(K)) {
            player2.translateX(translation);
        }
    }

    private void limitPlayerPositions() {
        player1.setX(MathUtils.clamp(player1.getX(), 0f, GRID_SIZE - player1.getWidth()));
        player1.setY(MathUtils.clamp(player1.getY(), 0f, GRID_SIZE - player1.getHeight()));
        player2.setX(MathUtils.clamp(player2.getX(), 0f, GRID_SIZE - player2.getWidth()));
        player2.setY(MathUtils.clamp(player2.getY(), 0f, GRID_SIZE - player2.getHeight()));
    }

    private void updateCameraPositions() {
        if (drawable.arePlayersClose()) {
            float averageX = (player1.getCenterX() + player2.getCenterX()) / 2f;
            float averageY = (player1.getCenterY() + player2.getCenterY()) / 2f;
            camera1.position.x = averageX;
            camera1.position.y = averageY;
            camera2.position.x = averageX;
            camera2.position.y = averageY;
        } else {
            float angle = MathUtils.atan2(player2.getCenterY() - player1.getCenterY(), player2.getCenterX() - player1.getCenterX());
            angle *= MathUtils.radiansToDegrees;
            float distanceX = MathUtils.cosDeg(angle) * MAX_DISTANCE;
            float distanceY = MathUtils.sinDeg(angle) * MAX_DISTANCE;
            camera1.position.x = player1.getCenterX() + distanceX / 2f;
            camera1.position.y = player1.getCenterY() + distanceY / 2f;
            camera2.position.x = player2.getCenterX() - distanceX / 2f;
            camera2.position.y = player2.getCenterY() - distanceY / 2f;
        }
    }

    private void updateCameraZoom() {
        if (!drawable.arePlayersClose()) {
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
        Sprited mask = drawable.getMask();
        Sprited divider = drawable.getDivider();

        float angle = MathUtils.atan2(player2.getCenterY() - player1.getCenterY(), player2.getCenterX() - player1.getCenterX());
        angle *= MathUtils.radiansToDegrees;
        angle += 90f;
        mask.setRotation(angle);
        divider.setRotation(angle);
    }

    private void updateCloseState() {
        drawable.setPlayersClose(player1.getPosition().dst(player2.getPosition()) < MAX_DISTANCE);
        drawable.setCamerasClose(camera1.position.dst(camera2.position) == 0f);
    }

    @Override
    protected void exit() {
        renderer.getViewport().setMaxWorldWidth(0);
        renderer.getViewport().setMaxWorldHeight(0);
        renderer.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        renderer.getShapeRenderer().setColor(WHITE);
        renderer.useStaticCamera();
    }
}