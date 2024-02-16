package com.epicness.fundamentals.logic.behaviors;

public class Tracker<A> {

    private A trackedObject;

    public A get() {
        return trackedObject;
    }

    public void set(A trackedObject) {
        this.trackedObject = trackedObject;
    }
}