package game.controller;

import game.controller.model.Controller;
import game.core.GameWorld;
import game.gameobject.unit.model.Unit;
import game.util.GameOptions;
import game.util.Logger;

/**
 * Created by Эдуард on 03.07.2014.
 */
public class BotController implements Controller {
    private final GameWorld gameWorld;
    private final Unit unitUnderControl;

    private static final Logger LOGGER = Logger.getLogger(BotController.class);
    private static final String TAG = "AI Thoughts";

    private boolean movingToPoint = false;
    private int targetX = -1;
    private int targetY = -1;
    private boolean inBattle = false;
    private float bellicosity = 1;
    private Unit enemy = null;

    private final int fault = GameOptions.TILE_SIZE/10;

    private long nextAiTime = System.currentTimeMillis() + GameOptions.AI_DELAY_MILLISECONDS;

    public Unit getUnitUnderControl() {
        return unitUnderControl;
    }

    @Override
    public void update() {
        if(System.currentTimeMillis()>=nextAiTime){
            nextAiTime=System.currentTimeMillis()+ GameOptions.AI_DELAY_MILLISECONDS;
            LOGGER.log(TAG, "Думаю");
            if(getUnitUnderControl().isAlive()){
                LOGGER.log(TAG, "Я живой!");
            //Трупы не думают
             if(inBattle){
                  updateEmemy();
                  if(enemy!=null) {
                      int x = enemy.getRealX();
                      int y = enemy.getRealY();

                      if (((x - getX()) * (x - getX()) + (y - getY()) * (y - getY())) >= fault * fault) {
                          if (!(((x - fault) < getX()) && (getX() < (x + fault)))) {
                              movingToPoint = true;
                              targetX = x;
                          }
                          if (!(((y - fault) < getY()) && (getY() < (y + fault)))) {
                              movingToPoint = true;
                              targetY = y;
                          }
                      }else{
                          //TODO: FIRE!
                          LOGGER.log(TAG, "Атакуем типа: "+((x - getX()) * (x - getX()) + (y - getY()) * (y - getY())));
                      }
                  }
             }else if (bellicosity == 1){
                 findEmemy();
             }
             if(movingToPoint){
                //TODO:ну тут должен быть крутой алгоритм поиска пути
                 LOGGER.log(TAG, "Иду до точки!");
                if(getX() < (targetX - fault)) {
                    getUnitUnderControl().setSpeedX(1);
                } else if (getUnitUnderControl().getRealX() > (targetX + fault)) {
                    getUnitUnderControl().setSpeedX(-1);
                } else {
                    getUnitUnderControl().setSpeedX(0);
                }

                if(getY() < (targetY - fault)) {
                    getUnitUnderControl().setSpeedY(1);
                } else if (getY() > (targetY + fault)) {
                    getUnitUnderControl().setSpeedY(-1);
                } else {
                    getUnitUnderControl().setSpeedY(0);
                }
            }else{

            }
            } else {
            //TODO: Тут нужно установить удаление контроллера
            }
        }

    }

    private void updateEmemy() {
        if(enemy==null && bellicosity == 1){
            findEmemy();
        }
        if(enemy==null){
            inBattle = false;
        }
    }

    private void findEmemy() {
        LOGGER.log(TAG, "Поиск врага!");
        inBattle = true;
        enemy =  gameWorld.getPlayer();
    }

    private int getX() {
        return getUnitUnderControl().getRealX();
    }

    private int getY() {
        return getUnitUnderControl().getRealY();
    }

    public BotController(final Unit unitUnderControl) {
       this.unitUnderControl = unitUnderControl;
       gameWorld = unitUnderControl.getGameWorld();
    }
}
