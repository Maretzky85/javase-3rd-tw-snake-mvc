package com.codecool.snake.model.entities;

import com.codecool.snake.model.Entity;
import com.codecool.snake.model.common.GameEntityType;


/**
 * Holds data about powerup entity
 */
public class PowerupEntity extends Entity {

    /**
     * Creates new powerup entity
     */
    public PowerupEntity() {
        super();
        setEntityType(GameEntityType.POWERUP);
    }
}
