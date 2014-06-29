package game.core;

import game.gameobject.model.GameObject;

/**
 * Created by Semyon Danilov on 27.06.2014.
 */
//TODO: add pause
public class UpdateThread extends Thread {

    private Game game;
    private GameWorld gameWorld;

    private final Game.GameEngineLock renderLock;

    public UpdateThread(final Game game) {
        this.game = game;
        this.gameWorld = game.getGameWorld();
        this.renderLock = game.getRenderLock();
    }

    @Override
    public void run() {
        while (true) {
            renderLock.lock();
            try {
                update();
                game.startRender();
                renderLock.notifyCanDraw();
                renderLock.waitUntilCanUpdate();
            } finally {
                renderLock.unlock();
            }
        }
    }

    private void update() {
        for (GameObject go: gameWorld.getGameObjects()) {
            go.update();
        }
    }

}