package com.codecool.snake.view.entities;

import com.codecool.snake.model.Entity;
import com.codecool.snake.model.common.EntityObserver;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This is view class for entity visual representation other then snake
 */
public class EntityView extends Group implements EntityObserver {

    /**
     * Creates Visual representation of some entity,
     * attaches it to scene, and saves image of tail for future use
     *
     * @param costumeForHead - Image for entity head
     */
    public EntityView(Image costumeForHead) {
        getChildren().add(new ImageView(costumeForHead));
    }

    /**
     * Updates coordinates of entity
     *
     * @param changedEntity - Entity based object
     */
    @Override
    public void updateOnChange(Entity changedEntity) {
        Platform.runLater(() -> {
            setLayoutX(changedEntity.getShape().getX());
            setLayoutY(changedEntity.getShape().getY());
            setRotate(changedEntity.getAngle());
        }); //Resolves problem with foreign thread making changes in JavaFX
    }
}
