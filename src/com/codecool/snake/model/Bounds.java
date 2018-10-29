package com.codecool.snake.model;

import com.codecool.snake.common.Config;

public class Bounds {
    private double x;
    private double y;
    private int radius;

    public Bounds(double x, double y, int radius){
        this.radius = radius;
        this.x = x;
        this.y = y;
    }

    public Bounds(Bounds boundsToCopy){
        this.radius = boundsToCopy.getRadius();
        this.x = boundsToCopy.getX();
        this.y = boundsToCopy.getY();
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public int getRadius() {
        return radius;
    }

    public boolean intersectWith(Bounds otherBounds){
        return Math.sqrt(Math.pow(this.x - otherBounds.getX(), 2) + Math.pow(this.y - otherBounds.getY(), 2)) <= this.radius + otherBounds.getRadius();
    }

    public void moveTo(int distance, int angle){
        this.x += distance*Math.cos(Math.toRadians(angle));
        this.y += distance*Math.sin(Math.toRadians(angle));
    }

    public Bounds cloneBound() {
        return new Bounds(this);
    }

    public static Bounds getRandomBound() {
        int x = Config.RANDOMIZER.apply(Config.ARENA_WIDTH);
        int y = Config.RANDOMIZER.apply(Config.ARENA_HEIGHT);

        return new Bounds(x, y, Config.HIT_BOX);


    }
}