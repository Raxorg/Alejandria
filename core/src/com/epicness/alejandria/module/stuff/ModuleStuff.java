package com.epicness.alejandria.module.stuff;

import com.epicness.fundamentals.stuff.Stuff;

public class ModuleStuff extends Stuff {

    private ModuleStorage moduleStorage;

    @Override
    public void initializeStuff() {
        moduleStorage = new ModuleStorage();
    }

    public ModuleStorage getModuleStorage() {
        return moduleStorage;
    }
}