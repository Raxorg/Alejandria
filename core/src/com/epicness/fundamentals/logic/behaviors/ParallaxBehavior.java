package com.epicness.fundamentals.logic.behaviors;

import com.epicness.fundamentals.stuff.interfaces.Movable;

import java.util.HashMap;
import java.util.Map;

public class ParallaxBehavior {

    private final HashMap<Movable, Float> movables;

    public ParallaxBehavior() {
        movables = new HashMap<>();
    }

    public void addMovable(Movable movable, float parallaxFactor) {
        movables.put(movable, parallaxFactor);
    }

    public void update(float xTranslation) {
        for (Map.Entry<Movable, Float> e : movables.entrySet()) {
            Movable movable = e.getKey();
            float parallaxFactor = e.getValue();
            movable.translateX(xTranslation * parallaxFactor);
        }
    }
}