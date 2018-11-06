package com.codecool.snake.model.common;

import com.codecool.snake.model.Entity;


/**
 * Interface which must be implemented by classes
 * which want observer ObservableModel based object
 */
public interface ModelObserver {

    /**
     * Updates state of observator in respond to new created entity
     *
     * @param spawnEntity - Entity based object
     */
    void updateOnSpawn(Entity spawnEntity);

    /**
     * Updates state of observator in respond to destroyed entity
     *
     * @param destroyedEntity - Entity based object
     */
    void updateOnDestroy(Entity destroyedEntity);
}
