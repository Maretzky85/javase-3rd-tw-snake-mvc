package com.codecool.snake.controller;

import static com.codecool.snake.common.Config.FRAME_RATE;

public class FrameControlLoop extends Thread implements Runnable {

    private Runnable updater;

    private boolean isRunning = false;

    private int tics = 0; //For FPS Debugging

    private long initialTime  = System.currentTimeMillis(), //time for Loop Control
            startTime  = System.currentTimeMillis(), //initial time for FPS drawing
            timeFrame = 1000/ FRAME_RATE, //time in milliseconds for one loop;
            timeCounterMs = 0, //milliseconds counter
            currentTime  = System.currentTimeMillis();

    public FrameControlLoop(Runnable updater) {
        this.updater = updater;
    }

    public void run() {

        isRunning = true;
        while (isRunning){
            currentTime = System.currentTimeMillis();
            timeCounterMs += (currentTime - initialTime);
            initialTime = currentTime;

            if (timeCounterMs >= timeFrame) {
                updater.run();
                tics += 1;
                timeCounterMs = 0;
            }

            //if statement for FPS loging in console=========
            if (currentTime-startTime>1000){
                System.out.println("FPS: "+tics);
                startTime = System.currentTimeMillis();
                tics = 0;
            }

            //===============================================
        }
    }
}
