package com.epicness.alejandria.showcase.logic.input;

import com.epicness.alejandria.showcase.logic.Module;
import com.epicness.alejandria.showcase.modules.animations.SpriteAnimation;

@SuppressWarnings("rawtypes")
public class ModuleInputAdapter extends ModuleInput {

    public void setModuleClass(Class<? extends Module<?>> moduleClass) {
        module = logic.get(moduleClass);
    }

    @Override
    protected Class<?> setup() {
        return SpriteAnimation.class;
    }
}