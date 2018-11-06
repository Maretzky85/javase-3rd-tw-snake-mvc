package com.codecool.snake.model;

import com.codecool.snake.common.Config;

/**
 * This class represent underlying shape of ours entities.
 */
public class Shape {
    private double x;
    private double y;
    private int radius;

    /**
     * Constructs shape
     *
     * @param x      - x-axis coordinate
     * @param y      - y-axis coordinate
     * @param radius - radius of area occupied by shape
     */
    Shape(double x, double y, int radius) {
        this.radius = radius;
        this.x = x;
        this.y = y;
    }

    /**
     * Copy constructor it creates new shape from already existing.
     *
     * @param shapeToCopy - already existing shape
     */
    private Shape(Shape shapeToCopy) {
        this.radius = shapeToCopy.getRadius();
        this.x = shapeToCopy.getX();
        this.y = shapeToCopy.getY();
    }

    /**
     * Creates shape in random places based on size of arena on
     * it must appears.
     *
     * @return - created Shape object
     */
    static Shape getRandomBound() {
        int x = Config.RANDOMIZER.apply(Config.ARENA_WIDTH);
        int y = Config.RANDOMIZER.apply(Config.ARENA_HEIGHT);

        return new Shape(x, y, Config.HIT_BOX);


    }

    /**
     * Getter for x-axis cooridnates.
     *
     * @return - x-axis coordinates
     */
    public int getX() {
        return (int) x;
    }

    /**
     * Getter for y-axis cooridnates.
     *
     *
     * @return - y-axis coordinates
     */
    public int getY() {
        return (int) y;
    }

    /**
     * Getter for radius fo shape.
     *
     * @return - radius of area occupied by shape
     */
    private int getRadius() {
        return radius;
    }

    /**
     * Moves shape by distance based on specific angle.
     *
     * @param distance - distance to move
     * @param angle - angle in which it move
     */
    void moveTo(int distance, int angle) {
        this.x += distance * Math.cos(Math.toRadians(angle));
        this.y += distance * Math.sin(Math.toRadians(angle));
    }

    /**
     * Checks if this shape intersect with other shape.
     *
     * @param otherShape - Shape to check
     * @return - True if we intersect, otherwise False
     */
    public boolean intersectWith(Shape otherShape) {
        return Math.sqrt(Math.pow(this.x - otherShape.getX(), 2) + Math.pow(this.y - otherShape.getY(), 2)) <= this.radius + otherShape.getRadius();
    }

    /**
     * Clones this shape.
     *
     * @return - cloned Shape
     */
    public Shape cloneShape() {
        return new Shape(this);
    }
}