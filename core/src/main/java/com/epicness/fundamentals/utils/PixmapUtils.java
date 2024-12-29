package com.epicness.fundamentals.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Blending;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * This class is used for pixmap utilities.
 *
 * @author Albert Beaupre
 */
public class PixmapUtils {

    public static Pixmap changeAlpha(Pixmap pixmap, float alpha) {
        Pixmap newPixmap = new Pixmap(pixmap.getWidth(), pixmap.getHeight(), Format.RGBA8888);

        newPixmap.setBlending(Blending.SourceOver);
        for (int x = 0; x < pixmap.getWidth(); x++) {
            for (int y = 0; y < pixmap.getHeight(); y++) {
                int pixelValue = pixmap.getPixel(x, y);
                Color color = new Color(pixelValue);
                color.a = alpha;
                newPixmap.setColor(color);
                if ((pixelValue >> 24) != 0x00) {
                    newPixmap.drawPixel(x, y);
                }
            }
        }
        return newPixmap;
    }

    public static Pixmap createOverlay(Pixmap pixmap, Color color) {
        Pixmap newPixmap = new Pixmap(pixmap.getWidth(), pixmap.getHeight(), Format.RGBA8888);

        newPixmap.setBlending(Blending.SourceOver);
        for (int x = 0; x < pixmap.getWidth(); x++) {
            for (int y = 0; y < pixmap.getHeight(); y++) {
                int pixelValue = pixmap.getPixel(x, y);
                if ((pixelValue >> 24) != 0x00) {
                    newPixmap.drawPixel(x, y, Color.rgba8888(color));
                }
            }
        }
        return newPixmap;
    }

    public static Pixmap outline(Pixmap pixmap, Color outlineColor) {
        Pixmap newPixmap = new Pixmap(pixmap.getWidth(), pixmap.getHeight(), Format.RGBA8888);

        newPixmap.setBlending(Blending.SourceOver);
        for (int x = 0; x <= pixmap.getWidth(); x++) {
            for (int y = 0; y <= pixmap.getHeight(); y++) {
                int pixelValue = pixmap.getPixel(x, y);
                int beforePixelValue = pixmap.getPixel(x - 1, y);
                int afterPixelValue = pixmap.getPixel(x + 1, y);
                if (((beforePixelValue >> 24) == 0x00 && (pixelValue >> 24) != 0x00) || (((afterPixelValue >> 24) == 0x00 && (pixelValue >> 24) != 0x00))) {
                    newPixmap.setColor(outlineColor);
                    newPixmap.drawPixel(x, y);
                }
            }
        }
        return newPixmap;
    }

    public static Pixmap createShadow(Pixmap pixmap, int copyHeight) {
        Pixmap newPixmap = new Pixmap(pixmap.getWidth(), pixmap.getHeight(), Format.RGBA8888);

        newPixmap.setBlending(Blending.SourceOver);
        for (int x = 0; x < pixmap.getWidth(); x++) {
            for (int y = 0; y < copyHeight; y++) {
                Color color = Color.BLACK;
                int pixelValue = pixmap.getPixel(x, pixmap.getHeight() - y);
                if ((pixelValue >> 24) != 0x00) {
                    color.a = 0.5f;
                    newPixmap.setColor(color);
                    newPixmap.drawPixel(x, pixmap.getHeight() - y);
                }
            }
        }
        return newPixmap;
    }

    public static Pixmap createLight(Color color, int radius) {
        Pixmap newPixmap = new Pixmap(radius * 2, radius * 2, Format.RGBA8888);
        Vector2 tile = new Vector2(0, 0);
        Vector2 center = new Vector2(radius, radius);

        newPixmap.setBlending(Blending.SourceOver);
        for (int x = 0; x < radius * 2; x++) {
            for (int y = 0; y < radius * 2; y++) {
                tile.set(x, y);
                float dx = center.x - tile.x, dy = center.y - tile.y;
                float distance = (float) Math.sqrt(dx * dx + dy * dy);
                if (distance < radius) {
                    color.a = 0.25f;
                    newPixmap.setColor(color);
                    newPixmap.drawPixel(x, y);
                }
            }
        }
        return newPixmap;
    }

    public static Pixmap createLight(Color color, int width, int height) {
        Pixmap newPixmap = new Pixmap(width, height, Format.RGBA8888);
        Vector2 tile = new Vector2(0, 0);
        Vector2 center = new Vector2(width / 2f, height / 2f);

        newPixmap.setBlending(Blending.SourceOver);
        for (int x = 0; x <= width; x++) {
            for (int y = 0; y <= height; y++) {
                tile.set(x, y);
                color.a = 1 - center.dst(tile) / Math.max(width, height);
                newPixmap.setColor(color);
                newPixmap.drawPixel(x, y);
            }
        }
        return newPixmap;
    }

    public static Pixmap convertToPixmap(Texture texture) {
        if (!texture.getTextureData().isPrepared()) {
            texture.getTextureData().prepare();
        }
        return texture.getTextureData().consumePixmap();
    }

    public static Pixmap convertToPixmap(TextureRegion region) {
        Pixmap pixmap = convertToPixmap(region.getTexture());
        Pixmap newPixmap = new Pixmap(region.getRegionWidth(), region.getRegionHeight(), Format.RGBA8888);
        int pixelValue;
        for (int x = 0; x <= newPixmap.getWidth(); x++) {
            for (int y = 0; y <= newPixmap.getHeight(); y++) {
                pixelValue = pixmap.getPixel(region.getRegionX() + x, region.getRegionY() + y);
                newPixmap.drawPixel(x, y, pixelValue);
            }
        }
        return newPixmap;
    }
}