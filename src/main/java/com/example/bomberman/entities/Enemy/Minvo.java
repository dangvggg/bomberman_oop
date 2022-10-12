package com.example.bomberman.entities.Enemy;

import com.example.bomberman.entities.CurrentImage;
import com.example.bomberman.graphics.Sprite;
import javafx.scene.image.Image;

public class Minvo extends Enemy{
    private CurrentImage currentImage = new CurrentImage();

    public Minvo(int x, int y, Image img) {
        super(x, y, img);
        life = 2;
        throughWall = false;
    }

    @Override
    public boolean moveLeft() {
        speed = 2;
        img = Sprite
                .movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, currentImage.left)
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
        speed = 1;
        img = Sprite
                .movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, currentImage.right)
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
        speed = 2;
        img = Sprite
                .movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, currentImage.right)
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
        speed = 1;
        img = Sprite
                .movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, currentImage.left)
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
                    .movingSprite(Sprite.mob_dead2, Sprite.mob_dead1, Sprite.minvo_dead, deathCountDown)
                    .getFxImage();
            deathCountDown--;
        }
    }

}
