package com.codecool.snake.model.entities;

import com.codecool.snake.model.Entity;
import com.codecool.snake.model.common.GameEntityType;

public class EnemyEntity extends Entity {
    public EnemyEntity(){
        super();
        setEntityType(GameEntityType.ENEMY);
    }
}
