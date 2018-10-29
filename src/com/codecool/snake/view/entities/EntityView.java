package com.codecool.snake.view.entities;

import com.codecool.snake.model.common.EntityObserver;
import com.codecool.snake.model.Entity;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EntityView extends Group implements EntityObserver {

    public EntityView(Image costumeForHead){
        getChildren().add(new ImageView(costumeForHead));
    }

    @Override
    public void updateOnChange(Entity changedEntity) {
        Platform.runLater(() -> {
            setLayoutX(changedEntity.getBounds().getX());
            setLayoutY(changedEntity.getBounds().getY());
            setRotate(changedEntity.getAngle());
        }); //Resolves problem with foreign thread making changes in JavaFX
    }
}
