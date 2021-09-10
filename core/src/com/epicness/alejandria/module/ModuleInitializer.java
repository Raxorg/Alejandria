package com.epicness.alejandria.module;

import com.epicness.alejandria.module.logic.ModuleLogic;
import com.epicness.alejandria.module.stuff.ModuleStuff;
import com.epicness.fundamentals.Initializer;
import com.epicness.fundamentals.SharedResources;

public class ModuleInitializer extends Initializer {

    public ModuleInitializer() {
        super(new ModuleAssets());
    }

    @Override
    public void initialize(SharedResources sharedResources) {
        logic = new ModuleLogic(sharedResources.getLogic());
        renderer = new ModuleRenderer();
        stuff = new ModuleStuff();
        super.initialize(sharedResources);
    }
}