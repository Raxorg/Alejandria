package com.epicness.fundamentals.stuff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class GradientFont extends BitmapFont {

    private String string;
    private float x, y;
    private final Color topColor, bottomColor;
    @SuppressWarnings({"FieldCanBeLocal"}) // Optimization
    private float topColorBits, bottomColorBits;

    public GradientFont(String string, float x, float y, Color topColor, Color bottomColor) {
        this.string = string;
        this.x = x;
        this.y = y;
        this.topColor = new Color(topColor);
        topColorBits = topColor.toFloatBits();
        this.bottomColor = new Color(bottomColor);
        bottomColorBits = bottomColor.toFloatBits();
    }

    public GradientFont(Color topColor, Color bottomColor) {
        this("", 0f, 0f, topColor, bottomColor);
    }

    static void applyGradient(float[] vertices, int vertexCount, float color1, float color2, float color3, float color4) {
        for (int index = 0; index < vertexCount; index += 20) {
            vertices[index + SpriteBatch.C1] = color1; // C1: 2
            vertices[index + SpriteBatch.C2] = color2; // C2: 7
            vertices[index + SpriteBatch.C3] = color3; // C3: 12
            vertices[index + SpriteBatch.C4] = color4; // C4: 17
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    public GlyphLayout drawGradient(Batch batch) {
        BitmapFontCache cache = getCache();
        cache.clear();
        GlyphLayout layout = cache.addText(string, x, y);
        for (int page = 0; page < cache.getFont().getRegions().size; page++) {
            applyGradient(
                cache.getVertices(page),
                cache.getVertexCount(page),
                bottomColorBits, topColorBits,
                topColorBits, bottomColorBits
            );
        }
        cache.draw(batch);
        return layout;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setTopColor(Color color) {
        topColor.set(color);
        topColorBits = topColor.toFloatBits();
    }

    public void setBottomColor(Color color) {
        bottomColor.set(color);
        bottomColorBits = bottomColor.toFloatBits();
    }
}