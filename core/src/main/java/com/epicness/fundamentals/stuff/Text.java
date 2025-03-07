package com.epicness.fundamentals.stuff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import com.epicness.fundamentals.stuff.interfaces.Buttonable;
import com.epicness.fundamentals.stuff.interfaces.Movable;
import com.epicness.fundamentals.utils.TextUtils;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class Text implements Buttonable, Movable {

    private final BitmapFont font;
    private String text;
    private boolean verticallyCentered, wrap;
    private float yOffset;
    private int hAlign;
    private String truncate;
    private final Rectangle bounds;
    private final Color color;

    public Text(BitmapFont font, String text) {
        this.font = font;
        this.text = text;
        hAlign = Align.left;
        bounds = new Rectangle();
        bounds.width = 500f;
        color = new Color(1f, 1f, 1f, 1f);
        updateBounds();
    }

    public Text(BitmapFont font) {
        this(font, "");
    }

    public void draw(SpriteBatch spriteBatch) {
        font.setColor(color);
        font.draw(
            spriteBatch,
            text,
            bounds.x,
            bounds.y + yOffset,
            0,
            text.length(),
            bounds.width,
            hAlign,
            wrap,
            truncate
        );
    }

    public void drawDebug(ShapeDrawer shapeDrawer) {
        shapeDrawer.rectangle(
            bounds.x,
            bounds.y + yOffset,
            bounds.width,
            -bounds.height
        );
    }

    @Override
    public boolean contains(float x, float y) {
        return bounds.contains(x, y);
    }

    @Override
    public float getX() {
        return bounds.x;
    }

    @Override
    public void translateX(float amount) {
        bounds.x += amount;
    }

    @Override
    public float getY() {
        return bounds.y;
    }

    @Override
    public void translateY(float amount) {
        bounds.y += amount;
    }

    public BitmapFont getFont() {
        return font;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        updateBounds();
    }

    public int getHAlign() {
        return hAlign;
    }

    public void hAlignLeft() {
        hAlign = Align.left;
    }

    public void hAlignCenter() {
        hAlign = Align.center;
    }

    public void hAlignRight() {
        hAlign = Align.right;
    }

    public void setVerticallyCentered(boolean centered) {
        verticallyCentered = centered;
        yOffset = centered ? bounds.height * 0.5f : 0f;
    }

    public void setWrap(boolean wrap) {
        this.wrap = wrap;
    }

    public String getTruncate() {
        return truncate;
    }

    public void setTruncate(String truncate) {
        this.truncate = truncate;
        updateBounds();
    }

    public float getScale() {
        return font.getScaleX();
    }

    public void setScale(float scale) {
        font.getData().setScale(scale);
        updateBounds();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public float getWidth() {
        return bounds.width;
    }

    public void setWidth(float width) {
        bounds.width = width;
    }

    public float getPlainWidth() {
        return TextUtils.getTextWidth(this);
    }

    public float getHeight() {
        return TextUtils.getTextHeight(this);
    }

    private void updateBounds() {
        bounds.height = TextUtils.getTextHeight(this);
        yOffset = verticallyCentered ? bounds.height * 0.5f : 0f;
    }
}