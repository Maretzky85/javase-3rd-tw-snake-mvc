package com.codecool.snake.view.entities;

import com.codecool.snake.model.Entity;
import com.codecool.snake.model.Shape;
import com.codecool.snake.model.common.EntityObserver;
import com.codecool.snake.model.entities.SnakeEntity;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Iterator;

/**
 * This is view class for snake visual representation
 * holds node of snake head from scene,
 * image of tail needed when we add new part
 */
public class SnakeView extends Group implements EntityObserver {
    private int INITIAL_ROTATE = 90;

    private ImageView head;
    private Image tail;

    /**
     * Creates Visual representation of snake,
     * attaches it to scene, and saves image of tail for future use
     *
     * @param costumeForHead - Image for snake head
     * @param costumeForTail - Image for snake tail
     */
    public SnakeView(Image costumeForHead, Image costumeForTail) {
        this.head = new ImageView(costumeForHead);
        this.tail = costumeForTail;

        getChildren().add(this.head);
    }

    /**
     * Helper method to change cordinates of node on scene based,
     * on shape passed.
     *
     * @param part  - JavaFx based Node
     * @param bound - underlying shape with coordinate
     */
    private void setPartBounds(Node part, Shape bound) {
        part.setLayoutX(bound.getX());
        part.setLayoutY(bound.getY());
    }


    /**
     * Updates cooridnates of snake parts,
     * If data holds much more elements then scene nodes,
     * it creates new elements
     *
     * @param changedEntity - Entity based object
     */
    @Override
    public void updateOnChange(Entity changedEntity) {
        Platform.runLater(() -> {
            Iterator<Shape> snakeBounds = ((SnakeEntity) changedEntity).getSnakeShape().iterator();


            getChildren().listIterator().forEachRemaining(part -> {
                if (snakeBounds.hasNext()) setPartBounds(part, snakeBounds.next());
            });

            snakeBounds.forEachRemaining(bound -> {
                ImageView part = new ImageView(tail);

                getChildren().add(part);
                setPartBounds(part, bound);
            });

            this.head.setRotate(INITIAL_ROTATE + changedEntity.getAngle());
        });
    }
}
