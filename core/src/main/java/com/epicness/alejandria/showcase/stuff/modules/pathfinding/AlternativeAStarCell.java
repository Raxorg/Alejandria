package com.epicness.alejandria.showcase.stuff.modules.pathfinding;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.stuff.SpritedText;
import com.epicness.fundamentals.stuff.Text;

public class AlternativeAStarCell {

    public final int col, row;
    private final SpritedText spritedText;
    private final Text gCostText, hCostText;
    public int gCost, hCost, fCost;
    public AlternativeAStarCell previousCell;
    public boolean enabled;

    public AlternativeAStarCell(int col, int row, Sprite pixel, BitmapFont font, BitmapFont smallerFont) {
        this.col = col;
        this.row = row;
        spritedText = new SpritedText(pixel, font);
        gCostText = new Text(smallerFont);
        gCostText.setText("G");
        hCostText = new Text(smallerFont);
        hCostText.setText("H");
        enabled = true;
    }

    public void draw(SpriteBatch spriteBatch) {
        spritedText.draw(spriteBatch);
        gCostText.draw(spriteBatch);
        hCostText.draw(spriteBatch);
    }

    public boolean contains(float x, float y) {
        return spritedText.contains(x, y);
    }

    public float getX() {
        return spritedText.getX();
    }

    public float getY() {
        return spritedText.getY();
    }

    public void setPosition(float x, float y) {
        spritedText.setPosition(x, y);
        updateCostPositions();
    }

    public void translateY(float amount) {
        spritedText.translateY(amount);
        gCostText.translateY(amount);
        hCostText.translateY(amount);
    }

    public void setSize(float size) {
        spritedText.setSize(size);
        updateCostPositions();
    }

    private void updateCostPositions() {
        gCostText.setPosition(getX() + spritedText.getWidth() * 0.05f, getY() + spritedText.getHeight() * 0.95f);
        hCostText.setX(getX() + spritedText.getWidth() * 0.95f - hCostText.getPlainWidth());
        hCostText.setY(getY() + hCostText.getHeight() + spritedText.getHeight() * 0.05f);
    }

    public void setCosts(int g, int h, int f) {
        gCostText.setText(String.valueOf(g));
        hCostText.setText(String.valueOf(h));
        spritedText.setText(String.valueOf(f));
        updateCostPositions();
    }

    public void setColor(Color color) {
        spritedText.setBackgroundColor(color);
    }
}