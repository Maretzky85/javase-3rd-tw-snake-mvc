package com.codecool.snake.model.common;

import com.codecool.snake.model.Entity;


/**
 * Interface which must be implemented by classes
 * which want observer ObservableEntity based object
 */
public interface EntityObserver {

    /**
     * Updates coordinates of entity
     *
     * @param changedEntity - Entity based object
     */
    void updateOnChange(Entity changedEntity);
}
