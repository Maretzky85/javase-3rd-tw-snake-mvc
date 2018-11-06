package com.codecool.snake.view;

import com.codecool.snake.common.Config;
import com.codecool.snake.controller.GameController;
import com.codecool.snake.model.Entity;
import com.codecool.snake.model.common.EntityObserver;
import com.codecool.snake.model.common.GameEntityType;
import com.codecool.snake.model.common.ModelObserver;
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


/**
 * This is view class for Snake app
 * holds images of entities and node on scene
 * also holds instance of scene
 */
public class GameView extends Pane implements ModelObserver {
    private static HashMap<GameEntityType, Image> costumes;
    private HashMap<String, Group> entitiesOnScene = new HashMap<>();
    private Scene scene;

    /**
     * This constructor takes JavaFx stage and attaches itself to it,
     * also loads costiume for entites in game.
     *
     * @param primaryStage - JavaFX stage
     */
    public GameView(Stage primaryStage){
        primaryStage.setTitle("Snake");
        attachViewToStage(primaryStage);
        loadCostumes();
    }

    /**
     * Creates new visual representation of entities on demand,
     * associates with their model representation,
     * save their instance to future use.
     *
     * @param createdEntity - Entity based object
     */
    @Override
    public void updateOnSpawn(Entity createdEntity) {
        Group entity = new Group();
        switch (createdEntity.getEntityType()) {
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
        createdEntity.addObserver((EntityObserver) entity);
        entitiesOnScene.put(createdEntity.toString(), entity);
        getChildren().add(entity);
    }

    /**
     * Remove visual representation of entities from scene,
     * and theirs saved instance.
     *
     * @param destroyedEntity - Entity based object
     */
    @Override
    public void updateOnDestroy(Entity destroyedEntity) {
        Group entity = entitiesOnScene.get(destroyedEntity.toString());

        entitiesOnScene.remove(entity);
        Platform.runLater(() -> getChildren().remove(entity));
    }

    /**
     * Creates new scene on which view will be drawing,
     * attaches it to scene and makes it visible.
     *
     * @param stage - JavaFx stage
     */
    private void attachViewToStage(Stage stage){
        scene = new Scene(this, Config.ARENA_WIDTH, Config.ARENA_HEIGHT);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Attaches scene to already created constroller,
     * by attaches key events and window events.
     *
     * @param gameController - GameController which controls game
     */
    public void attachInputToController(GameController gameController) {
        scene.setOnKeyPressed(gameController::handleOnKeyPressed);
        scene.setOnKeyReleased(gameController::handleOnKeyReleased);
        scene.getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> gameController.handleOnAppClose());
    }

    /**
     * Loads graphics for new all entities of game.
     */
    private void loadCostumes(){
        costumes = new HashMap<>();
        costumes.put(GameEntityType.SNAKE, new Image("snake_head.png"));
        costumes.put(GameEntityType.ENEMY, new Image("simple_enemy.png"));
        costumes.put(GameEntityType.POWERUP, new Image("powerup_berry.png"));
        costumes.put(GameEntityType.SNAKETAIL, new Image("snake_body.png"));
    }
}
