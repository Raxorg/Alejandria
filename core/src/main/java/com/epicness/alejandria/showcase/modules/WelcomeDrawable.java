package com.epicness.alejandria.showcase.modules;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_STRIPE_HEIGHT;
import static com.epicness.alejandria.showcase.constants.WelcomeConstants.CANVAS_HEIGHT;
import static com.epicness.alejandria.showcase.constants.WelcomeConstants.CANVAS_WIDTH;
import static com.epicness.alejandria.showcase.constants.WelcomeConstants.CANVAS_X;
import static com.epicness.alejandria.showcase.constants.WelcomeConstants.CANVAS_Y;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.stuff.Text;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class WelcomeDrawable implements ModuleDrawable {

    private final ShapeDrawer shapeDrawer;
    private final ShaderProgram shader;
    private final Sprited canvas;
    private final Text text;

    public WelcomeDrawable(ShapeDrawer shapeDrawer, BitmapFont pixelFont, Sprite pixel, ShaderProgram neonWavesShader) {
        this.shapeDrawer = shapeDrawer;

        shader = neonWavesShader;
        ShaderProgram.pedantic = false;
        if (!shader.isCompiled()) {
            Gdx.app.error("SHADER", "Failed to compile shader");
        }
        if (!shader.getLog().isEmpty()) {
            Gdx.app.error("SHADER LOG:", shader.getLog());
        }
        canvas = new Sprited(pixel);
        canvas.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setPosition(CANVAS_X, CANVAS_Y);

        text = new Text(pixelFont);
        text.setVerticallyCentered(true);
        text.setWidth(CAMERA_WIDTH);
        text.hAlignCenter();
        text.setY(SHOWCASE_STRIPE_HEIGHT * 1.5f);
        text.setText("By Luis \"Groxar\" Frontanilla");
        text.setScale(5f);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRendererPlus shapeRenderer) {
        spriteBatch.setShader(shader);
        spriteBatch.begin();
        canvas.draw(spriteBatch);
        spriteBatch.flush();
        spriteBatch.setShader(null);
        text.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeRendererPlus shapeRenderer) {
        shapeDrawer.getBatch().begin();
        text.drawDebug(shapeDrawer);
        shapeDrawer.getBatch().end();
    }

    public ShaderProgram getShader() {
        return shader;
    }
}