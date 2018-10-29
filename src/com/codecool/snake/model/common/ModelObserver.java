package com.codecool.snake.model.common;

import com.codecool.snake.model.Entity;

public interface ModelObserver {
    void updateOnSpawn(Entity spawnEntity);
    void updateOnDestroy(Entity destroyedEntity);
}
