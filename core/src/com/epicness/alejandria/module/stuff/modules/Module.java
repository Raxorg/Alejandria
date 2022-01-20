package com.epicness.alejandria.module.stuff.modules;

import com.epicness.alejandria.ModuleID;

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