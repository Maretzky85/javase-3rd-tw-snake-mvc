package com.codecool.snake.model;

import com.codecool.snake.model.common.GameEntityType;
import com.codecool.snake.model.common.ObservableEntity;

import static com.codecool.snake.common.Config.SNAKE_SPEED;

/**
 * Holds type which identify it as one of entities,
 * underlying moving shape,
 * direction and speed in which moves,
 * and life state
 */
public class Entity extends ObservableEntity {
    private GameEntityType entityType;
    private boolean alive;
    private Shape bound;
    private int angle,
            speed = SNAKE_SPEED;

    /**
     * Creates alive entity, out of arena
     */
    public Entity() {
        this.alive = true;
        this.bound = new Shape(-100, -100, 0);
        this.angle = 0;
    }

    /**
     * Creates alive entity, in place which bound points
     *
     * @param bound - place to create
     */
    public Entity(Shape bound) {
        this.alive = true;
        this.bound = bound;
        this.angle = 0;
    }

    /**
     * Getter for type of entity
     *
     * @return - type of entity
     */
    public GameEntityType getEntityType() {
        return entityType;
    }

    /**
     * Setter for type of entity
     *
     * @param entityType - type of entity
     */
    protected void setEntityType(GameEntityType entityType) {
        this.entityType = entityType;
    }

    /**
     * Getter for underlying shape
     *
     * @return - underlying shape
     */
    public Shape getShape() {
        return this.bound;
    }

    /**
     * Getter for angle in which entity move
     *
     * @return - angle in degree
     */
    public int getAngle() {
        return this.angle;
    }

    /**
     * Setter for angle in which entity move
     *
     * @param angle - angle in degree
     */
    void setAngle(int angle) {
        this.angle = angle;
    }

    /**
     * Setter for underlying shape
     *
     * @param newShape - new shape for entity
     */
    void setBounds(Shape newShape) {
        this.bound = newShape;
    }

    /**
     * Setter for speed for this entity
     *
     * @param speed - new speed
     */
    void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Check if entity is alive
     *
     * @return - True if current entity is alive, otherwise False
     */
    boolean isAlive() {
        return alive;
    }

    /**
     * Rotate entity by specific point,
     *
     * @param rotateBy - positive rotate to left, negative rotate to right
     */
    protected void rotate(int rotateBy) {
        this.angle += rotateBy;
    }

    /**
     * General movement behaviour for entity
     */
    public void movement() {
        this.bound.moveTo(speed, this.angle);
        notifyAboutChange(this);
    }

    /**
     * General death behaviour for entity
     */
    public void death() {
        System.out.println("==> ... Death of " + entityType);
        this.alive = false;
    }


}
