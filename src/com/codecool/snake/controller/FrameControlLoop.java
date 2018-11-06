package com.codecool.snake.controller;

import static com.codecool.snake.common.Config.FRAME_RATE;

/**
 * This class is for controlling how many frames is generated for second
 * This is clock for theoretical model, that run selected functions in requested frequency
 */
public class FrameControlLoop extends Thread {

    private Runnable updater;

    private boolean isRunning = false;

    private int tics = 0; //For FPS Debugging

    private long initialTime = System.currentTimeMillis(), //time for Loop Control
            startTime = System.currentTimeMillis(), //initial time for FPS console logging
            timeFrame = 1000 / FRAME_RATE, //time in milliseconds for one loop;
            timeCounterMs = 0, //milliseconds counter
            currentTime = System.currentTimeMillis();

    FrameControlLoop(Runnable updater) {
        this.updater = updater;
    }

    /**
     * Run function for starting loop control
     * checks time between current time and start time, waits for rest ms,
     * if time between current and start time is greater than time frame, than runs requied command.
     */
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
            try {
                Thread.sleep(timeFrame - timeCounterMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
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

    /**
     * function for killing FrameControlLoop
     */
    void toggleLoopState() {
        isRunning = !isRunning;
    }
}
