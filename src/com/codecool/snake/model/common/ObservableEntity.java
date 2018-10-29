package com.codecool.snake.model.common;

import com.codecool.snake.model.Entity;

import java.util.ArrayList;
import java.util.List;

public class ObservableEntity {
    private List<EntityObserver> observers = new ArrayList<>();

    public void addObserver(EntityObserver observer) {
        if(observer != null) {
            observers.add(observer);
        }
    }

    public void notifyAboutChange(Entity changedEntity) {
        observers.forEach(entityObserver -> entityObserver.updateOnChange(changedEntity));
    }
}
