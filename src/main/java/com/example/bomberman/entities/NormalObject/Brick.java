package com.example.bomberman.entities.NormalObject;

import com.example.bomberman.entities.Entity;
import com.example.bomberman.entities.Item.Item;
import com.example.bomberman.graphics.Sprite;
import javafx.scene.image.Image;

public class Brick extends Entity {
    private Item item = null;
    private boolean damaged = false;
    private boolean done = false;
    private int deathCountDown = 50;

    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    public Brick(int x, int y, Image img, Item item) {
        super(x, y, img);
        this.item = item;
    }

    public boolean isDamaged() {
        return damaged;
    }

    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }


    public Item getItem() {
        return item;
    }

    public void collapsingImg() {
        if (deathCountDown == 0) {
            this.img = null;
            setDone(true);
        } else {
            this.img = Sprite
                    .bombExplodeSprite(Sprite.brick_exploded2, Sprite.brick_exploded1, Sprite.brick_exploded, deathCountDown)
                    .getFxImage();
            deathCountDown--;
        }
    }

    @Override
    public void update() {
        if (this.isDamaged()) {
            collapsingImg();
        }
    }
}