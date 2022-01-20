package com.epicness.alejandria.module.stuff;

import com.epicness.alejandria.ModuleID;
import com.epicness.alejandria.module.stuff.modules.Module;

import java.util.HashMap;

public class ModuleStorage {

    private final HashMap<ModuleID, Module> moduleMap;
    private Module currentModule;

    public ModuleStorage() {
        moduleMap = new HashMap<>();
    }

    public void addModule(Module module) {
        moduleMap.put(module.getID(), module);
    }

    public Module getCurrentModule() {
        return currentModule;
    }

    public void setCurrentModule(ModuleID moduleID) {
        currentModule = moduleMap.get(moduleID);
    }

    public void draw() {
        currentModule.draw();
    }
}