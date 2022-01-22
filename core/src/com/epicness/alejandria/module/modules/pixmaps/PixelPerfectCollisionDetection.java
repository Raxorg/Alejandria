package com.epicness.alejandria.module.modules.pixmaps;

import static com.epicness.alejandria.Constants.INITIAL_WINDOW_SIZE;
import static com.epicness.alejandria.ModuleID.PIXEL_PERFECT_COLLISION_DETECTION;
import static com.epicness.fundamentals.SharedConstants.WEIRD_SHAPE_PATH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.module.modules.Module;

public class PixelPerfectCollisionDetection extends Module {

    private SpriteBatch spriteBatch;
    private Pixmap pixmap;
    private Sprite[] shapes;

    public PixelPerfectCollisionDetection() {
        super(PIXEL_PERFECT_COLLISION_DETECTION);
    }

    @Override
    public void setup() {
        spriteBatch = new SpriteBatch();

        pixmap = new Pixmap(Gdx.files.internal(WEIRD_SHAPE_PATH));
        Texture texture = new Texture(pixmap);
        shapes = new Sprite[50];
        for (int i = 0; i < shapes.length; i++) {
            shapes[i] = new Sprite(texture);
            float size = MathUtils.random(50, 70);
            shapes[i].setSize(size, size);
            shapes[i].setPosition(MathUtils.random(0, INITIAL_WINDOW_SIZE - size), MathUtils.random(0, INITIAL_WINDOW_SIZE - size));
        }
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.justTouched()) {
            float clickX = Gdx.input.getX();
            float clickY = Gdx.graphics.getHeight() - Gdx.input.getY();
            for (int i = 0; i < shapes.length; i++) {
                Sprite shape = shapes[i];
                if (!shape.getBoundingRectangle().contains(clickX, clickY)) {
                    continue;
                }
                int pixmapX = (int) MathUtils.map(shape.getX(), shape.getX() + shape.getWidth(), 0, pixmap.getWidth(), clickX);
                int pixmapY = (int) MathUtils.map(shape.getY(), shape.getY() + shape.getHeight(), 0, pixmap.getHeight(), clickY);
                Color pixelColor = new Color();
                Color.rgba8888ToColor(pixelColor, pixmap.getPixel(pixmapX, pixmapY));
                if (pixelColor.a == 0) {
                    System.out.println("Transparent pixel clicked");
                }
                break;
            }
        }
    }

    @Override
    public void draw() {
        spriteBatch.begin();
        for (int i = 0; i < shapes.length; i++) {
            shapes[i].draw(spriteBatch);
        }
        spriteBatch.end();
    }
}