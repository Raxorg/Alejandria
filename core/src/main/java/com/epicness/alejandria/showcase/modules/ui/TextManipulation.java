package com.epicness.alejandria.showcase.modules.ui;

import static com.badlogic.gdx.Input.Keys.NUM_1;
import static com.badlogic.gdx.Input.Keys.NUM_2;
import static com.badlogic.gdx.Input.Keys.NUM_3;
import static com.badlogic.gdx.Input.Keys.NUM_4;
import static com.badlogic.gdx.Input.Keys.NUM_5;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.epicness.alejandria.showcase.constants.UIConstants.TEXT_MANIPULATION_BASE_TEXT;
import static com.epicness.fundamentals.constants.SharedConstants.PASTEL_RED;

import com.badlogic.gdx.utils.Align;
import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.fundamentals.stuff.Text;
import com.epicness.fundamentals.utils.ColorUtils;

public class TextManipulation extends Module<TextManipulationDrawable> {

    private Text text;
    private boolean centerVertically;
    private String truncate;

    public TextManipulation() {
        super(
            "Text Manipulation",
            "Demonstration of the various ways we can manipulate how text is displayed\n\n" +
                "Press 1 through 5 to change the text's properties"
        );
    }

    @Override
    protected TextManipulationDrawable setup() {
        drawable = new TextManipulationDrawable(sharedAssets.getPixelFont());
        text = drawable.getText();
        centerVertically = true;
        truncate = null;
        return drawable;
    }

    @Override
    public void keyDown(int keycode) {
        switch (keycode) {
            case NUM_1:
                switch (text.getHAlign()) {
                    case Align.left:
                        text.hAlignCenter();
                        text.setText("Center Horizontal Align\n\n" + TEXT_MANIPULATION_BASE_TEXT);
                        break;
                    case Align.center:
                        text.hAlignRight();
                        text.setText("Right Horizontal Align\n\n" + TEXT_MANIPULATION_BASE_TEXT);
                        break;
                    case Align.right:
                        text.hAlignLeft();
                        text.setText("Left Horizontal Align\n\n" + TEXT_MANIPULATION_BASE_TEXT);
                        break;
                }
                break;
            case NUM_2:
                text.setVerticallyCentered(centerVertically = !centerVertically);
                text.setText("Vertical centering: " + centerVertically + "\n\n" + TEXT_MANIPULATION_BASE_TEXT);
                break;
            case NUM_3:
                text.setTruncate(truncate = null == truncate ? "..." : null);
                text.setText("Truncation: " + truncate + "\n\n" + TEXT_MANIPULATION_BASE_TEXT);
                break;
            case NUM_4:
                text.setScale(text.getScale() == 4 ? 5 : 4);
                text.setText("Scale: " + (int) text.getScale() + "\n\n" + TEXT_MANIPULATION_BASE_TEXT);
                break;
            case NUM_5:
                text.setColor(text.getColor().equals(WHITE) ? PASTEL_RED : WHITE);
                text.setText("Color: " + ColorUtils.stringFromColor(text.getColor()) + "\n\n" + TEXT_MANIPULATION_BASE_TEXT);
        }
    }
}