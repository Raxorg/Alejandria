package com.epicness.alejandria.showcase.logic;

import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.logic.modules.cursor.PointAtCursor;

import java.util.HashMap;
import java.util.Map;

public class ShowcaseHandler extends ShowcaseLogicHandler {

    // Logic
    private Map<Class<? extends Module>, Module> moduleList;
    private Module currentModule;

    public void setup() {
        moduleList = new HashMap<>();
        moduleList.put(PointAtCursor.class, (Module) logic.getHandler(PointAtCursor.class));

        changeModule(PointAtCursor.class);
    }

    public void update(float delta) {
        currentModule.update(delta);
    }

    public void changeModule(Class<? extends Module> moduleClass) {
        if (currentModule != null) {
            currentModule.exit();
        }
        currentModule = moduleList.get(moduleClass);
        currentModule.setup();
    }
}