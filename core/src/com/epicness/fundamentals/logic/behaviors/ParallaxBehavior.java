package com.epicness.fundamentals.logic.behaviors;

import com.epicness.fundamentals.stuff.interfaces.Movable;

import java.util.HashMap;
import java.util.Map;

public class ParallaxBehavior {

    private final HashMap<Movable, Float> parallaxables;

    public ParallaxBehavior() {
        parallaxables = new HashMap<>();
    }

    public void addParallaxable(Movable parallaxable, float parallaxFactor) {
        parallaxables.put(parallaxable, parallaxFactor);
    }

    public void update(float xTranslation) {
        for (Map.Entry<Movable, Float> e : parallaxables.entrySet()) {
            Movable parallaxable = e.getKey();
            float parallaxFactor = e.getValue();
            parallaxable.translateX(xTranslation * parallaxFactor);
        }
    }
}