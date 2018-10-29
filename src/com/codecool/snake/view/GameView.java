package com.codecool.snake.view;

import com.codecool.snake.common.Config;
import com.codecool.snake.model.common.EntityObserver;
import com.codecool.snake.model.common.GameEntityType;
import com.codecool.snake.model.common.ModelObserver;
import com.codecool.snake.controller.GameController;
import com.codecool.snake.model.Entity;
import com.codecool.snake.view.entities.EntityView;
import com.codecool.snake.view.entities.SnakeView;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.HashMap;


public class GameView extends Pane implements ModelObserver {
    private static HashMap<GameEntityType, Image> costumes;
    private HashMap<String, Group> entitiesOnScene = new HashMap<>();
    private Scene scene;

    public GameView(Stage primaryStage){
        attachViewToStage(primaryStage);
        loadCostumes();
    }

    @Override
    public void updateOnSpawn(Entity spawnEntity) {
        Group entity = new Group();
        switch (spawnEntity.getEntityType()){
            case ENEMY:
                entity = new EntityView(costumes.get(GameEntityType.ENEMY));
                break;
            case POWERUP:
                entity = new EntityView(costumes.get(GameEntityType.POWERUP));
                break;
            case SNAKE:
                entity = new SnakeView(costumes.get(GameEntityType.SNAKE), costumes.get(GameEntityType.SNAKETAIL));
                break;
        }
        spawnEntity.addObserver((EntityObserver) entity);
        entitiesOnScene.put(spawnEntity.toString(), entity);
        getChildren().add(entity);
    }

    @Override
    public void updateOnDestroy(Entity destroyedEntity) {
        Group entity = entitiesOnScene.get(destroyedEntity.toString());

        entitiesOnScene.remove(entity);
        Platform.runLater(()->getChildren().remove(entity));
    }

    private void attachViewToStage(Stage stage){
        scene = new Scene(this, Config.ARENA_WIDTH, Config.ARENA_HEIGHT);

        stage.setScene(scene);
        stage.show();
    }

    public void attachInputToController(GameController gameController){
        scene.setOnKeyPressed(gameController::handleOnKeyPressed);
        scene.setOnKeyReleased(gameController::handleOnKeyReleased);
        scene.getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> gameController.handleOnAppClose());
    }

    private void loadCostumes(){
        costumes = new HashMap<>();
        costumes.put(GameEntityType.SNAKE, new Image("snake_head.png"));
        costumes.put(GameEntityType.ENEMY, new Image("simple_enemy.png"));
        costumes.put(GameEntityType.POWERUP, new Image("powerup_berry.png"));
        costumes.put(GameEntityType.SNAKETAIL, new Image("snake_body.png"));
    }
}
