package com.epicness.alejandria.module.logic;

import static com.epicness.alejandria.ModuleID.PIXEL_PERFECT_COLLISION_DETECTION;

import com.epicness.alejandria.ModuleID;
import com.epicness.alejandria.module.stuff.ModuleStorage;
import com.epicness.alejandria.module.stuff.ModuleStuff;
import com.epicness.alejandria.module.stuff.modules.masking.AlphaMasking;
import com.epicness.alejandria.module.stuff.modules.masking.LayeredMasking;
import com.epicness.alejandria.module.stuff.modules.masking.ShapeRendererMasking;
import com.epicness.alejandria.module.stuff.modules.pixmaps.PixelPerfectCollisionDetection;
import com.epicness.alejandria.module.stuff.modules.rendering.ProceduralSquare;
import com.epicness.alejandria.module.stuff.modules.shaders.InvertShader;
import com.epicness.alejandria.module.stuff.modules.shaders.ShakeShader;
import com.epicness.alejandria.module.stuff.modules.shaders.VignetteShader;

public class ModuleHandler {

    // Structure
    private ModuleStuff stuff;

    public void setup() {
        ModuleStorage moduleStorage = stuff.getModuleStorage();

        moduleStorage.addModule(new LayeredMasking());
        moduleStorage.addModule(new AlphaMasking());
        moduleStorage.addModule(new ShapeRendererMasking());
        moduleStorage.addModule(new ProceduralSquare());
        moduleStorage.addModule(new InvertShader());
        moduleStorage.addModule(new ShakeShader());
        moduleStorage.addModule(new VignetteShader());
        moduleStorage.addModule(new PixelPerfectCollisionDetection());

        changeModule(PIXEL_PERFECT_COLLISION_DETECTION);
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