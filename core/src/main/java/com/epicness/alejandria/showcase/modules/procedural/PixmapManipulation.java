package com.epicness.alejandria.showcase.modules.procedural;

import static com.badlogic.gdx.Input.Keys.C;
import static com.badlogic.gdx.Input.Keys.NUM_1;
import static com.badlogic.gdx.Input.Keys.NUM_9;
import static com.badlogic.gdx.graphics.Color.CLEAR;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_SIZE;
import static com.epicness.fundamentals.constants.SharedConstants.DARK_DIRT;
import static com.epicness.fundamentals.constants.SharedConstants.DIRT;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.PixmapTextureData;
import com.badlogic.gdx.math.MathUtils;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.utils.Random;

public class PixmapManipulation extends Module<PixmapManipulationDrawable> {

    private Texture texture;
    private Pixmap pixmap;
    private PixmapTextureData pixmapTextureData;
    private int radius, rings;
    private Color innerColor, outerColor, lerpColor, pixmapColor;
    @SuppressWarnings("FieldCanBeLocal") // Avoids creating floats each frame
    private float time, progress;

    public PixmapManipulation() {
        super(
            "Pixmap Manipulation",
            "Demonstrates realtime manipulation of a texture using pixmap data\n\n" +
                "avoids allocating memory each frame\n\n" +
                "Press 1 through 9 to see different \"ring\" counts\n\n" +
                "Press C to randomize colors"
        );
    }

    @Override
    protected PixmapManipulationDrawable setup() {
        drawable = new PixmapManipulationDrawable();
        texture = drawable.getTexture();
        pixmap = drawable.getPixmap();
        pixmapTextureData = drawable.getPixmapTextureData();
        radius = (int) SHOWCASE_SIZE / 2;
        rings = 5;
        innerColor = new Color(DIRT);
        outerColor = new Color(DARK_DIRT);
        lerpColor = new Color();
        pixmapColor = new Color();
        return drawable;
    }

    @Override
    public void update(float delta) {
        if (radius <= 0) return;

        time += delta;
        if (time >= 0.001f) {
            drawCircle();
            time = 0f;
        }
    }

    private void drawCircle() {
        for (int i = 0; i < 3 && radius > 0; i++) {
            radius--;
            progress = MathUtils.lerp(0f, rings, (radius / (SHOWCASE_SIZE / 2f)) % (1f / rings));
            pixmapColor.set(lerpColor.lerp(outerColor, progress));
            lerpColor.set(innerColor);
            pixmap.setColor(pixmapColor);
            pixmap.fillCircle((int) (SHOWCASE_SIZE / 2), (int) (SHOWCASE_SIZE / 2), radius);
        }
        texture.load(pixmapTextureData); // Instead of "new Texture(pixmap);"
        // Alternative option:
        // texture.draw(pixmap, 0, 0);
    }

    @Override
    public void keyDown(int keycode) {
        if (keycode >= NUM_1 && keycode <= NUM_9) {
            rings = keycode - 7;
            reset();
        }
        if (keycode == C) {
            innerColor = Random.opaqueColor();
            outerColor = Random.opaqueColor();
            reset();
        }
    }

    private void reset() {
        pixmap.setColor(CLEAR);
        pixmap.fill();
        radius = (int) (SHOWCASE_SIZE / 2);
    }
}