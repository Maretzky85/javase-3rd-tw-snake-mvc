package com.codecool.snake.app;

import com.codecool.snake.controller.GameController;
import com.codecool.snake.model.GameModel;
import com.codecool.snake.view.GameView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This is entry point for JavaFX Snake app - a remake of classic Snake using MVC methodology
 */

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * JavaFx required method for starting app
     * GameView - sets and creates JavaFX application window and attaches updaters
     * GameView is only for visual representation of theoretical model (GameModel)
     * <p>
     * GameModel - creates instance of GameModel that represents a mathematical
     * (theoretical) representation of game grid and entities
     * <p>
     * GameController - Create instance of Controller that controls game parameters
     * and allows communication between model and view
     *
     * @param primaryStage JavaFx default
     */
    @Override
    public void start(Stage primaryStage) {
        GameView view = new GameView(primaryStage);
        GameModel model = new GameModel();
        model.addObserver(view);

        GameController gameController = new GameController(model, view);
        gameController.startLoop();
    }

}
