package com.example.bomberman.entities.Item;

import com.example.bomberman.SoundEffect;
import com.example.bomberman.entities.Bomberman.Bomber;
import com.example.bomberman.entities.Enemy.Enemy;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.graphics.Sprite;
import javafx.scene.image.Image;

import static com.example.bomberman.BombermanGame.entities;


public class SpeedItem extends Item {

    public SpeedItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        for (Entity o : entities) {
            if (o instanceof Bomber) {
                int X = o.getX() / Sprite.SCALED_SIZE,  x = this.getX() / Sprite.SCALED_SIZE;
                int Y = o.getY() / Sprite.SCALED_SIZE,  y = this.getY() / Sprite.SCALED_SIZE;
                if(X == x && Y == y) {
                    ((Bomber) o).setSpeed(2);
                    ((Bomber) o).setImagespeed(12);
                    SoundEffect.playerEatItem();
                    this.img = null;
                }
            }
        }
    }
}
