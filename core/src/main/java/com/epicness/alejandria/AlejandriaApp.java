package com.epicness.alejandria;

import com.badlogic.gdx.Game;
import com.epicness.alejandria.interfacing.AlertSystem;
import com.epicness.alejandria.interfacing.ShowcasePicker;
import com.epicness.alejandria.showcase.ShowcaseInitializer;
import com.epicness.alejandria.showcase.assets.ShowcaseAssets;
import com.epicness.alejandria.showcase.logic.ShowcaseLogic;
import com.epicness.fundamentals.SharedResources;

// TODO: 2/29/2024 Show FPS (1/Delta or Gdx.graphics.getFramesPerSecond() and Delta Time when debug enabled
// TODO: 3/9/2024 Add visible module category labels/tags
// TODO: 8/30/2024 Improve readme with images and such
// TODO: 8/30/2024 Create a setup tool like liftoff that creates the base structure for screens like splash, menu, game, etc
// TODO: 8/30/2024 Add "-", "+", "*" and "/" characters to pixel font
// TODO: 8/30/2024 OPTIMIZE CANTOR GASKET, IT IS TOO SLOW! (Probably multi frame solution)
// TODO: 8/31/2024 Allow drawDebug to use ShapeDrawer too
// TODO: 8/31/2024 The information panel expands to fill the entire screen
// TODO: 9/1/2024 Make it so I can share a module directly using the url
// HtmlLauncher: Window.Location.getParameter("whatever"): https://cool.game/?whatever=text
// TODO: 9/2/2024 Fog of War module
// TODO: 9/2/2024 Font generation from ttf file using FreeTypeFontGenerator
// TODO: 9/2/2024 Input module where you can see the key you touched in the center of the screen
// TODO: 9/2/2024 Blend functions module
// TODO: 9/5/2024 Add GDX-VFX module
public class AlejandriaApp extends Game {

    private AlertSystem alertSystem;
    private ShowcasePicker showcasePicker;

    @Override
    public void create() {
        ShowcaseAssets assets = new ShowcaseAssets();
        assets.queueAssetLoading();
        assets.finishLoading();
        assets.initializeAssets();

        var potentialShowcaseName = showcasePicker.getShowcase();
        var showcaseInitializer = new ShowcaseInitializer(assets);
        showcaseInitializer.initialize(new SharedResources());
        ((ShowcaseLogic) showcaseInitializer.getLogic()).setShowcaseByName(potentialShowcaseName);
    }

    public AlertSystem getAlertSystem() {
        return alertSystem;
    }

    public void setAlertSystem(AlertSystem alertSystem) {
        this.alertSystem = alertSystem;
    }

    public void setShowcasePicker(ShowcasePicker showcasePicker) {
        this.showcasePicker = showcasePicker;
    }

    public ShowcasePicker getShowcasePicker() {
        return showcasePicker;
    }
}