package com.codecool.snake.model;

import com.codecool.snake.common.Config;
import com.codecool.snake.model.common.GameEntityType;
import com.codecool.snake.model.common.ObservableModel;
import com.codecool.snake.model.entities.EnemyEntity;
import com.codecool.snake.model.entities.PowerupEntity;
import com.codecool.snake.model.entities.SnakeEntity;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Class with creates model of game,
 * holds entities which are on arena
 */
public class GameModel extends ObservableModel {
    private final int FULL_ANGLE = 360;

    private List<Entity> gameEntities;

    /**
     * Creates place holder for new entities
     */
    public GameModel() {
        gameEntities = new ArrayList<>();
    }

    /**
     * Populates model with new entities
     */
    public void firstSpawn() {
        spawnGameObject(GameEntityType.SNAKE);

        for (int i = 0; i < 5; ++i) {
            spawnGameObject(GameEntityType.ENEMY);
            spawnGameObject(GameEntityType.POWERUP);
        }
    }

    /**
     * Update model by cleanup arena from death entities,
     * check if collision exist,
     * and move all entities
     */
    public void updateModel() {
        cleanDeathEntities();
        checkForCollision();
        moveAll();
    }

    /**
     * Cleanup death entities if they exist,
     */
    private void cleanDeathEntities() {
        ListIterator<Entity> entitiesIterator = gameEntities.listIterator();
        while (entitiesIterator.hasNext()) {
            Entity entity = entitiesIterator.next();
            if (!entity.isAlive()) {
                entitiesIterator.remove();
                removeGameObject(entity);
            }
        }
    }

    /**
     *  Check if collision exist,
     *  Compares Snakes with other entities
     */
    private void checkForCollision() {

        // filter list
        ArrayList<Entity> entities = new ArrayList<>();
        ArrayList<SnakeEntity> snakes = new ArrayList<>();

        for (Entity entity : gameEntities) {
            switch (entity.getEntityType()) {
                case SNAKE:
                    snakes.add((SnakeEntity) entity);
                    break;

                default:
                    entities.add(entity);
            }
        }

        for (SnakeEntity snake : snakes) {
            if (isOutOfArenaBounds(snake)) {
                snake.death();
                continue;
            }

            for (Entity entity : entities) {
                if (isOutOfArenaBounds(entity)) {
                    entity.death();
                    continue;
                } else if (!snake.isCollideWith(entity)) continue;

                snake.interactWith(entity);
            }
        }
    }

    /**
     *  Move all entities
     */
    private void moveAll() {

        for (Entity entity : gameEntities) {
            entity.movement();
        }
    }

    /**
     * Creates entities based on request,
     * append them to arena,
     * and notify about that observators
     *
     * @param type - request for entity
     */
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

        if (entity != null) {
            entity.setBounds(Shape.getRandomBound());
            entity.setAngle(Config.RANDOMIZER.apply(FULL_ANGLE));

            gameEntities.add(entity);
            notifyAboutSpawn(entity);
        }
    }

    /**
     * Remove entities from arena and notify about that observers
     *
     * @param entityToRemove - Entity based object to remove
     */
    private void removeGameObject(Entity entityToRemove) {
        notifyAboutDestroy(entityToRemove);
    }

    /**
     * Check if entity is not in bounds of arena
     *
     * @param entity - Entity based object to check
     * @return - TRUE if is not in bounds, otherwise False
     */
    private boolean isOutOfArenaBounds(Entity entity) {
        Shape bound = entity.getShape();

        return bound.getX() < 0 || Config.ARENA_WIDTH < bound.getX() ||
                bound.getY() < 0 || Config.ARENA_HEIGHT < bound.getY();
    }

    /**
     * Interprets press event by passing it futher to snakes entities
     *
     * @param event - event occured on scene
     */
    public void interpretPressEvent(KeyEvent event) {
        for (Entity gameEntity : gameEntities)
            if (gameEntity.getEntityType().equals(GameEntityType.SNAKE)) {
                ((SnakeEntity) gameEntity).interpretPressEvent(event);
            }
    }

    /**
     *  Interprets release event by passing it futher to snakes entities
     */
    public void interpretReleaseEvent() {
        for (Entity gameEntity : gameEntities)
            if (gameEntity.getEntityType().equals(GameEntityType.SNAKE)) {
                ((SnakeEntity) gameEntity).interpretReleaseEvent();
            }
    }
}
