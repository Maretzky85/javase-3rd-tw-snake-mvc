package com.codecool.snake.model.entities;

import com.codecool.snake.model.Entity;
import com.codecool.snake.model.common.GameEntityType;

public class PowerupEntity extends Entity {
    public PowerupEntity() {
        super();
        setEntityType(GameEntityType.POWERUP);
    }
}
