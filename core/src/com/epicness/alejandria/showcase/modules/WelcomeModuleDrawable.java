package com.epicness.alejandria.showcase.modules;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.STRIPE_HEIGHT;
import static com.epicness.alejandria.showcase.constants.WelcomeConstants.CANVAS_SIZE;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.constants.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Align;
import com.epicness.fundamentals.renderer.ShapeRendererPlus;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.stuff.Text;
import com.epicness.alejandria.showcase.stuff.modules.ModuleDrawable;

public class WelcomeModuleDrawable implements ModuleDrawable {

    private final ShaderProgram shader;
    private final Sprited canvas;
    private final Text text;

    public WelcomeModuleDrawable(BitmapFont pixelFont, Sprite pixel) {
        FileHandle vertexShader = Gdx.files.internal("alejandria/showcase/shaders/shared/vertex.glsl");
        FileHandle fragmentShader = Gdx.files.internal("youtube05.glsl");
        //init shaders
        shader = new ShaderProgram(vertexShader, fragmentShader);
        shader.bind();
        ShaderProgram.pedantic = false;
        if (!shader.isCompiled()) {
            Gdx.app.error("SHADER", "Failed to compile shader");
        }
        if (!shader.getLog().isEmpty()) {
            Gdx.app.error("SHADER LOG:", shader.getLog());
        }
        canvas = new Sprited(pixel);
        canvas.setSize(CANVAS_SIZE);
        canvas.setOriginCenter();
        canvas.setOriginBasedPosition(CAMERA_HALF_WIDTH, CAMERA_HALF_HEIGHT);

        text = new Text(pixelFont);
        text.setCenterVertical(true);
        text.setTextTargetWidth(CAMERA_WIDTH);
        text.setHorizontalAlignment(Align.center);
        text.setY(STRIPE_HEIGHT * 1.5f);
        text.setText("By Luis \"Groxar\" Frontanilla");
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
        text.drawDebug(shapeRenderer);
    }

    public ShaderProgram getShader() {
        return shader;
    }
}