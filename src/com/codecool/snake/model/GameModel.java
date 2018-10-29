package com.codecool.snake.model;

import com.codecool.snake.common.Config;
import com.codecool.snake.model.common.GameEntityType;
import com.codecool.snake.model.common.ObservableModel;
import com.codecool.snake.model.entities.EnemyEntity;
import com.codecool.snake.model.entities.PowerupEntity;
import com.codecool.snake.model.entities.SnakeEntity;
import javafx.scene.input.KeyEvent;

import java.util.*;

public class GameModel extends ObservableModel {
    private final int FULL_ANGLE = 360;

    private List<Entity> gameEntities;

    public GameModel() {
        gameEntities = new ArrayList<>();
    }

    public void firstSpawn() {
        spawnGameObject(GameEntityType.SNAKE);

        for(int i = 0; i < 3; ++i) {
            spawnGameObject(GameEntityType.ENEMY);
            spawnGameObject(GameEntityType.POWERUP);
        }
    }

    public void updateModel(){
        cleanDeathEntities();
        checkForCollision();
        moveAll();
    }

    private void cleanDeathEntities() {
        ListIterator<Entity> entitiesIterator = gameEntities.listIterator();
        while(entitiesIterator.hasNext()){
            Entity entity = entitiesIterator.next();
            if(!entity.isAlive()){
                entitiesIterator.remove();
                removeGameObject(entity);
            }
        }
    }

    private void checkForCollision() {

        // filter list
        ArrayList<Entity> entities = new ArrayList<>();
        ArrayList<SnakeEntity> snakes = new ArrayList<>();

        for(Entity entity : gameEntities) {
            switch (entity.getEntityType()) {
                case SNAKE:
                    snakes.add((SnakeEntity) entity);
                    break;

                default:
                    entities.add(entity);
            }
        }

        for(SnakeEntity snake: snakes) {
            if(isOutOfArenaBounds(snake)) {
                snake.death();
                continue;
            }

            for(Entity entity: entities) {
                if(isOutOfArenaBounds(entity)) {
                    entity.death();
                    continue;
                }
                else if(!snake.isCollideWith(entity)) continue;

                snake.interactWith(entity);
            }
        }
    }

    private void moveAll() {

        for (Entity entity:  gameEntities) {
            entity.movement();
        }
    }

    private void spawnGameObject(GameEntityType type) {
        Entity entity = null;

        switch (type) {
            case SNAKE:
                entity = new SnakeEntity(Config.INITIAL_SNAKE_SIZE);
                break;
            case ENEMY:
                entity = new EnemyEntity();
                entity.setSpeed(Config.ENEMY_SPEED);
                break;
            case POWERUP:
                entity = new PowerupEntity();
                entity.setSpeed(Config.POWERUP_SPEED);
                break;
            case SNAKETAIL:
                break;
            default:
                entity = new EnemyEntity();
                entity.setSpeed(Config.ENEMY_SPEED);
                break;
        }

        if(entity != null) {
            entity.setBounds(Bounds.getRandomBound());
            entity.setAngle(Config.RANDOMIZER.apply(FULL_ANGLE));

            gameEntities.add(entity);
            notifyAboutSpawn(entity);
        }
    }

    private void removeGameObject(Entity entityToRemove) {
        notifyAboutDestroy(entityToRemove);
    }

    private boolean isOutOfArenaBounds(Entity entity) {
        Bounds bound = entity.getBounds();

        return  bound.getX() < 0 || Config.ARENA_WIDTH < bound.getX() ||
                bound.getY() < 0 || Config.ARENA_HEIGHT < bound.getY();
    }

    public void interpretPressEvent(KeyEvent event) {
        for (Entity gameEntity : gameEntities)
            if (gameEntity.getEntityType().equals(GameEntityType.SNAKE)) {
                ((SnakeEntity) gameEntity).interpretPressEvent(event);
            }
    }

    public void interpretReleaseEvent() {
        for (Entity gameEntity : gameEntities)
            if (gameEntity.getEntityType().equals(GameEntityType.SNAKE)) {
                ((SnakeEntity) gameEntity).interpretReleaseEvent();
            }
    }
}
