package com.example.bomberman.entities.Enemy;

import com.example.bomberman.BombermanGame;
import com.example.bomberman.KeyInput;
import com.example.bomberman.entities.CurrentImage;
import com.example.bomberman.graphics.Sprite;
import javafx.scene.image.Image;

public class Ghost extends Enemy {

    public static KeyInput keyInput = new KeyInput();

    private CurrentImage currentImage = new CurrentImage();

    public Ghost(int x, int y, Image img) {
        super(x, y, img);
        speed = 2;
        life = 2;
        throughWall = true;
    }

    @Override
    public boolean moveLeft() {
        img = Sprite
                .movingSprite(Sprite.ghost_left1, Sprite.ghost_left2, Sprite.ghost_left3, currentImage.left)
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
                .movingSprite(Sprite.ghost_right1, Sprite.ghost_right2, Sprite.ghost_right3, currentImage.right)
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
                .movingSprite(Sprite.ghost_right1, Sprite.ghost_right2, Sprite.ghost_right3, currentImage.right)
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
                .movingSprite(Sprite.ghost_left1, Sprite.ghost_left2, Sprite.ghost_left3, currentImage.left)
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
                    .movingSprite(Sprite.mob_dead2, Sprite.mob_dead1, Sprite.ghost_dead, deathCountDown)
                    .getFxImage();
            deathCountDown--;
        }
    }

    @Override
    public void update() {
        if (life == 2) {
            if (x < BombermanGame.bomberman.getX()) keyInput.left = true;
            if (x > BombermanGame.bomberman.getX()) keyInput.right = true;
            if (y < BombermanGame.bomberman.getY()) keyInput.up = true;
            if (y > BombermanGame.bomberman.getY()) keyInput.down = true;
            if (keyInput.left) {
                if (x + Sprite.SCALED_SIZE <= BombermanGame.bomberman.getX()
                        && BombermanGame.bomberman.getX() - x - Sprite.SCALED_SIZE >= 0
                        && BombermanGame.bomberman.getX() - x - Sprite.SCALED_SIZE < 2 * Sprite.SCALED_SIZE) {
                    int leg = BombermanGame.bomberman.getY() + Sprite.SCALED_SIZE;
                    int row;
                    if (leg % Sprite.SCALED_SIZE != 0) row = leg / Sprite.SCALED_SIZE;
                    else row = (leg - 2) / Sprite.SCALED_SIZE;
                    if (y < row * Sprite.SCALED_SIZE && row * Sprite.SCALED_SIZE - y >= 0 && row * Sprite.SCALED_SIZE - y <= 10)
                        y++;
                    if (y > row * Sprite.SCALED_SIZE && y - row * Sprite.SCALED_SIZE >= 0 && y - row * Sprite.SCALED_SIZE <= 10)
                        y--;
                    if (y == row * Sprite.SCALED_SIZE) {
                        keyInput.left = false;
                        direction = 2;
                    }
                }
            }
            if (keyInput.right) {
                if (x >= BombermanGame.bomberman.getX() + Sprite.SCALED_SIZE
                        && x - BombermanGame.bomberman.getX() - Sprite.SCALED_SIZE >= 0
                        && x - BombermanGame.bomberman.getX() - Sprite.SCALED_SIZE < 2 * Sprite.SCALED_SIZE) {
                    int leg = BombermanGame.bomberman.getY() + Sprite.SCALED_SIZE;
                    int row;
                    if (leg % Sprite.SCALED_SIZE != 0) row = leg / Sprite.SCALED_SIZE;
                    else row = (leg - 2) / Sprite.SCALED_SIZE;
                    if (y < row * Sprite.SCALED_SIZE && row * Sprite.SCALED_SIZE - y >= 0 && row * Sprite.SCALED_SIZE - y <= 10)
                        y++;
                    if (y > row * Sprite.SCALED_SIZE && y - row * Sprite.SCALED_SIZE >= 0 && y - row * Sprite.SCALED_SIZE <= 10)
                        y--;
                    if (y == row * Sprite.SCALED_SIZE) {
                        keyInput.right = false;
                        direction = 1;
                    }
                }
            }
            if (keyInput.up) {
                if (y + Sprite.SCALED_SIZE <= BombermanGame.bomberman.getY()
                        && BombermanGame.bomberman.getY() - y - Sprite.SCALED_SIZE >= 0
                        && BombermanGame.bomberman.getY() - y - Sprite.SCALED_SIZE < Sprite.SCALED_SIZE) {
                    int col = (BombermanGame.bomberman.getX() + 2) / Sprite.SCALED_SIZE;
                    if (x < col * Sprite.SCALED_SIZE && col * Sprite.SCALED_SIZE - x >= 0 && col * Sprite.SCALED_SIZE - x < 32)
                        x++;
                    if (x > col * Sprite.SCALED_SIZE && x - col * Sprite.SCALED_SIZE >= 0 && x - col * Sprite.SCALED_SIZE < 32)
                        x--;
                    if (x == col * Sprite.SCALED_SIZE) {
                        keyInput.up = false;
                        direction = 4;
                    }
                }
            }
            if (keyInput.down) {
                if (y >= BombermanGame.bomberman.getY() + Sprite.SCALED_SIZE
                        && y - BombermanGame.bomberman.getY() - Sprite.SCALED_SIZE >= 0
                        && y - BombermanGame.bomberman.getY() - Sprite.SCALED_SIZE < Sprite.SCALED_SIZE) {
                    int col = (BombermanGame.bomberman.getX() + 2) / Sprite.SCALED_SIZE;
                    if (x < col * Sprite.SCALED_SIZE && col * Sprite.SCALED_SIZE - x >= 0 && col * Sprite.SCALED_SIZE - x < 32)
                        x++;
                    if (x > col * Sprite.SCALED_SIZE && x - col * Sprite.SCALED_SIZE >= 0 && x - col * Sprite.SCALED_SIZE < 32)
                        x--;
                    if (x == col * Sprite.SCALED_SIZE) {
                        keyInput.down = false;
                        direction = 3;
                    }
                }
            }
        }
        if (life == 1) {
            speed = 1;
            throughWall = false;
        }
        super.update();
    }
}
