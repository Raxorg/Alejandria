package com.epicness.alejandria.showcase.stuff.modules.fun;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.stuff.SpritePlus;
import com.epicness.fundamentals.stuff.interfaces.Transformable;

public class Laser implements Transformable {

    private final SpritePlus start, mid, end, startGlow, midGlow, endGlow;
    private final Color auxColor;

    public Laser(Sprite beamStart, Sprite beamMid, Sprite beamEnd, Sprite beamStartGlow, Sprite beamMidGlow, Sprite beamEndGlow,
                 float width, float length, Color color) {
        start = new SpritePlus(beamStart);
        mid = new SpritePlus(beamMid);
        end = new SpritePlus(beamEnd);
        startGlow = new SpritePlus(beamStartGlow);
        midGlow = new SpritePlus(beamMidGlow);
        endGlow = new SpritePlus(beamEndGlow);
        auxColor = new Color(1f, 1f, 1f, 1f);
        setup(width, length);
        setColor(color);
    }

    public Laser(Sprite start, Sprite mid, Sprite end, Sprite startGlow, Sprite midGlow, Sprite endGlow) {
        this(start, mid, end, startGlow, midGlow, endGlow, 100f, 300f, new Color(1f, 0.5f, 0f, 1f));
    }

    public void setup(float width, float length) {
        start.setSize(width);
        start.setOriginCenter();

        mid.setSize(width, Math.max(length - width * 2f, 0f));
        mid.setOrigin(start.getOriginX(), start.getOriginY() - width);
        mid.setY(start.getY() + width);

        end.setSize(width);
        end.setOrigin(start.getOriginX(), start.getOriginY() - length + width);
        end.setY(start.getY() + length - width);

        startGlow.setSize(width);
        startGlow.setOriginCenter();

        midGlow.setSize(width, Math.max(length - width * 2f, 0f));
        midGlow.setOrigin(start.getOriginX(), start.getOriginY() - width);
        midGlow.setY(start.getY() + width);

        endGlow.setSize(width);
        endGlow.setOrigin(start.getOriginX(), start.getOriginY() - length + width);
        endGlow.setY(start.getY() + length - width);
    }

    public void draw(SpriteBatch spriteBatch) {
        start.draw(spriteBatch);
        mid.draw(spriteBatch);
        end.draw(spriteBatch);
        startGlow.draw(spriteBatch);
        midGlow.draw(spriteBatch);
        endGlow.draw(spriteBatch);
    }

    public void drawDebug(ShapeDrawerPlus shapeDrawer) {
        start.drawDebug(shapeDrawer);
        mid.drawDebug(shapeDrawer);
        end.drawDebug(shapeDrawer);
    }

    @Override
    public float getX() {
        return start.getCenterX();
    }

    @Override
    public void translateX(float amount) {
        start.translateX(amount);
        mid.translateX(amount);
        end.translateX(amount);
        startGlow.translateX(amount);
        midGlow.translateX(amount);
        endGlow.translateX(amount);
    }

    @Override
    public float getY() {
        return start.getCenterY();
    }

    @Override
    public void translateY(float amount) {
        start.translateY(amount);
        mid.translateY(amount);
        end.translateY(amount);
        startGlow.translateY(amount);
        midGlow.translateY(amount);
        endGlow.translateY(amount);
    }

    @Override
    public float getRotation() {
        return start.getRotation();
    }

    @Override
    public void rotate(float degrees) {
        start.rotate(degrees);
        mid.rotate(degrees);
        end.rotate(degrees);
        startGlow.rotate(degrees);
        midGlow.rotate(degrees);
        endGlow.rotate(degrees);
    }

    @Override
    public void stretchWidth(float amount) {
        setup(getWidth() + amount, getHeight());
    }

    @Override
    public void stretchHeight(float amount) {
        setup(getWidth(), getHeight() + amount);
    }

    @Override
    public float getWidth() {
        return start.getWidth();
    }

    @Override
    public float getHeight() {
        return start.getHeight() + mid.getHeight() + end.getHeight();
    }

    public void setColor(Color color) {
        start.setColor(color);
        mid.setColor(color);
        end.setColor(color);
        auxColor.lerp(color, 0.1f);
        startGlow.setColor(auxColor);
        midGlow.setColor(auxColor);
        endGlow.setColor(auxColor);
        auxColor.set(1f, 1f, 1f, 1f);
    }
}