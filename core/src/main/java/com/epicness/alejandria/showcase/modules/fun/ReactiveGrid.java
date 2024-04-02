package com.epicness.alejandria.showcase.modules.fun;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.DARK_GRAY;
import static com.epicness.alejandria.showcase.constants.FunConstants.CELL_SIZE;
import static com.epicness.alejandria.showcase.constants.FunConstants.CIRCLE_RADIUS;
import static com.epicness.alejandria.showcase.constants.FunConstants.GRID_COLOR_FADE_TIME;
import static com.epicness.alejandria.showcase.constants.FunConstants.GRID_COLUMNS;
import static com.epicness.alejandria.showcase.constants.FunConstants.GRID_ROWS;
import static com.epicness.alejandria.showcase.constants.FunConstants.GRID_X;
import static com.epicness.alejandria.showcase.constants.FunConstants.GRID_Y;
import static com.epicness.alejandria.showcase.constants.FunConstants.MAX_CIRCLE_SPEED;
import static com.epicness.alejandria.showcase.constants.FunConstants.MIN_CIRCLE_SPEED;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.SnapshotArray;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.alejandria.showcase.stuff.modules.fun.ColorBall;
import com.epicness.alejandria.showcase.stuff.modules.fun.ColoredCell;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.stuff.grid.CellGrid;
import com.epicness.fundamentals.stuff.shapes.bidimensional.Circle;
import com.epicness.fundamentals.utils.Random;

public class ReactiveGrid extends Module<ReactiveGridDrawable> {

    private CellGrid<ColoredCell> grid;
    private SnapshotArray<Sprited> colorBombs;
    private SnapshotArray<ColorBall> colorBalls;
    private Circle circle;

    private float bombTimer;
    private float translationX, translationY;
    private ColoredCell auxCell;
    private Color color;
    private Vector2 auxVector2, auxVector1;
    private ColorBall auxColorBall;
    private Sprited auxColorBomb;

    public ReactiveGrid() {
        super("Reactive Grid", "Mesmerizing and colorful grid effects :3");
    }

    @Override
    protected ReactiveGridDrawable setup() {
        drawable = new ReactiveGridDrawable(assets.getRoundedSquare());

        grid = drawable.getGrid();
        for (int column = 0; column < grid.getColumns(); column++) {
            for (int row = 0; row < grid.getRows(); row++) {
                grid.cells[column][row].originalColor.set(DARK_GRAY);
            }
        }

        colorBombs = drawable.getColorBombs();
        colorBalls = drawable.getColorBalls();

        circle = drawable.getCircle();
        circle.setX(MathUtils.random(GRID_X + CIRCLE_RADIUS, GRID_X + grid.getWidth()));
        circle.setY(MathUtils.random(GRID_Y + CIRCLE_RADIUS, GRID_Y + grid.getHeight()));

        translationX = MathUtils.random(MIN_CIRCLE_SPEED, MAX_CIRCLE_SPEED) * MathUtils.randomSign();
        translationY = MathUtils.random(MIN_CIRCLE_SPEED, MAX_CIRCLE_SPEED) * MathUtils.randomSign();
        auxCell = null;
        color = new Color();
        auxVector2 = new Vector2();
        auxVector1 = new Vector2();
        return drawable;
    }

    @Override
    public void update(float delta) {
        spawnColorBombs(delta);
        moveCircle(delta);
        checkBombs();
        colorCircleCell();
        updateGrid(delta);
        moveBalls(delta);
        colorBallCells();
    }

    private void spawnColorBombs(float delta) {
        bombTimer += delta;
        if (bombTimer >= 2f) {
            Sprited circle = new Sprited(assets.getCircle());
            circle.setSize(CELL_SIZE);
            circle.setX(GRID_X + MathUtils.random(GRID_COLUMNS - 1) * CELL_SIZE);
            circle.setY(GRID_Y + MathUtils.random(GRID_ROWS - 1) * CELL_SIZE);
            circle.setColor(Random.opaqueColor());
            colorBombs.add(circle);
            bombTimer = 0f;
        }
    }

    private void moveCircle(float delta) {
        circle.translate(delta * translationX, delta * translationY);
        if (circle.getX() <= GRID_X || circle.getEndX() >= GRID_X + grid.getWidth()) {
            circle.setX(MathUtils.clamp(circle.getX(), GRID_X + CIRCLE_RADIUS, GRID_X + grid.getWidth() - CIRCLE_RADIUS));
            translationX = MathUtils.random(MIN_CIRCLE_SPEED, MAX_CIRCLE_SPEED) * -(translationX / Math.abs(translationX));
            circle.setColor(Random.opaqueColor());
        }
        if (circle.getY() <= GRID_Y || circle.getEndY() >= GRID_Y + grid.getHeight()) {
            circle.setY(MathUtils.clamp(circle.getY(), GRID_Y, GRID_Y + grid.getHeight() - circle.getHeight()));
            translationY = MathUtils.random(MIN_CIRCLE_SPEED, MAX_CIRCLE_SPEED) * -(translationY / Math.abs(translationY));
            circle.setColor(Random.opaqueColor());
        }
    }

    private void checkBombs() {
        colorBombs.begin();
        for (int i = 0; i < colorBombs.size; i++) {
            auxColorBomb = colorBombs.get(i);
            if (auxColorBomb.getCenter(auxVector2).dst(circle.getCenter(auxVector1)) <= CELL_SIZE / 2f + CIRCLE_RADIUS) {
                spawnExplosion(auxVector2, auxColorBomb.getColor());
                colorBombs.removeValue(auxColorBomb, true);
                return;
            }
        }
        colorBombs.end();
    }

    private void spawnExplosion(Vector2 bombCenter, Color color) {
        int angles = MathUtils.random(3, 16);
        float angleOffset = MathUtils.random(360f);
        for (int i = 0; i < angles; i++) {
            float angle = 360f / angles * i + angleOffset;
            Vector2 speed = new Vector2(MathUtils.cosDeg(angle) * 200f, MathUtils.sinDeg(angle) * 200f);
            ColorBall ball = new ColorBall(assets.getCircle(), speed);
            ball.setSize(CELL_SIZE);
            ball.setOriginCenter();
            ball.setOriginBasedPosition(bombCenter);
            ball.setColor(color);
            colorBalls.add(ball);
        }
    }

    private void colorCircleCell() {
        colorCell(circle.getCenterX(), circle.getCenterY(), circle.getBorderColor());
    }

    private void colorCell(float x, float y, Color color) {
        if (x <= GRID_X || x >= GRID_X + grid.getWidth() || y <= GRID_Y || y >= GRID_Y + grid.getHeight()) {
            return;
        }
        int column = (int) MathUtils.map(GRID_X, GRID_X + grid.getWidth(), 0, GRID_COLUMNS, x);
        int row = (int) MathUtils.map(GRID_Y, GRID_Y + grid.getHeight(), 0, GRID_ROWS, y);
        auxCell = grid.cells[column][row];
        auxCell.originalColor.set(color);
        auxCell.colorProgress = 1f;
    }

    private void updateGrid(float delta) {
        for (int column = 0; column < grid.getColumns(); column++) {
            for (int row = 0; row < grid.getRows(); row++) {
                auxCell = grid.cells[column][row];
                auxCell.colorProgress = Math.max(auxCell.colorProgress - delta / GRID_COLOR_FADE_TIME, 0f);
                color.set(BLACK);
                auxCell.setColor(color.lerp(auxCell.originalColor, auxCell.colorProgress));
            }
        }
    }

    private void moveBalls(float delta) {
        colorBalls.begin();
        for (int i = 0; i < colorBalls.size; i++) {
            auxColorBall = colorBalls.get(i);
            auxColorBall.translate(auxColorBall.speed.x * delta, auxColorBall.speed.y * delta);
            if (auxColorBall.getEndX() <= 0f || auxColorBall.getX() >= CAMERA_WIDTH
                || auxColorBall.getEndY() <= 0f || auxColorBall.getY() >= CAMERA_HEIGHT) {
                colorBalls.removeValue(auxColorBall, true);
            }
            for (int j = 0; j < colorBombs.size; j++) {
                auxColorBomb = colorBombs.get(j);
                if (auxColorBall.getCenter(auxVector1).dst(auxColorBomb.getCenter(auxVector2)) <= CELL_SIZE) {
                    spawnExplosion(auxVector2, auxColorBomb.getColor());
                    colorBombs.removeValue(auxColorBomb, true);
                    break;
                }
            }
        }
        colorBalls.end();
    }

    private void colorBallCells() {
        colorBalls.begin();
        for (int i = 0; i < colorBalls.size; i++) {
            auxColorBall = colorBalls.get(i);
            colorCell(auxColorBall.getCenterX(), auxColorBall.getCenterY(), auxColorBall.getColor());
        }
    }
}