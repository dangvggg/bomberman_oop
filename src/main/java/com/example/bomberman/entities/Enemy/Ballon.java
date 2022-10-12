package com.example.bomberman.entities.Enemy;

import com.example.bomberman.entities.CurrentImage;
import com.example.bomberman.graphics.Sprite;
import javafx.scene.image.Image;


public class Ballon extends Enemy {

    private CurrentImage currentImage = new CurrentImage();

    public Ballon(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        speed = 1;
        life = 1;
        throughWall = false;
    }

    @Override
    public boolean moveLeft() {
        img = Sprite
                .movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, currentImage.left)
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
                .movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, currentImage.down)
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
                .movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, currentImage.right)
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
                .movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, currentImage.up)
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
            this.img = null;
        } else {
            this.img = Sprite
                    .movingSprite(Sprite.mob_dead2, Sprite.mob_dead1, Sprite.balloom_dead, deathCountDown)
                    .getFxImage();
            deathCountDown--;
        }
    }
}