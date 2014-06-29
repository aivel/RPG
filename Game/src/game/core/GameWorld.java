package game.core;

import game.gameobject.model.GameObject;
import game.level.model.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semyon Danilov on 27.06.2014.
 */
public class GameWorld {

    private List<GameObject> gameObjects = null;

    private Level currentLevel;

    public GameWorld() {
        this.gameObjects = new ArrayList<>();
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void addGameObject(final GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void addGameObjects(final List<? extends GameObject> gameObjects) {
        this.gameObjects.addAll(gameObjects);
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void removeGameObject(final GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    public boolean isOccupied(final int x, final int y) {
        for (final GameObject gameObject : gameObjects) {
            if (gameObject.getX() == x && gameObject.getY() == y) {
                return true;
            }
        }

        return !getCurrentLevel().getLevelMap().getTile(x, y).isVisitable();
    }

}