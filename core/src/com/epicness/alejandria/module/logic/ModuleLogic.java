package com.epicness.alejandria.module.logic;

import com.epicness.alejandria.module.stuff.ModuleStuff;
import com.epicness.fundamentals.input.SharedInput;
import com.epicness.fundamentals.logic.Logic;
import com.epicness.fundamentals.logic.SharedLogic;
import com.epicness.fundamentals.stuff.Stuff;

public class ModuleLogic extends Logic {

    private final ModuleHandler moduleHandler;
    private final ModuleInputHandler moduleInputHandler;

    public ModuleLogic(SharedLogic sharedLogic) {
        super(sharedLogic);
        moduleHandler = new ModuleHandler();
        moduleInputHandler = new ModuleInputHandler();

        moduleInputHandler.setLogic(this);
    }

    @Override
    public void initialLogic() {
        moduleHandler.setup();
        moduleInputHandler.setupInput();
    }

    @Override
    public void update(float delta) {
        moduleHandler.update(delta);
    }

    @Override
    public void setInput(SharedInput input) {
        moduleInputHandler.setInput(input);
    }

    @Override
    public void setStuff(Stuff stuff) {
        ModuleStuff moduleStuff = (ModuleStuff) stuff;
        moduleHandler.setStuff(moduleStuff);
    }

    public ModuleHandler getModuleHandler() {
        return moduleHandler;
    }
}