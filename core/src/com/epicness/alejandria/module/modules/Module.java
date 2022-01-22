package com.epicness.alejandria.module.modules;

import com.epicness.alejandria.ModuleID;

/** The modules package is an exception to the conventional structuring of epicness games/apps **/
public abstract class Module {

    private final ModuleID moduleID;

    public Module(ModuleID moduleID) {
        this.moduleID = moduleID;
    }

    public abstract void setup();

    public void update(float delta) {

    }

    public void draw() {

    }

    public void dispose() {

    }

    public ModuleID getID() {
        return moduleID;
    }
}