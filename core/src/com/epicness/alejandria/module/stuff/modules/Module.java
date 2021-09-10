package com.epicness.alejandria.module.stuff.modules;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.alejandria.ModuleID;

public abstract class Module {

    private final ModuleID moduleID;

    public Module(ModuleID moduleID) {
        this.moduleID = moduleID;
    }

    public abstract void setup();

    public abstract void update(float delta);

    public abstract void draw(SpriteBatch spriteBatch);

    public ModuleID getID() {
        return moduleID;
    }
}