package com.codecool.snake.view.entities;

import com.codecool.snake.model.common.EntityObserver;
import com.codecool.snake.model.Entity;
import com.codecool.snake.model.Bounds;
import com.codecool.snake.model.entities.SnakeEntity;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Iterator;

public class SnakeView extends Group implements EntityObserver {
    private int INITIAL_ROTATE = 90;

    private ImageView head;
    private Image tail;

    public SnakeView(Image costumeForHead, Image costumeForTail){
        this.head = new ImageView(costumeForHead);
        this.tail = costumeForTail;

        getChildren().add(this.head);
    }

    private void setPartBounds(Node part, Bounds bound) {
        part.setLayoutX(bound.getX());
        part.setLayoutY(bound.getY());
    }


    @Override
    public void updateOnChange(Entity changedEntity) {
        Platform.runLater(() -> {
            Iterator<Bounds> snakeBounds = ((SnakeEntity) changedEntity).getSnakeBounds().iterator();


            getChildren().listIterator().forEachRemaining(part -> {
                if(snakeBounds.hasNext()) setPartBounds(part, snakeBounds.next());
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
