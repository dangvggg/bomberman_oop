package com.example.bomberman.entities.Enemy;

import com.example.bomberman.entities.CurrentImage;
import com.example.bomberman.graphics.Sprite;
import javafx.scene.image.Image;

public class Doll extends Enemy{

    private CurrentImage currentImage = new CurrentImage();

    public Doll(int x, int y, Image img) {
        super(x, y, img);
        life = 2;
        speed = 2;
        throughWall = true;
    }

    @Override
    public boolean moveLeft() {
        img = Sprite
                .movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, currentImage.left)
                .getFxImage();
        if (currentImage.left == 8) {
            currentImage.left = 0;
        } else {
            currentImage.left++;
        }
        return super.moveLeft();
    }

    @Override
    public boolean moveDown() {
        img = Sprite
                .movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, currentImage.down)
                .getFxImage();
        if (currentImage.down == 8) {
            currentImage.down = 0;
        } else {
            currentImage.down++;
        }
        return super.moveDown();
    }


    @Override
    public boolean moveRight() {
        img = Sprite
                .movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, currentImage.right)
                .getFxImage();
        if (currentImage.right == 8) {
            currentImage.right = 0;
        } else {
            currentImage.right++;
        }
        return super.moveRight();
    }

    @Override
    public boolean moveUp() {
        img = Sprite
                .movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, currentImage.up)
                .getFxImage();
        if (currentImage.up == 8) {
            currentImage.up = 0;
        } else {
            currentImage.up++;
        }
        return super.moveUp();
    }

    @Override
    public void die() {
        if (deathCountDown == 0) {
            super.die();
        } else {
            this.img = Sprite
                    .movingSprite(Sprite.mob_dead2, Sprite.mob_dead1, Sprite.doll_dead, deathCountDown)
                    .getFxImage();
            deathCountDown--;
        }
    }

    @Override
    public void update() {
        if (life == 1) {
            throughWall = false;
        }
        super.update();
    }
}
