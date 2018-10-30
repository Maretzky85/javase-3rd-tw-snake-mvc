package com.codecool.snake.model;

import com.codecool.snake.common.Config;

public class Shape {
    private double x;
    private double y;
    private int radius;

    Shape(double x, double y, int radius) {
        this.radius = radius;
        this.x = x;
        this.y = y;
    }

    private Shape(Shape shapeToCopy) {
        this.radius = shapeToCopy.getRadius();
        this.x = shapeToCopy.getX();
        this.y = shapeToCopy.getY();
    }

    static Shape getRandomBound() {
        int x = Config.RANDOMIZER.apply(Config.ARENA_WIDTH);
        int y = Config.RANDOMIZER.apply(Config.ARENA_HEIGHT);

        return new Shape(x, y, Config.HIT_BOX);


    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    private int getRadius() {
        return radius;
    }

    void moveTo(int distance, int angle) {
        this.x += distance * Math.cos(Math.toRadians(angle));
        this.y += distance * Math.sin(Math.toRadians(angle));
    }

    public boolean intersectWith(Shape otherShape) {
        return Math.sqrt(Math.pow(this.x - otherShape.getX(), 2) + Math.pow(this.y - otherShape.getY(), 2)) <= this.radius + otherShape.getRadius();
    }

    public Shape cloneShape() {
        return new Shape(this);
    }
}