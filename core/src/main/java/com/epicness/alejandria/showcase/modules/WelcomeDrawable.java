package com.epicness.alejandria.showcase.modules;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_STRIPE_HEIGHT;
import static com.epicness.alejandria.showcase.constants.WelcomeConstants.CANVAS_HEIGHT;
import static com.epicness.alejandria.showcase.constants.WelcomeConstants.CANVAS_Y;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeDrawerPlus;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.SpritePlus;
import com.epicness.fundamentals.stuff.Text;

public class WelcomeDrawable implements ModuleDrawable {

    private final ShaderProgram shader;
    private final SpritePlus canvas;
    private final Text authorText;

    public WelcomeDrawable(BitmapFont pixelFont, Sprite pixel, ShaderProgram neonWavesShader) {
        shader = neonWavesShader;
        ShaderProgram.pedantic = false;
        if (!shader.isCompiled()) {
            Gdx.app.error("SHADER", "Failed to compile shader");
        }
        if (!shader.getLog().isEmpty()) {
            Gdx.app.error("SHADER LOG:", shader.getLog());
        }
        canvas = new SpritePlus(pixel);
        canvas.setSize(VIEWPORT_WIDTH, CANVAS_HEIGHT);
        canvas.setY(CANVAS_Y);

        authorText = new Text(pixelFont);
        authorText.setVerticallyCentered(true);
        authorText.setWidth(VIEWPORT_WIDTH);
        authorText.hAlignCenter();
        authorText.setY(SHOWCASE_STRIPE_HEIGHT * 1.5f);
        authorText.setText("By Luis \"Groxar\" Frontanilla");
        authorText.setScale(5f);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeDrawerPlus shapeDrawer, ShapeRendererPlus shapeRenderer) {
        spriteBatch.setShader(shader);
        spriteBatch.begin();
        canvas.draw(spriteBatch);
        spriteBatch.flush();
        spriteBatch.setShader(null);
        authorText.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeDrawerPlus shapeDrawer) {
        authorText.drawDebug(shapeDrawer);
    }

    public ShaderProgram getShader() {
        return shader;
    }
}