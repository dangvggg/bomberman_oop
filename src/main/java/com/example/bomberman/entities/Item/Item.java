package com.example.bomberman.entities.Item;

import com.example.bomberman.entities.Entity;
import javafx.scene.image.Image;

import java.io.IOException;

public class Item extends Entity {
    private boolean isCollision = false;

    public Item(int x, int y, Image img) {
        super(x, y, img);
    }


    //Collision Bomber

//    public boolean collision(Entity entities) {
//        if (this.rec.intersects(entities.rec.getLayoutBounds())) {
//            return true;
//        }
//        return false;
//    }

    @Override
    public void update() {

    }
}

