package com.codecool.snake.controller;

import com.codecool.snake.model.GameModel;
import com.codecool.snake.view.GameView;
import javafx.scene.input.KeyEvent;

public class GameController {
    private GameView view;
    private GameModel model;

    private FrameControlLoop loop = new FrameControlLoop(this::updateModel);

    public GameController(GameModel model, GameView view){
        this.view = view;
        this.model = model;

        view.attachInputToController(this);
        model.firstSpawn();

        loop.setDaemon(true);
    }


    public void startLoop() {
        loop.start();
    }

    public void handleOnKeyPressed(KeyEvent event){
        model.interpretPressEvent(event);
    }
    public void handleOnKeyReleased(KeyEvent event){
        model.interpretReleaseEvent();
    }
    public void handleOnAppClose() {
        loop.toggleLoopState();
    }

    private void updateModel() {
        model.updateModel();
    }

}
