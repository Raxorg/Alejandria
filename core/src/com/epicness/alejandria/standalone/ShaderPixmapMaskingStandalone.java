package com.epicness.alejandria.standalone;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.badlogic.gdx.graphics.Pixmap.Blending.None;
import static com.badlogic.gdx.graphics.Pixmap.Format.Alpha;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;

public class ShaderPixmapMaskingStandalone extends Game {

    private final int size = 300;
    private Texture smileColor, smileOutline;
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;

    @Override
    public void create() {
        /* The fragment shader simply multiplies the fragment's usual alpha with
         * our mask alpha, since we only care about the alpha channel, the Alpha
         * Pixmap format is just what we need. */
        Pixmap pixmap = new Pixmap(size, size, Alpha);

        /* Pixmap blending can result in some funky looking lines when
         * drawing. You may need to disable it. */
        pixmap.setBlending(None);

        /* The default color of a newly created Pixmap has an alpha value of 0
         * play with different alpha values for different levels of transparency. */
        pixmap.setColor(0, 0, 0, 1);

        /* This setting will let us see some portions of the masked image. */
        pixmap.fillCircle(size / 4, size / 4, size / 4);
        pixmap.setColor(0, 0, 0, 0.5f);
        pixmap.fillRectangle(size / 2, size / 2, size / 2, size / 2);

        /* Create a Texture based on the pixmap. */
        Texture pixmapTex = new Texture(pixmap);

        /* Bind the mask texture to TEXTURE<N> (TEXTURE1 for our purposes),
         * which also sets the currently active texture unit. */
        pixmapTex.bind(1);

        /* However SpriteBatch will auto-bind to the current active texture,
         * so we must now reset it to TEXTURE0 or else our mask will be
         * overwritten. */
        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);

        /* Some regular textures to draw on the screen. */
        smileColor = new Texture("images/masking/smiley_color.png");
        smileOutline = new Texture("images/masking/smiley_outline.png");

        /* It's nicer to keep shader programs as text files in the assets
         * directory rather than dealing with horrid Java string formatting. */
        FileHandle vertexShader = Gdx.files.internal("shaders/shared/vertex.glsl");
        FileHandle fragmentShader = Gdx.files.internal("shaders/masking/fragment.glsl");

        /* Bonus: you can set `pedantic = false` while tinkering with your
         * shaders. This will stop it from crashing if you have unused variables
         * and so on. */
        ShaderProgram.pedantic = false;

        /* Construct our shader program. Spit out a log and quit if the shaders
         * fail to compile. */
        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);
        if (!shader.isCompiled()) {
            Gdx.app.log("Shader", shader.getLog());
            Gdx.app.exit();
        }

        /* Tell our shader that u_texture will be in the TEXTURE0 spot and
         * u_mask will be in the TEXTURE1 spot. We can set these now since
         * they'll never change; we don't have to send them every render frame. */
        shader.bind();
        shader.setUniformi("u_texture", 0);
        shader.setUniformi("u_mask", 1);

        /* Construct a simple SpriteBatch using our shader program. */
        spriteBatch = new SpriteBatch();
        spriteBatch.setShader(shader);

        /* Construct a simple ShapeRenderer to draw reference contours. */
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        Gdx.gl.glLineWidth(2);
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.BLACK);

        spriteBatch.begin();
        drawImages();
        spriteBatch.end();

        shapeRenderer.begin();
        drawContours();
        shapeRenderer.end();
    }

    private void drawImages() {
        spriteBatch.draw(smileColor, 0, 0, size, size);
        spriteBatch.draw(smileOutline, 0, 0, size, size);
    }

    private void drawContours() {
        /* The contour of the image. */
        shapeRenderer.set(Line);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(0, 0, size, size);
        /* The contour of the masks. */
        shapeRenderer.setColor(Color.CYAN);
        shapeRenderer.rect(size / 2f, 0f, size / 2f, size / 2f);
        shapeRenderer.circle(size / 4f, size * 0.75f, size / 4f);
    }
}