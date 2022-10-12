package com.example.bomberman.entities.Bomberman;

import com.example.bomberman.entities.Entity;
import com.example.bomberman.graphics.Sprite;
import javafx.scene.image.Image;

public class Flame extends Entity {

    private int explosionCountDown = 120;
    private String pos;
    private boolean done = false;

    // parameter x, y for coordinator, image is the picture corresponding to Flame
    public Flame(int x, int y, Image img) {
        super(x, y, img);
    }

    public Flame(int x, int y, Image img, String pos) {
        super(x, y, img);
        this.pos = pos;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }

    public String getPos() {
        return pos;
    }

    public void explodingImg() {
        if (explosionCountDown == 0 || explosionCountDown >= 30) {
            this.img = null;
            if (explosionCountDown >= 30) {
                explosionCountDown--;
            } else {
                setDone(true);
            }
        } else {
            System.out.println(pos);
            switch (pos) {
                case "left", "right" -> this.img = Sprite
                        .bombExplodeSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1,
                                Sprite.explosion_horizontal2, explosionCountDown)
                        .getFxImage();
                case "top", "down" -> this.img = Sprite
                        .bombExplodeSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1,
                                Sprite.explosion_vertical2, explosionCountDown)
                        .getFxImage();
                case "left_most" -> this.img = Sprite
                        .bombExplodeSprite(Sprite.explosion_horizontal_left_last,
                                Sprite.explosion_horizontal_left_last1,
                                Sprite.explosion_horizontal_left_last2, explosionCountDown)
                        .getFxImage();
                case "down_most" -> this.img = Sprite
                        .bombExplodeSprite(Sprite.explosion_vertical_down_last,
                                Sprite.explosion_vertical_down_last1,
                                Sprite.explosion_vertical_down_last2, explosionCountDown)
                        .getFxImage();
                case "right_most" -> this.img = Sprite
                        .bombExplodeSprite(Sprite.explosion_horizontal_right_last,
                                Sprite.explosion_horizontal_right_last1,
                                Sprite.explosion_horizontal_right_last2, explosionCountDown)
                        .getFxImage();
                case "top_most" -> this.img = Sprite
                        .bombExplodeSprite(Sprite.explosion_vertical_top_last,
                                Sprite.explosion_vertical_top_last1,
                                Sprite.explosion_vertical_top_last2, explosionCountDown)
                        .getFxImage();
                case "center" -> this.img = Sprite
                        .bombExplodeSprite(Sprite.bomb_exploded,
                                Sprite.bomb_exploded1,
                                Sprite.bomb_exploded2, explosionCountDown)
                        .getFxImage();
            }

            explosionCountDown--;
        }
    }

    @Override
    public void update() {
        explodingImg();
    }

}