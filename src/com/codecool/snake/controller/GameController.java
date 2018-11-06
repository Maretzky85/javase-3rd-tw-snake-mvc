package com.codecool.snake.controller;

import com.codecool.snake.model.GameModel;
import com.codecool.snake.view.GameView;
import javafx.scene.input.KeyEvent;

/**
 * This is controller class for Snake app
 * holds view and model instances and allowing communication between them
 * holds FrameControlLoop for controlling game update speed //TODO
 */

public class GameController {
    private GameView view;
    private GameModel model;
    private FrameControlLoop loop = new FrameControlLoop(this::updateModel);

    /**
     * This constructor takes gameView and gameModel instances
     * <p>
     * passes ability to handle to events like key press or app close from JavaFX application Thread
     * <p>
     * initiates game start by spawning all entities in model
     * <p>
     * sets FrameLoopControl Thread to be daemon //TODO
     *
     * @param model - instance of GameModel
     * @param view  - instance of GameView that is JavaFX application
     */
    public GameController(GameModel model, GameView view) {
        this.view = view;
        this.model = model;

        view.attachInputToController(this);
        model.firstSpawn();

        loop.setDaemon(true);
    }

    /**
     * Starts FrameControlLoop in new Thread by executing run() function
     */
    public void startLoop() {
        loop.start();
    }

    /**
     * Passes key press event from JavaFX application to handle by GameModel
     *
     * @param event JavaFX key event
     */
    public void handleOnKeyPressed(KeyEvent event) {
        model.interpretPressEvent(event);
    }


    /**
     * Passes key release event from JavaFX application to handle by GameModel
     *
     * @param event JavaFX key event
     */
    public void handleOnKeyReleased(KeyEvent event) {
        model.interpretReleaseEvent();
    }


    /**
     * Allows GameModel and GameLoop for gentle shut-down
     */
    public void handleOnAppClose() {
        loop.toggleLoopState();
    }


    /**
     * This method calls GameModel update function to calculate next frame
     */
    private void updateModel() {
        model.updateModel();
    }

}
