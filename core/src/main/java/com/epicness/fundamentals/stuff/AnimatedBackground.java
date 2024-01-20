package com.epicness.fundamentals.stuff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;

public class AnimatedBackground {

    protected Rectangle bounds;
    // Received data
    protected TextureRegion repeatedImage, backgroundImage;
    protected Color color;
    private final OrthographicCamera camera;
    private final int imageRows, imageColumns;
    private final float speed;
    // Calculated data
    protected float imageSize;
    protected Vector2[] positions;

    public AnimatedBackground(float x, float y, float w, float h, Color color, Sprite repeatedImage, Sprite backgroundImage,
                              OrthographicCamera camera, int imageColumns, int imageRows, float speed) {
        bounds = new Rectangle(x, y, w, h);
        this.color = color;
        this.repeatedImage = repeatedImage;
        this.backgroundImage = backgroundImage;
        this.camera = camera;
        this.imageColumns = imageColumns;
        this.imageRows = imageRows + (imageRows % 2 == 0 ? 0 : 1);
        this.speed = speed;
        initialize();
    }

    protected void initialize() {
        // Calculate Image Size
        float smallerDimension = Math.min(bounds.width, bounds.height);
        imageSize = smallerDimension / imageRows;
        // Calculate Spacing
        float xSpacing = imageSize + ((bounds.width + imageSize) - imageSize * (imageColumns - 1)) / imageColumns;
        float ySpacing = imageSize + ((bounds.height + imageSize) - imageSize * imageRows) / (imageRows * 0.7f);
        // Calculate Initial Positions
        positions = new Vector2[imageRows * imageColumns];
        int index = 0;
        for (int r = 0; r < imageRows; r++) {
            for (int c = 0; c < imageColumns; c++, index++) {
                float xPos;
                if (r % 2 != 0) {
                    xPos = -imageSize + xSpacing / 2 + xSpacing * c + bounds.x;
                } else {
                    xPos = -imageSize + xSpacing * c + bounds.x;
                }
                // Place the Calculated Position in the Array
                positions[index] = new Vector2(xPos, -imageSize + ySpacing * r + bounds.y);
            }
        }
    }

    public void update(float delta) {
        for (Vector2 position : positions) {
            position.x += speed * delta;
            position.y += speed * delta;
            // Verify Right Limit
            if (position.x >= bounds.x + bounds.width + imageSize / 2) {
                position.x = bounds.x - imageSize * 1.5f;
            }
            // Verify Top Limit
            if (position.y >= bounds.y + bounds.height + imageSize / 4) {
                position.y = bounds.y - imageSize * 1.25f;
            }
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        // Render the Background Image
        spriteBatch.setColor(color);
        spriteBatch.draw(
            backgroundImage,
            bounds.x,
            bounds.y,
            bounds.width,
            bounds.height
        );
        // Render the Moving Images
        renderImages(spriteBatch, camera);
    }

    protected void renderImages(SpriteBatch spriteBatch, OrthographicCamera camera) {
        float x = bounds.x;
        float y = bounds.y;
        float w = bounds.width;
        float h = bounds.height;
        Rectangle scissors = new Rectangle();
        Rectangle clipBounds = new Rectangle(x, y, w, h);
        ScissorStack.calculateScissors(camera, spriteBatch.getTransformMatrix(), clipBounds, scissors);
        boolean pop = ScissorStack.pushScissors(scissors);
        spriteBatch.setColor(color.r / 2f, color.g / 2f, color.b / 2f, color.a);
        for (Vector2 position : positions) {
            spriteBatch.draw(
                repeatedImage,
                position.x, position.y, imageSize / 2f, imageSize / 2f,
                imageSize, imageSize, 1f, 1f,
                45f);
        }

        spriteBatch.flush();
        spriteBatch.setColor(1f, 1f, 1f, 1f);
        if (pop) {
            ScissorStack.popScissors();
        }
    }

    // Getter
    public Color getColor() {
        return color;
    }
}