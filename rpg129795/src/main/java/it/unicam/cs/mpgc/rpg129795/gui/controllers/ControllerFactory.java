package it.unicam.cs.mpgc.rpg129795.gui.controllers;

import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class ControllerFactory implements Callback<Class<?>, Object> {

    private final Map<Class<?>, Supplier<Object>> creators = new HashMap<>();

    public void register(Class<?> controllerType, Supplier<Object> creator) {
        if (controllerType == null || creator == null) {
            throw new IllegalArgumentException("Controller type and creator cannot be null");
        }
        creators.put(controllerType, creator);
    }

    public Object call(Class<?> controllerType) {
        Supplier<Object> creator = creators.get(controllerType);
        if (creator == null) {
            throw new IllegalStateException("No controller registered for " + controllerType.getSimpleName());
        }
        return creator.get();
    }

}