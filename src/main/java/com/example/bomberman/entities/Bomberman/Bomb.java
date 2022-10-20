package com.example.bomberman.entities.Bomberman;

import com.example.bomberman.BombermanGame;
import com.example.bomberman.SoundEffect;
import com.example.bomberman.entities.Enemy.Enemy;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.entities.NormalObject.Brick;
import com.example.bomberman.entities.NormalObject.Wall;
import com.example.bomberman.graphics.Sprite;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends Entity {
    private int bombLevel = 4;

    // Danh sach cac flames hien ra khi bomb no

    private List<Flame> flames = new ArrayList<>();
    private boolean exploded = false;
    private int explosionCountDown = 20;
    private int tickingCountDown = 90;


    // parameter x, y for coordinator, image la anh tuong ung
    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        setFlames();
    }

    public Bomb(int x, int y, Image img, int bombLevel) {
        super(x, y, img);
        this.bombLevel = bombLevel;
        setFlames();
    }

    public boolean isExploded() {
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    public List<Flame> getFlames() {
        return flames;
    }

    // Ham nay de update các image

    @Override
    public void update() {
        if (!isExploded()) {
            tickingImg();
        } else {
            BombermanGame.map[getY() / Sprite.SCALED_SIZE][getX() / Sprite.SCALED_SIZE] = 'g';
            explodingImg();
        }
    }

    public void tickingImg() {
        if (tickingCountDown == 0) {
            SoundEffect.bombExploded();
            setExploded(true);
        } else {
            this.img = Sprite.bombTickingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, tickingCountDown).getFxImage();
            tickingCountDown--;
        }
    }

    public void explodingImg() {
        if (explosionCountDown == 0) {
            this.img = null;
        } else {
            this.img = Sprite
                    .bombExplodeSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1,
                            Sprite.bomb_exploded2, explosionCountDown)
                    .getFxImage();
            explosionCountDown--;
        }
    }

    /**
     * Other entities collision with flame
     */

    public void handleFlameCollision(List<Entity> entities, List<Entity> staticObjects,
                                     List<Entity> damagedEntities) {
        // damage bricks
        for (Entity o : staticObjects) {
            if (o instanceof Brick) {
                for (Flame flame : flames) {
                    int oX = o.getX() / Sprite.SCALED_SIZE, fX = flame.getX() / Sprite.SCALED_SIZE, x = this.getX() / Sprite.SCALED_SIZE;
                    int oY = o.getY() / Sprite.SCALED_SIZE, fY = flame.getY() / Sprite.SCALED_SIZE, y = this.getY() / Sprite.SCALED_SIZE;
                    String pos = flame.getPos();

                    if (!pos.equals("left_most") && !pos.equals("down_most") &&
                            !pos.equals("right_most") && !pos.equals("top_most")) {
                        if (oX == fX && x == oX && oY - fY == 1 ||
                                oX == fX && x == oX && oY - fY == -1 ||
                                oX - fX == -1 && oY == fY && y == oY ||
                                oX - fX == 1 && oY == fY && y == oY) {
                            ((Brick) o).setDamaged(true);
                            damagedEntities.add(o);
                        }
                    } else {
                        if (oX == fX && oY == fY) {
                            ((Brick) o).setDamaged(true);
                            damagedEntities.add(o);
                        }
                    }
                }
            }
        }

        //damage enemy
        for (Entity o : entities) {
            if (o instanceof Enemy) {
                for (Flame flame : flames) {
                    int oX = o.getX() / Sprite.SCALED_SIZE, fX = flame.getX() / Sprite.SCALED_SIZE, x = this.getX() / Sprite.SCALED_SIZE;
                    int oY = o.getY() / Sprite.SCALED_SIZE, fY = flame.getY() / Sprite.SCALED_SIZE, y = this.getY() / Sprite.SCALED_SIZE;
                    String pos = flame.getPos();

                    if (!pos.equals("left_most") && !pos.equals("down_most") &&
                            !pos.equals("right_most") && !pos.equals("top_most")) {
                        if (oX == fX && x == oX && oY - fY == 1 ||
                                oX == fX && x == oX && oY - fY == -1 ||
                                oX - fX == -1 && oY == fY && y == oY ||
                                oX - fX == 1 && oY == fY && y == oY ||
                                oX == fX && x == oX && oY == fY) {
                            ((Enemy) o).setDamaged(true);
                            damagedEntities.add(o);
                        }
                    } else {
                        if (oX == fX && oY == fY) {
                            ((Enemy) o).setDamaged(true);
                            damagedEntities.add(o);
                        }
                    }
                }
            }
        }

        //damaged bomber
        Entity o = BombermanGame.bomberman;
        for (Flame flame : flames) {
            int oX = o.getX() / Sprite.SCALED_SIZE, fX = flame.getX() / Sprite.SCALED_SIZE, x = this.getX() / Sprite.SCALED_SIZE;
            int oY = o.getY() / Sprite.SCALED_SIZE, fY = flame.getY() / Sprite.SCALED_SIZE, y = this.getY() / Sprite.SCALED_SIZE;
            String pos = flame.getPos();

            if (!pos.equals("left_most") && !pos.equals("down_most") &&
                    !pos.equals("right_most") && !pos.equals("top_most")) {
                if (oX == fX && x == oX && oY - fY == 1 ||
                        oX == fX && x == oX && oY - fY == -1 ||
                        oX - fX == -1 && oY == fY && y == oY ||
                        oX - fX == 1 && oY == fY && y == oY ||
                        oX == fX && x == oX && oY == fY) {
                    ((Bomber) o).setDamaged(true);
                    damagedEntities.add(o);
                }
            } else {
                if (oX == fX && oY == fY) {
                    ((Bomber) o).setDamaged(true);
                    damagedEntities.add(o);
                }
            }
        }
    }

    public void setFlames() {
        String[] pos = {"left", "down", "right", "top", "left_most", "down_most", "right_most", "top_most", "center"};
        int[] iX = {-1, 0, 1, 0}; // left down right top
        int[] iY = {0, 1, 0, -1};

        BombermanGame.printMap();

        flames.add(new Flame(getX() / Sprite.SCALED_SIZE, getY() / Sprite.SCALED_SIZE, null, "center"));
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= bombLevel; j++) {
                int row = x / Sprite.SCALED_SIZE + j * iX[i];
                int col = y / Sprite.SCALED_SIZE + j * iY[i];
                // check xem row col co trong map k
                if (row > 0 && row < BombermanGame.WIDTH - 1 && col > 0 && col < BombermanGame.HEIGHT - 1) {
                    char flag = BombermanGame.map[col][row];
                    if (flag == '*') {
                        flames.add(new Flame(x / Sprite.SCALED_SIZE + iX[i] * 1, y / Sprite.SCALED_SIZE + iY[i] * 1, null, pos[i + 4]));
                        break;
                    }
                    if (flag == 'x') {
                        BombermanGame.map[col][row] = 'x';
                    }
                    if(flag == '#'){
                        break;
                    }
                        else {
                    if (j == bombLevel) {
                        flames.add(new Flame(x / Sprite.SCALED_SIZE + iX[i] * bombLevel, y / Sprite.SCALED_SIZE + iY[i] * bombLevel, null, pos[i + 4]));
                    } else {
                        flames.add(new Flame(x / Sprite.SCALED_SIZE + iX[i] * j, y / Sprite.SCALED_SIZE + iY[i] * j, null, pos[i]));
                    }
                    }
                }
            }
        }
    }
}
