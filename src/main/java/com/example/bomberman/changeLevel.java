package com.example.bomberman;


import com.example.bomberman.entities.NormalObject.Portal;
import javafx.animation.AnimationTimer;

public class changeLevel extends BombermanGame{

    public static void restart() {

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
               BombermanGame.INSTANCE.render();
               BombermanGame.INSTANCE.update();
            }
        };
        timer.start();

        BombermanGame.INSTANCE.createMap(BombermanGame.level);
    }

    public static void changeMap(int level) {
        if(Portal.isLevelUp()) {
            level++;
            restart();
        }
    }
}
