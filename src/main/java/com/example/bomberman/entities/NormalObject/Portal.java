package com.example.bomberman.entities.NormalObject;

import com.example.bomberman.BombermanGame;
import com.example.bomberman.entities.Bomberman.Bomber;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.graphics.Sprite;
import javafx.scene.image.Image;

import static com.example.bomberman.BombermanGame.entities;

public class Portal extends Entity {
    public Portal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public static boolean LevelUp = false;


    public static boolean isLevelUp() {
        return LevelUp;
    }

    public void setLevelUp(boolean levelUp) {
        LevelUp = levelUp;
    }


    @Override
    public void update() {
        for (Entity o : entities) {
            if (o instanceof Bomber) {
                int oX = o.getX() / Sprite.SCALED_SIZE, x = this.getX() / Sprite.SCALED_SIZE;
                int oY = o.getY() / Sprite.SCALED_SIZE, y = this.getY() / Sprite.SCALED_SIZE;
                if (oX == x && oY == y && BombermanGame.numberOfEnemy == 0) {
                    LevelUp = true;
                    BombermanGame.INSTANCE.changeLevel();
                }
            }
        }
    }
}