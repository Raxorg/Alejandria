package com.epicness.alejandria.showcase.logic.input;

import com.epicness.alejandria.showcase.logic.modules.Module;
import com.epicness.alejandria.showcase.logic.modules.animations.SpriteAnimation;

@SuppressWarnings("rawtypes")
public class ModuleInputAdapter extends ModuleInput {

    public void setModuleClass(Class<? extends Module<?>> moduleClass) {
        module = logic.handler(moduleClass);
    }

    @Override
    protected Class<?> setup() {
        return SpriteAnimation.class;
    }
}