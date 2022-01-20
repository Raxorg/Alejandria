package com.epicness.fundamentals.logic.behaviors;

import com.epicness.fundamentals.stuff.interfaces.Parallaxable;

import java.util.HashMap;
import java.util.Map;

public class ParallaxBehavior {
    // Logic
    private final HashMap<Parallaxable, Float> parallaxables;

    public ParallaxBehavior() {
        parallaxables = new HashMap<>();
    }

    public void addParallaxable(Parallaxable parallaxable, float parallaxFactor) {
        parallaxables.put(parallaxable, parallaxFactor);
    }

    public void update(float xTranslation) {
        for (Map.Entry<Parallaxable, Float> e : parallaxables.entrySet()) {
            Parallaxable parallaxable = e.getKey();
            float parallaxFactor = e.getValue();
            parallaxable.translateX(xTranslation * parallaxFactor);
        }
    }
}