package com.epicness.alejandria.module.stuff.modules;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicness.alejandria.ModuleID;

public abstract class Module {

    private final ModuleID moduleID;

    public Module(ModuleID moduleID) {
        this.moduleID = moduleID;
    }

    public abstract void setup();

    public abstract void update(float delta);

    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {

    }

    public void dispose() {

    }

    public ModuleID getID() {
        return moduleID;
    }
}