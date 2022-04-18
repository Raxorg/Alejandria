package com.epicness.alejandria.showcase.stuff.modules.collisions;

import static com.epicness.alejandria.Constants.INITIAL_WINDOW_SIZE;
import static com.epicness.fundamentals.SharedConstants.WEIRD_SHAPE_PATH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.stuff.Drawable;
import com.epicness.fundamentals.stuff.Sprited;

public class PixelPerfectCollisionDetectionDrawable implements Drawable {

    private final Pixmap pixmap;
    private final Sprited[] shapes;

    public PixelPerfectCollisionDetectionDrawable() {
        pixmap = new Pixmap(Gdx.files.internal(WEIRD_SHAPE_PATH));
        Sprite sprite = new Sprite(new Texture(pixmap));
        shapes = new Sprited[50];
        for (int i = 0; i < shapes.length; i++) {
            shapes[i] = new Sprited(sprite);
            float size = MathUtils.random(50, 70);
            shapes[i].setSize(size, size);
            shapes[i].setPosition(MathUtils.random(0, INITIAL_WINDOW_SIZE - size), MathUtils.random(0, INITIAL_WINDOW_SIZE - size));
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        spriteBatch.begin();
        for (int i = 0; i < shapes.length; i++) {
            shapes[i].draw(spriteBatch);
        }
        spriteBatch.end();
    }

    public Pixmap getPixmap() {
        return pixmap;
    }

    public Sprited[] getShapes() {
        return shapes;
    }
}