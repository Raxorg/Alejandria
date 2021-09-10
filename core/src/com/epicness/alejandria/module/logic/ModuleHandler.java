package com.epicness.alejandria.module.logic;

import com.epicness.alejandria.ModuleID;
import com.epicness.alejandria.module.stuff.ModuleStorage;
import com.epicness.alejandria.module.stuff.ModuleStuff;
import com.epicness.alejandria.module.stuff.modules.masking.LayeredMasking;
import com.epicness.alejandria.module.stuff.modules.masking.Masking;
import com.epicness.fundamentals.SharedScreen;
import com.epicness.fundamentals.assets.SharedAssets;

import static com.epicness.alejandria.ModuleID.MASKING;

public class ModuleHandler {

    // Structure
    private SharedAssets sharedAssets;
    private SharedScreen screen;
    private ModuleStuff stuff;

    public void setup() {
        ModuleStorage moduleStorage = stuff.getModuleStorage();

        moduleStorage.addModule(new LayeredMasking(sharedAssets, screen));
        moduleStorage.addModule(new Masking(sharedAssets));

        changeModule(MASKING);
    }

    public void update(float delta) {
        stuff.getModuleStorage().getCurrentModule().update(delta);
    }

    public void changeModule(ModuleID moduleID) {
        ModuleStorage moduleStorage = stuff.getModuleStorage();
        if (moduleStorage.getCurrentModule() != null) {
            moduleStorage.getCurrentModule().dispose();
        }
        moduleStorage.setCurrentModule(moduleID);
        moduleStorage.getCurrentModule().setup();
    }

    // Structure
    public void setSharedAssets(SharedAssets sharedAssets) {
        this.sharedAssets = sharedAssets;
    }

    public void setScreen(SharedScreen screen) {
        this.screen = screen;
    }

    public void setStuff(ModuleStuff stuff) {
        this.stuff = stuff;
    }
}