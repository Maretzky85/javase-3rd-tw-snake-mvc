package com.codecool.snake.model.common;

import com.codecool.snake.model.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds list of observators, can take new observators
 */
public class ObservableEntity {
    private List<EntityObserver> observers = new ArrayList<>();

    /**
     * Add new observer to list
     *
     * @param observer - object with implement EntityObserver
     */
    public void addObserver(EntityObserver observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }

    /**
     * Notify about changing in entity
     *
     * @param changedEntity - entity which will change
     */
    protected void notifyAboutChange(Entity changedEntity) {
        observers.forEach(entityObserver -> entityObserver.updateOnChange(changedEntity));
    }
}
