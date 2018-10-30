package com.codecool.snake.model.entities;

import com.codecool.snake.model.Entity;
import com.codecool.snake.model.Shape;
import com.codecool.snake.model.common.GameEntityType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import static com.codecool.snake.common.Config.ROTATE_SPEED;

enum Direction {
    LEFT, RIGHT, CENTER
}

public class SnakeEntity extends Entity {
    private Deque<Shape> tail = new ArrayDeque<>();
    private Direction turnDirection = Direction.CENTER;

    public SnakeEntity(int initialSize) {
        super();
        this.setEntityType(GameEntityType.SNAKE);

        for(int i = initialSize; i > 0; --i) {
            Shape newPart = getShape().cloneShape();
            tail.addLast(newPart);
        }
    }

    public void movement() {

        switch(turnDirection) {
            case LEFT:
                rotate(-ROTATE_SPEED);
                break;
            case RIGHT:
                rotate(ROTATE_SPEED);
                break;
            default:
                break;
        }

        tail.add(getShape().cloneShape());
        tail.poll();
        super.movement();
    }

    public boolean isCollideWith(Entity collider) {
        return collider.getShape().intersectWith(this.getShape());
    }

    public void interactWith(Entity otherGameObject) {

        switch (otherGameObject.getEntityType()) {
            case POWERUP:
                eat(otherGameObject);
                break;
            case ENEMY:
                kill(this);
//                kill(otherGameObject);
                break;
        }
    }

    private void eat(Entity edibleEntity) {
        System.out.println("==> Snake eat [" + edibleEntity.getEntityType() + "]");

        tail.addFirst(getShape().cloneShape());
        edibleEntity.death();
    }

    private void kill(Entity killableEntity) {
        System.out.println("==> Snake kill [" + killableEntity.getEntityType() + "]");

        killableEntity.death();
    }

    public ArrayList<Shape> getSnakeShape() {
        ArrayList<Shape> snakeShape = new ArrayList<>();
        snakeShape.add(getShape());
        snakeShape.addAll(tail);

        return snakeShape;
    }

    public void interpretPressEvent(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {
            this.turnDirection = Direction.LEFT;
        }

        if (event.getCode() == KeyCode.RIGHT) {
            this.turnDirection = (Direction.RIGHT);
        }
    }

    public void interpretReleaseEvent() {
        this.turnDirection = Direction.CENTER;
    }

}