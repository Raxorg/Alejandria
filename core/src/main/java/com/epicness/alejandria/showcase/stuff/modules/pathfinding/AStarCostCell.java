package com.epicness.alejandria.showcase.stuff.modules.pathfinding;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.stuff.SpritedText;
import com.epicness.fundamentals.stuff.Text;
import com.epicness.fundamentals.stuff.interfaces.Buttonable;
import com.epicness.fundamentals.stuff.interfaces.Movable;
import com.epicness.fundamentals.stuff.interfaces.SpriteDrawable;

public class AStarCostCell implements SpriteDrawable, Buttonable, Movable {

    public final int col, row;
    private final SpritedText spritedText;
    private final Text gCostText, hCostText;
    public int gCost, hCost, fCost;
    public AStarCostCell previousCell;
    public boolean enabled;

    public AStarCostCell(int col, int row, Sprite pixel, BitmapFont font, BitmapFont smallerFont) {
        this.col = col;
        this.row = row;
        spritedText = new SpritedText(pixel, font);
        spritedText.setText("F");
        gCostText = new Text(smallerFont);
        gCostText.setText("G");
        hCostText = new Text(smallerFont);
        hCostText.setText("H");
        enabled = true;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spritedText.draw(spriteBatch);
        gCostText.draw(spriteBatch);
        hCostText.draw(spriteBatch);
    }

    @Override
    public boolean contains(float x, float y) {
        return spritedText.contains(x, y);
    }

    @Override
    public float getX() {
        return spritedText.getX();
    }

    @Override
    public void translateX(float amount) {
        spritedText.translateX(amount);
        gCostText.translateX(amount);
        hCostText.translateX(amount);
    }

    @Override
    public float getY() {
        return spritedText.getY();
    }

    @Override
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