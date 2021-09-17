package com.epicness.alejandria.module.logic;

import com.epicness.alejandria.ModuleID;
import com.epicness.alejandria.module.stuff.ModuleStorage;
import com.epicness.alejandria.module.stuff.ModuleStuff;
import com.epicness.alejandria.module.stuff.modules.masking.AlphaMasking;
import com.epicness.alejandria.module.stuff.modules.masking.LayeredMasking;
import com.epicness.alejandria.module.stuff.modules.masking.ShapeRendererMasking;

import static com.epicness.alejandria.ModuleID.ALPHA_MASKING;

public class ModuleHandler {

    // Structure
    private ModuleStuff stuff;

    public void setup() {
        ModuleStorage moduleStorage = stuff.getModuleStorage();

        moduleStorage.addModule(new LayeredMasking());
        moduleStorage.addModule(new AlphaMasking());
        moduleStorage.addModule(new ShapeRendererMasking());

        changeModule(ALPHA_MASKING);
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
    public void setStuff(ModuleStuff stuff) {
        this.stuff = stuff;
    }
}