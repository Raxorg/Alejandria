package com.epicness.alejandria.showcase.modules;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.STRIPE_HEIGHT;
import static com.epicness.alejandria.showcase.constants.WelcomeConstants.CANVAS_SIZE;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_HEIGHT;
import static com.epicness.fundamentals.SharedConstants.CAMERA_HALF_WIDTH;
import static com.epicness.fundamentals.SharedConstants.CAMERA_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Align;
import com.epicness.fundamentals.renderer.ShapeBatch;
import com.epicness.fundamentals.stuff.Sprited;
import com.epicness.fundamentals.stuff.Text;
import com.epicness.fundamentals.stuff.interfaces.Drawable;

public class WelcomeDrawable implements Drawable {

    private final ShaderProgram shader;
    private final Sprited canvas;
    private final Text text;

    public WelcomeDrawable(BitmapFont pixelFont, Sprite pixel) {
        FileHandle vertexShader = Gdx.files.internal("alejandria/showcase/shaders/shared/vertex.glsl");
        FileHandle fragmentShader = Gdx.files.internal("youtube05.glsl");
        //init shaders
        shader = new ShaderProgram(vertexShader, fragmentShader);
        shader.bind();
        ShaderProgram.pedantic = false;
        if (!shader.isCompiled()) {
            System.out.println("ERROR: Failed to compile shaders");
        }
        if (!shader.getLog().isEmpty()) {
            System.out.println("SHADER LOG: " + shader.getLog());
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
    public void draw(SpriteBatch spriteBatch, ShapeBatch shapeBatch) {
        spriteBatch.setShader(shader);
        spriteBatch.begin();
        canvas.draw(spriteBatch);
        spriteBatch.flush();
        spriteBatch.setShader(null);
        text.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void drawDebug(ShapeBatch shapeBatch) {
        text.drawDebug(shapeBatch);
    }

    public ShaderProgram getShader() {
        return shader;
    }
}