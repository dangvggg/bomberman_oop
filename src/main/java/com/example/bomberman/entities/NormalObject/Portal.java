package com.example.bomberman.entities.NormalObject;

import com.example.bomberman.BombermanGame;
import com.example.bomberman.SoundEffect;
import com.example.bomberman.entities.Bomberman.Bomber;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.graphics.Sprite;
import javafx.scene.image.Image;

import java.util.ConcurrentModificationException;

import static com.example.bomberman.BombermanGame.entities;

public class Portal extends Entity {
    public Portal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public static boolean levelUp = false;


    public static boolean isLevelUp() {
        return levelUp;
    }

    public void setLevelUp(boolean levelUp) {
        this.levelUp = levelUp;
    }


    @Override
    public void update() {
        try{
            for (Entity o : entities) {
                if (o instanceof Bomber) {
                    int oX = o.getX() / Sprite.SCALED_SIZE, x = this.getX() / Sprite.SCALED_SIZE;
                    int oY = o.getY() / Sprite.SCALED_SIZE, y = this.getY() / Sprite.SCALED_SIZE;
                    if (oX == x && oY == y) {
                        levelUp = true;
                        if(levelUp == true && BombermanGame.numberOfEnemy < 0)
                        {
                            BombermanGame.INSTANCE.changeLevel();
                            setLevelUp(false);
                            ((Bomber) o).setBombLimit(1);
                            ((Bomber) o).setBombRange(1);
                            ((Bomber) o).setSpeed(1);
                            ((Bomber) o).setImagespeed(32);
                            SoundEffect.playerEatItem();
                        }
                    }
                }
            }
        }
        catch(ConcurrentModificationException e){

        }
    }
}