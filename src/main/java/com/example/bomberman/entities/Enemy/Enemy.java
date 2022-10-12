package com.example.bomberman.entities.Enemy;

import com.example.bomberman.BombermanGame;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.graphics.Sprite;
import javafx.scene.image.Image;

public abstract class Enemy extends Entity {
    protected boolean damaged = false;
    protected int speed;
    protected int life;
    protected int direction = 1;
    protected boolean throughWall;
    protected int deathCountDown = 100;

    public Enemy(int x, int y, Image img) {
        super(x, y, img);
        damaged = false;
    }

    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
    }

    public boolean isDamaged() {
        return damaged;
    }

    public boolean moveRight() {
        if (y % Sprite.SCALED_SIZE == 0) {
            int mod = x % Sprite.SCALED_SIZE;
            if (!throughWall) {
                if (BombermanGame.map[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE + 1] != '*' && BombermanGame.map[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE + 1] != '#') {
                    if (mod == 0 || (BombermanGame.map[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE] != '*' && BombermanGame.map[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE] != '#')) {
                        x += speed;
                        return true;
                    }
                }
                return false;
            } else {
                if (BombermanGame.map[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE + 1] != '#') {
                    if (mod == 0 || (BombermanGame.map[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE] != '#')) {
                        x += speed;
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    public boolean moveLeft() {
        if (y % Sprite.SCALED_SIZE == 0) {
            int mod = x % Sprite.SCALED_SIZE;
            if (!throughWall) {
                if ((mod != 0 && BombermanGame.map[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE] != '*' && BombermanGame.map[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE] != '#')
                        || (mod == 0 && BombermanGame.map[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE - 1] != '*' && BombermanGame.map[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE - 1] != '#')) {
                    x -= speed;
                    return true;
                }
                return false;
            } else {
                if ((mod != 0 && BombermanGame.map[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE] != '#')
                        || (mod == 0 && BombermanGame.map[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE - 1] != '#')) {
                    x -= speed;
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public boolean moveUp() {
        if (x % Sprite.SCALED_SIZE == 0) {
            int mod = y % Sprite.SCALED_SIZE;
            if (mod == 0) {
                if (!throughWall) {
                    if (BombermanGame.map[y / Sprite.SCALED_SIZE - 1][x / Sprite.SCALED_SIZE] != '*' && BombermanGame.map[y / Sprite.SCALED_SIZE - 1][x / Sprite.SCALED_SIZE] != '#') {
                        y -= speed;
                        return true;
                    }
                    return false;
                } else {
                    if (BombermanGame.map[y / Sprite.SCALED_SIZE - 1][x / Sprite.SCALED_SIZE] != '#') {
                        y -= speed;
                        return true;
                    }
                    return false;
                }
            }
            else {
                if (!throughWall) {
                    if (BombermanGame.map[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE] != '*' && BombermanGame.map[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE] != '#') {
                        y -= speed;
                        return true;
                    }
                    return false;
                } else {
                    if (BombermanGame.map[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE] != '#') {
                        y -= speed;
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }


    public boolean moveDown() {
        if (x % Sprite.SCALED_SIZE == 0) {
            if (!throughWall) {
                if (BombermanGame.map[y / Sprite.SCALED_SIZE + 1][x / Sprite.SCALED_SIZE] != '*' && BombermanGame.map[y / Sprite.SCALED_SIZE + 1][x / Sprite.SCALED_SIZE] != '#') {
                    y += speed;
                    return true;
                }
                return false;
            } else {
                if (BombermanGame.map[y / Sprite.SCALED_SIZE + 1][x / Sprite.SCALED_SIZE] != '#') {
                    y += speed;
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public void die() {

    }

    @Override
    public void update() {
        if (isDamaged() && life == 2) {
            setDamaged(false);
            life = 1;
        } else if (isDamaged() && life == 1) {
            setDamaged(false);
            life = 0;
        }

        if (life == 0) {
            die();
        } else {

            if (direction == 1) {
                if (!moveLeft()) direction = (int) (Math.random() * 4 + 1);

            }
            if (direction == 2) {
                if (!moveRight()) direction = (int) (Math.random() * 4 + 1);
            }

            if (direction == 3) {
                if (!moveUp()) direction = (int) (Math.random() * 4 + 1);
            }

            if (direction == 4) {
                if (!moveDown()) direction = (int) (Math.random() * 4 + 1);
            }
        }
    }
}

