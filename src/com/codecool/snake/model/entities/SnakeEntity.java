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


/**
 * General constants for directions
 */
enum Direction {
    LEFT, RIGHT, CENTER
}

/**
 * Holds parts of snake and direction where he goes
 */
public class SnakeEntity extends Entity {
    private Deque<Shape> tail = new ArrayDeque<>();
    private Direction turnDirection = Direction.CENTER;

    /**
     * Creates alive snake entity,
     * and populate with parts
     *
     * @param initialSize - count of parts
     */
    public SnakeEntity(int initialSize) {
        super();
        this.setEntityType(GameEntityType.SNAKE);

        for (int i = initialSize; i > 0; --i) {
            Shape newPart = getShape().cloneShape();
            tail.addLast(newPart);
        }
    }

    /**
     * More specific movement for snake
     */
    public void movement() {

        switch (turnDirection) {
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

    /**
     * Detect if this entity collide with other entity
     *
     * @param collider - entity which can collide
     * @return - True if collide, False otherwise
     */
    public boolean isCollideWith(Entity collider) {
        return collider.getShape().intersectWith(this.getShape());
    }

    /**
     * Mechanism which choose behaviour based on interacting entity
     *
     * @param otherGameObject - entity which interact
     */
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

    /**
     * Behaviour when we interact with edible entities (powerups)
     *
     * @param edibleEntity - entity to eat
     */
    private void eat(Entity edibleEntity) {
        System.out.println("==> Snake eat [" + edibleEntity.getEntityType() + "]");

        tail.addFirst(getShape().cloneShape());
        edibleEntity.death();
    }

    /**
     * Behaviour when we interact with entities which can kill us (enemies)
     *
     * @param killableEntity - entity which can kill
     */
    private void kill(Entity killableEntity) {
        System.out.println("==> Snake kill [" + killableEntity.getEntityType() + "]");

        killableEntity.death();
    }

    /**
     * Getter for all underlying snnake shape
     *
     * @return - array of shapes
     */
    public ArrayList<Shape> getSnakeShape() {
        ArrayList<Shape> snakeShape = new ArrayList<>();
        snakeShape.add(getShape());
        snakeShape.addAll(tail);

        return snakeShape;
    }

    /**
     * Mechanism which interpret key press event for snake entity
     *
     * @param event - event occured
     */
    public void interpretPressEvent(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {
            this.turnDirection = Direction.LEFT;
        }

        if (event.getCode() == KeyCode.RIGHT) {
            this.turnDirection = (Direction.RIGHT);
        }
    }

    /**
     * Mechanism which interpret key release event for snake entity
     */
    public void interpretReleaseEvent() {
        this.turnDirection = Direction.CENTER;
    }

}