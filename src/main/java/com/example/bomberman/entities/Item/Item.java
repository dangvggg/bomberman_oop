package com.example.bomberman.entities.Item;

import com.example.bomberman.entities.Entity;
import javafx.scene.image.Image;

import java.io.IOException;

public abstract class Item extends Entity {
    public Item(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }
}

