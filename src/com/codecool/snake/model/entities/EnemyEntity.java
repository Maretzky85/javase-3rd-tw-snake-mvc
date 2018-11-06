package com.codecool.snake.model.entities;

import com.codecool.snake.model.Entity;
import com.codecool.snake.model.common.GameEntityType;

/**
 * Holds data about enemy entity
 */
public class EnemyEntity extends Entity {

    /**
     * Creates new powerup entity
     */
    public EnemyEntity() {
        super();
        setEntityType(GameEntityType.ENEMY);
    }
}
