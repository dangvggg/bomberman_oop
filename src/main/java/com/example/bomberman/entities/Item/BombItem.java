package com.example.bomberman.entities.Item;

import com.example.bomberman.entities.Bomberman.Bomber;
import com.example.bomberman.entities.Enemy.Enemy;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.graphics.Sprite;
import javafx.scene.image.Image;

import static com.example.bomberman.BombermanGame.entities;


public class BombItem extends Item {

    public BombItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        for (Entity o : entities) {
            if (o instanceof Bomber) {
                int oX = o.getX() / Sprite.SCALED_SIZE,  x = this.getX() / Sprite.SCALED_SIZE;
                int oY = o.getY() / Sprite.SCALED_SIZE,  y = this.getY() / Sprite.SCALED_SIZE;
                if(oX == x && oY == y) {
                    ((Bomber) o).setBombLimit(2);
                    this.img = null;
                }
            }
        }
    }
}