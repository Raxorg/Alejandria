package com.epicness.fundamentals.stuff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.epicness.fundamentals.utils.Random;

public class AnimatedBackgroundDeluxe extends AnimatedBackground {

    private Color[] colors;
    private float[] rotations;
    private boolean[] rotationModifiers;
    private Color a, b;
    private float time;
    private boolean colorTransitionEnabled, followBackgroundColor;
    private Color spriteColor;

    public AnimatedBackgroundDeluxe(float x, float y, float w, float h, Color color, Sprite repeatedImage, Sprite backgroundImage,
                                    OrthographicCamera camera, int imageColumns, int imageRows, float speed) {
        super(x, y, w, h, color, repeatedImage, backgroundImage, camera, imageColumns, imageRows, speed);
        a = color;
        b = Random.rainbowExcludedColor(a);
        time = 0;
        colorTransitionEnabled = true;
        followBackgroundColor = true;
        spriteColor = Color.WHITE;
    }

    @Override
    protected void initialize() {
        super.initialize();
        colors = new Color[positions.length];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = new Color(color.r, color.g, color.b, color.a * MathUtils.random(0.35f, 0.65f));
        }
        rotations = new float[positions.length];
        for (int i = 0; i < rotations.length; i++) {
            rotations[i] = MathUtils.random(360f);
        }
        rotationModifiers = new boolean[positions.length];
        for (int i = 0; i < rotationModifiers.length; i++) {
            rotationModifiers[i] = MathUtils.randomBoolean();
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        for (int i = 0; i < rotations.length; i++) {
            rotations[i] = rotations[i] + 1f * (rotationModifiers[i] ? -1 : 1);
        }
        if (colorTransitionEnabled) {
            time += delta / 5f;
            if (time >= 1) {
                time = 0;
                a = color;
                b = Random.rainbowExcludedColor(color);
            }
            color = a.cpy().lerp(b, time);
        }
        for (int i = 0; i < colors.length; i++) {
            colors[i] = new Color(color.r, color.g, color.b, colors[i].a);
        }
    }

    @Override
    protected void renderImages(SpriteBatch spriteBatch, OrthographicCamera camera) {
        float x = bounds.x;
        float y = bounds.y;
        float w = bounds.width;
        float h = bounds.height;
        Rectangle scissors = new Rectangle();
        Rectangle clipBounds = new Rectangle(x, y, w, h);
        ScissorStack.calculateScissors(camera, spriteBatch.getTransformMatrix(), clipBounds, scissors);
        boolean pop = ScissorStack.pushScissors(scissors);
        for (int i = 0; i < positions.length; i++) {
            if (followBackgroundColor) {
                spriteBatch.setColor(colors[i]);
            } else {
                spriteBatch.setColor(spriteColor);
            }
            spriteBatch.draw(
                repeatedImage,
                positions[i].x, positions[i].y, imageSize / 2f, imageSize / 2f,
                imageSize, imageSize, 1f, 1f,
                rotations[i]);
        }

        spriteBatch.flush();
        if (pop) {
            ScissorStack.popScissors();
        }
    }

    public void setColorTransitionEnabled(boolean enabled) {
        colorTransitionEnabled = enabled;
    }

    public void setFollowBackgroundColor(boolean followBackgroundColor) {
        this.followBackgroundColor = followBackgroundColor;
    }

    public void setSpriteColor(Color spriteColor) {
        this.spriteColor = spriteColor;
    }
}