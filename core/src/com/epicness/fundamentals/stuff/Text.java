package com.epicness.fundamentals.stuff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import com.epicness.fundamentals.utils.TextUtils;

public class Text implements Buttonable, Scrollable {

    protected Rectangle bounds;
    private BitmapFont font;
    protected String text;
    protected Color color;
    // Advanced
    private int horizontalAlignment;
    private boolean centerVertical;
    private String truncate;

    public Text(BitmapFont font) {
        bounds = new Rectangle();
        this.font = font;
        text = "";
        color = Color.WHITE;
        // Advanced
        horizontalAlignment = Align.left;
        centerVertical = false;
    }

    public void draw(SpriteBatch spriteBatch) {
        font.setColor(color);
        getFont().draw(
                spriteBatch,
                text,
                bounds.x,
                centerVertical ? bounds.y + bounds.height / 2f : bounds.y,
                0,
                text.length(),
                bounds.width,
                horizontalAlignment,
                true,
                truncate
        );
    }

    @Override
    public boolean contains(float x, float y) {
        return bounds.contains(x, y);
    }

    protected void calculateSize() {
        bounds.height = TextUtils.getTextHeight(getFont(), text, bounds.width, horizontalAlignment, true, truncate);
    }

    // Getters & Setters
    public void setX(float x) {
        bounds.x = x;
    }

    @Override
    public float getY() {
        return bounds.y;
    }

    @Override
    public void setY(float y) {
        bounds.y = y;
    }

    public void setPosition(float x, float y) {
        setX(x);
        setY(y);
    }

    @Override
    public void translateY(float y) {
        bounds.y += y;
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
        calculateSize();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        calculateSize();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    // Advanced
    public void setTextTargetWidth(float textWidth) {
        bounds.width = textWidth;
    }

    public void setHorizontalAlignment(int horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    public void setCenterVertical(boolean centerVertical) {
        this.centerVertical = centerVertical;
    }

    public void setTruncate(String truncate) {
        this.truncate = truncate;
    }
}