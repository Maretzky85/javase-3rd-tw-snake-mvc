package com.codecool.snake.common;

import java.util.Random;
import java.util.function.Function;

public class Config{
        public static final int FRAME_RATE = 30;
        public static final int ARENA_WIDTH = 1000;
        public static final int ARENA_HEIGHT = 700;
        public static final int INITIAL_SNAKE_SIZE = 20;
        public static final int SNAKE_SPEED = 10;
        public static final int ENEMY_SPEED = 1;
        public static final int POWERUP_SPEED = 0;
        public static final int ROTATE_SPEED = 5;
        public static final int HIT_BOX = 20;

        public static final Function<Integer, Integer> RANDOMIZER = (egdeNumber) -> new Random().nextInt(egdeNumber);
    }