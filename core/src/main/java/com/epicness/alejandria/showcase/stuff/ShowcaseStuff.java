package com.epicness.alejandria.showcase.stuff;

import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.GITHUB_BUTTON_X;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.INFO_BUTTON_X;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.NEXT_BUTTON_X;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_BUTTON_SIZE;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.SHOWCASE_STRIPE_HEIGHT;
import static com.epicness.alejandria.showcase.constants.ShowcaseConstants.TOP_STRIPE_Y;
import static com.epicness.fundamentals.constants.ColorConstants.WHITE_25;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_HEIGHT;
import static com.epicness.fundamentals.constants.SharedConstants.VIEWPORT_WIDTH;
import static com.epicness.fundamentals.utils.TextUtils.copyOf;

import com.epicness.alejandria.showcase.assets.ShowcaseAssets;
import com.epicness.fundamentals.stuff.SpritePlus;
import com.epicness.fundamentals.stuff.SpritedText;
import com.epicness.fundamentals.stuff.Stuff;

public class ShowcaseStuff extends Stuff<ShowcaseAssets> {

    private SpritedText title;
    private Showcase showcase;
    private SpritePlus bottomStripe, previous, gitHubButton, infoButton, next;
    private SpritedText information;

    @Override
    public void initializeStuff() {
        title = new SpritedText(sharedAssets.getPixel(), copyOf(sharedAssets.getPixelFont()));
        title.setSize(VIEWPORT_WIDTH, SHOWCASE_STRIPE_HEIGHT);
        title.setTextTargetWidth(VIEWPORT_WIDTH);
        title.setY(TOP_STRIPE_Y);
        title.setBackgroundColor(WHITE_25);
        title.setFontScale(5f);

        showcase = new Showcase();

        bottomStripe = new SpritePlus(sharedAssets.getPixel());
        bottomStripe.setSize(VIEWPORT_WIDTH, SHOWCASE_STRIPE_HEIGHT);
        bottomStripe.setColor(WHITE_25);

        previous = new SpritePlus(assets.getArrow());
        previous.setSize(SHOWCASE_BUTTON_SIZE);
        previous.setOriginCenter();
        previous.rotate(180f);

        gitHubButton = new SpritePlus(assets.getGitHub());
        gitHubButton.setSize(SHOWCASE_BUTTON_SIZE);
        gitHubButton.setX(GITHUB_BUTTON_X);
        gitHubButton.useBilinearFilter();

        infoButton = new SpritePlus(assets.getInfo());
        infoButton.setSize(SHOWCASE_BUTTON_SIZE);
        infoButton.setX(INFO_BUTTON_X);

        next = new SpritePlus(assets.getArrow());
        next.setSize(SHOWCASE_BUTTON_SIZE);
        next.setX(NEXT_BUTTON_X);

        information = new SpritedText(sharedAssets.getPixel(), copyOf(sharedAssets.getPixelFont()));
        information.setSize(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        information.setTextTargetWidth(VIEWPORT_WIDTH * 0.9f);
        information.setFontScale(5f);
    }

    public SpritedText getTitle() {
        return title;
    }

    public Showcase getShowcase() {
        return showcase;
    }

    public SpritePlus getBottomStripe() {
        return bottomStripe;
    }

    public SpritePlus getPrevious() {
        return previous;
    }

    public SpritePlus getGitHubButton() {
        return gitHubButton;
    }

    public SpritePlus getInfoButton() {
        return infoButton;
    }

    public SpritePlus getNext() {
        return next;
    }

    public SpritedText getInformation() {
        return information;
    }
}