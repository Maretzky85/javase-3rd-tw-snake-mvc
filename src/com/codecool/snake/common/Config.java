package com.codecool.snake.common;

import java.util.Random;
import java.util.function.Function;

/**
 * Config class for Snake App
 * <p>
 * FRAME_RATE - sets how many frames per second are generated by model
 * ARENA_WIDTH - sets application window width
 * ARENA_HEIGHT - sets application window height
 * ARENA_WIDTH - sets application window width
 * INITIAL_SNAKE_SIZE - sets how many parts snake entity have at the app start
 * SNAKE_SPEED - sets speed for snake entity (how many points (pixels) snake move for one frame) multiplied by angle
 * ENEMY_SPEED - sets speed for enemy entity
 * POWERUP_SPEED - sets speed for powerup entity
 * ROTATE_SPEED - sets how fast snake is to rotate (degree per frame)
 * HIT_BOX - hit box for theoretical model //TODO
 */
public class Config {
    public static final int FRAME_RATE = 30;
    public static final int ARENA_WIDTH = 1000;
    public static final int ARENA_HEIGHT = 700;
    public static final int INITIAL_SNAKE_SIZE = 20;
    public static final int SNAKE_SPEED = 10;
    public static final int ENEMY_SPEED = 2;
    public static final int POWERUP_SPEED = 1;
    public static final int ROTATE_SPEED = 10;
    public static final int HIT_BOX = 20;

    public static final Function<Integer, Integer> RANDOMIZER = (egdeNumber) -> new Random().nextInt(egdeNumber);
}