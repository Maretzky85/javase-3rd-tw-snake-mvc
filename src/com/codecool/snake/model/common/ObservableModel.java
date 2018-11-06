package com.codecool.snake.model.common;

import com.codecool.snake.model.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds list of observators, can take new observators
 */
public class ObservableModel {
    private List<ModelObserver> observators = new ArrayList<>();

    /**
     * Add new observer to list
     *
     * @param observer - object with implement ModelObserver
     */
    public void addObserver(ModelObserver observer) {
        if (observer != null) {
            observators.add(observer);
        }
    }

    /**
     * Notify observators about spawning of entity
     *
     * @param spawnedEntity - new created entity
     */
    protected void notifyAboutSpawn(Entity spawnedEntity) {
        observators.forEach(modelObserver -> modelObserver.updateOnSpawn(spawnedEntity));
    }

    /**
     * Notify observators about destroying of entity
     *
     * @param destroyedEntity - existing entity to remove
     */
    protected void notifyAboutDestroy(Entity destroyedEntity) {
        observators.forEach(modelObserver -> modelObserver.updateOnDestroy(destroyedEntity));
    }
}
