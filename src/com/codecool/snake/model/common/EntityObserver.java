package com.codecool.snake.model.common;

import com.codecool.snake.model.Entity;

public interface EntityObserver {
    void updateOnChange(Entity changedEntity);
}
