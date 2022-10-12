package com.example.bomberman.entities.Bomberman;

import com.example.bomberman.BombermanGame;
import com.example.bomberman.SoundEffect;
import com.example.bomberman.entities.CurrentImage;
import com.example.bomberman.entities.Enemy.Enemy;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.graphics.Sprite;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Entity {

    private boolean damaged = false;
    public List<Bomb> bombList = new ArrayList<Bomb>();
    private int bombRange = 5;
    public int bombLimit = 2;
    public int speed = 2;
    private int deathCountDown = 100;
    private int live = 1;

    /*
    For rendering
     */
    private CurrentImage currentImage = new CurrentImage();


    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLive() {
        return live;
    }

    public void setLive(int live) {
        this.live = live;
    }

    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
    }

    public boolean isDamaged() {
        return damaged;
    }

    /*
    MOVEMENTS AND BOMB PLACING.
     */

    public boolean moveRight() {
        SoundEffect.playerMove();
        img = Sprite
                .movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, currentImage.right)
                .getFxImage();
        if (currentImage.right == 8) {
            currentImage.right = 0;
        } else {
            currentImage.right++;
        }
        if (y % Sprite.SCALED_SIZE == 0) {
            int rightX = x + 22;
            if (BombermanGame.map[y / Sprite.SCALED_SIZE][rightX / Sprite.SCALED_SIZE] != '*' && BombermanGame.map[y / Sprite.SCALED_SIZE][rightX / Sprite.SCALED_SIZE] != '#') {
                x += speed;
                return true;
            }
        } else {
            int rightX = x + 22;
            int leg = y + Sprite.SCALED_SIZE;
            int upY = (leg / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE;
            int downY = upY + Sprite.SCALED_SIZE;
            if (BombermanGame.map[leg / Sprite.SCALED_SIZE][rightX / Sprite.SCALED_SIZE] != '*' && BombermanGame.map[leg / Sprite.SCALED_SIZE][rightX / Sprite.SCALED_SIZE] != '#'
                    && leg > upY + 13 && leg < downY) {
                x += speed;
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean moveLeft() {
        SoundEffect.playerMove();
        img = Sprite
                .movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, currentImage.left)
                .getFxImage();
        if (currentImage.left == 8) {
            currentImage.left = 0;
        } else {
            currentImage.left++;
        }
        if (y % Sprite.SCALED_SIZE == 0) {
            if (BombermanGame.map[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE] != '*' && BombermanGame.map[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE] != '#') {
                x -= speed;
                return true;
            }
        } else {
            int leg = y + Sprite.SCALED_SIZE;
            int upY = (leg / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE;
            int downY = upY + Sprite.SCALED_SIZE;
            if (BombermanGame.map[leg / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE] != '*' && BombermanGame.map[leg / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE] != '#'
                    && leg > upY + 13 && leg < downY) {
                x -= speed;
                return true;
            }
        }
        return false;
    }

    public boolean moveUp() {
        SoundEffect.playerMove();
        img = Sprite
                .movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, currentImage.up)
                .getFxImage();
        if (currentImage.up == 8) {
            currentImage.up = 0;
        } else {
            currentImage.up++;
        }
        int nextY = y - speed;
        int leftX = x + 6;
        int rightX = x + 20;
        if (BombermanGame.map[nextY / Sprite.SCALED_SIZE][rightX / Sprite.SCALED_SIZE] != '*' && BombermanGame.map[nextY / Sprite.SCALED_SIZE][rightX / Sprite.SCALED_SIZE] != '#'
                && BombermanGame.map[nextY / Sprite.SCALED_SIZE][leftX / Sprite.SCALED_SIZE] != '*' && BombermanGame.map[nextY / Sprite.SCALED_SIZE][leftX / Sprite.SCALED_SIZE] != '#') {
            y -= speed;
            return true;
        }
        return false;
    }

    @Override
    public boolean moveDown() {
        SoundEffect.playerMove();
        img = Sprite
                .movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, currentImage.down)
                .getFxImage();
        if (currentImage.down == 8) {
            currentImage.down = 0;
        } else {
            currentImage.down++;
        }
        int nextY = y + speed;
        int leftX = x + 6;
        int rightX = x + 20;
        int leg = nextY + 30;
        if (BombermanGame.map[leg / Sprite.SCALED_SIZE][rightX / Sprite.SCALED_SIZE] != '*' && BombermanGame.map[leg / Sprite.SCALED_SIZE][rightX / Sprite.SCALED_SIZE] != '#'
                && BombermanGame.map[leg / Sprite.SCALED_SIZE][leftX / Sprite.SCALED_SIZE] != '*' && BombermanGame.map[leg / Sprite.SCALED_SIZE][leftX / Sprite.SCALED_SIZE] != '#') {
            y += speed;
            return true;
        }
        return false;
    }

    public Entity placeBomb() {
        SoundEffect.playerPlaceBomb();
        Entity bom = new Bomb((this.getX() + 10) / Sprite.SCALED_SIZE, (this.getY() + 20) / Sprite.SCALED_SIZE,
                Sprite.bomb.getFxImage(), this.bombRange);
        this.bombList.add((Bomb) bom);
        return bom;
    }

    public int bombCounter() {
        return bombList.size();
    }

    public void die() {
        if (deathCountDown == 0) {
            this.img = null;
        } else {
            SoundEffect.playerDie();
            this.img = Sprite
                    .bombExplodeSprite(Sprite.player_dead3, Sprite.player_dead2, Sprite.player_dead1, deathCountDown)
                    .getFxImage();
            deathCountDown--;
        }
    }

    @Override
    public void update() {
        if (isDamaged() && live == 1) {
            setDamaged(false);
            live = 0;
        }

        if (live == 0) {
            die();
        }
        if (live > 0) {
            if (BombermanGame.keyInput.left && !BombermanGame.keyInput.up && !BombermanGame.keyInput.down) {
                moveLeft();
            }
            if (BombermanGame.keyInput.right && !BombermanGame.keyInput.up && !BombermanGame.keyInput.down) {
                moveRight();
            }
            if (BombermanGame.keyInput.up && !BombermanGame.keyInput.right && !BombermanGame.keyInput.left) {
                moveUp();
            }
            if (BombermanGame.keyInput.down && !BombermanGame.keyInput.right && !BombermanGame.keyInput.left) {
                moveDown();
            }
            if (!bombList.isEmpty()) {
                if (bombList.get(bombList.size() - 1).isExploded()) {
                    bombList.remove(bombList.size() - 1);
                }
            }
        } else {
            die();
        }
    }

    // Collision Enemy
    public boolean collision(List<Entity> entities) {

        int brightX = this.x + 24;
        int bdownY = this.y + Sprite.SCALED_SIZE;
        for (Entity x : entities) {
            if (x instanceof Enemy) {
                int erightX = x.getX() + Sprite.SCALED_SIZE;
                int edownY = x.getY() + Sprite.SCALED_SIZE;
                if (x.getX() <= this.x && this.x <= erightX) {
                    return (x.getY() <= bdownY && bdownY <= edownY) || (x.getY() <= this.y && this.y <= edownY);
                }
                if (x.getX() <= brightX && brightX <= erightX) {
                    return (x.getY() <= bdownY && bdownY <= edownY) || (x.getY() <= this.y && this.y <= edownY);
                }
            }
        }
        return false;
    }
}
