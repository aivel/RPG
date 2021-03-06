package game.gameobject;

import game.core.GameWorld;
import game.gameobject.model.GameObject;
import game.graphics.sprite.model.AbstractSprite;
import game.util.ResourceManager;

/**
 * Created by Max on 6/29/2014.
 */
public class Floor extends GameObject {
    @Override
    public void update(long deltaTime) {

    }

    public Floor(Integer x, Integer y, String spriteFileName, GameWorld gameWorld) {
        super(x, y, spriteFileName, gameWorld);

        final AbstractSprite sprite = ResourceManager.getSprite(spriteFileName, 0);
        putSprite(sprite);
        setCurrentSprite(sprite);
    }
}
