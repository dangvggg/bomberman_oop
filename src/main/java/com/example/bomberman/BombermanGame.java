package com.example.bomberman;

import com.example.bomberman.entities.*;
import com.example.bomberman.entities.Bomberman.Bomb;
import com.example.bomberman.entities.Bomberman.Bomber;
import com.example.bomberman.entities.Bomberman.Flame;
import com.example.bomberman.entities.Enemy.*;
import com.example.bomberman.entities.Item.BombItem;
import com.example.bomberman.entities.Item.FlameItem;
import com.example.bomberman.entities.Item.SpeedItem;
import com.example.bomberman.entities.NormalObject.Brick;
import com.example.bomberman.entities.NormalObject.Grass;
import com.example.bomberman.entities.NormalObject.Portal;
import com.example.bomberman.entities.NormalObject.Wall;
import com.example.bomberman.graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    private GraphicsContext gc;
    private Canvas canvas;
    public static Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
    public static List<Entity> entities = new ArrayList<>(); // contains moving items
    public List<Entity> stillObjects = new ArrayList<>(); // contain still items
    public List<Entity> flames = new ArrayList<>(); // contain flames items
    public static List<Entity> staticObjects = new ArrayList<>(); // contains Items and Bricks
    public static List<Entity> damagedEntities = new ArrayList<>();
    public static char map[][];
    public static KeyInput keyInput = new KeyInput();


    /**
     * Khoi tao game.
     */
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    // JavaFx -> Application -> launch -> start
    // a window -> stage
    @Override
    public void start(Stage stage) {

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        //Setting the title to Stage.
        stage.setTitle("Bomberman");
        // Attach the scene object to the stage
        stage.setScene(scene);
        //Display the contents of the scene
        stage.show();


        entities.add(bomberman);

        // loop
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();

        SoundEffect.playGame();
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case LEFT -> {
                    keyInput.left = true;
                }
                case RIGHT -> {
                    keyInput.right = true;
                }
                case UP -> {
                    keyInput.up = true;
                }
                case DOWN -> {
                    keyInput.down = true;
                }
            }
        });
        scene.setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case LEFT -> {
                    keyInput.left = false;
                }
                case RIGHT -> {
                    keyInput.right = false;
                }
                case UP -> {
                    keyInput.up = false;
                }
                case DOWN -> {
                    keyInput.down = false;
                }
                case SPACE -> {

                    // limit the number of bomb (=1) at one tile

                    int curX = bomberman.getX() / Sprite.SCALED_SIZE, curY = bomberman.getY() / Sprite.SCALED_SIZE;
                    if (bomberman instanceof Bomber && map[curY][curX] != '*') {

                        Bomber bomber = (Bomber) bomberman;
                        if (bomber.getLive() > 0 && bomber.bombCounter() < bomber.getBombLimit()) {

                            // dat bom
                            Entity bomb = bomber.placeBomb();
                            entities.add(bomb);
                            flames.addAll(((Bomb) bomb).getFlames());

                        }
                    }
                }
            }
        });
    }

    public static void printMap() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void createMap() {
        try {
            Scanner scf = new Scanner(new BufferedReader(new FileReader("src/main/resources/levels/Level" + 1 + ".txt")));

            int lv = scf.nextInt();
            int row = scf.nextInt();
            int col = scf.nextInt();
            map = new char[row][col];
            scf.nextLine();

            // Xu ly thong tin trong file
            for (int i = 0; i < row; i++) {
                String s1 = scf.nextLine();
                System.out.println(s1);
                for (int j = 0; j < col; j++) {
                    Entity object;
                    char key = s1.charAt(j);
                    switch (key) {
                        case '#' -> {
                            object = new Wall(j, i, Sprite.wall.getFxImage());
                            stillObjects.add(object);
                            map[i][j] = '#';
                        }
                        case '*' -> {
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            object = new Brick(j, i, Sprite.brick.getFxImage());
                            staticObjects.add(object);
                            map[i][j] = '*';
                        }
                        case 'x' -> {
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            object = new Portal(j, i, Sprite.portal.getFxImage());
                            staticObjects.add(object);
                            object = new Brick(j, i, Sprite.brick.getFxImage());
                            staticObjects.add(object);
                            map[i][j] = '*';
                        }
                        case 's' -> {
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            object = new SpeedItem(j, i, Sprite.powerup_speed.getFxImage());
                            staticObjects.add(object);
                            object = new Brick(j, i, Sprite.brick.getFxImage());
                            staticObjects.add(object);
                            map[i][j] = '*';
                        }
                        case 'f' -> {
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            object = new FlameItem(j, i, Sprite.powerup_flames.getFxImage());
                            staticObjects.add(object);
                            object = new Brick(j, i, Sprite.brick.getFxImage());
                            staticObjects.add(object);
                            map[i][j] = '*';
                        }
                        case 'b' -> {
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            object = new BombItem(j, i, Sprite.powerup_bombs.getFxImage());
                            staticObjects.add(object);
                            object = new Brick(j, i, Sprite.brick.getFxImage());
                            staticObjects.add(object);
                            map[i][j] = '*';
                        }
                        case '1' -> {
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            object = new Ballon(j, i, Sprite.player_right.getFxImage());
                            entities.add(object);
                            map[i][j] = '1';
                        }
                        case '2' -> {
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            object = new Oneal(j, i, Sprite.player_right.getFxImage());
                            entities.add(object);
                            map[i][j] = '2';
                        }
                        case '3' -> {
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            object = new Minvo(j, i, Sprite.player_right.getFxImage());
                            entities.add(object);
                            map[i][j] = '3';
                        }
                        case '4' -> {
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            object = new Doll(j, i, Sprite.player_right.getFxImage());
                            entities.add(object);
                            map[i][j] = '4';
                        }
                        case '5' -> {
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            object = new Ghost(j, i, Sprite.player_right.getFxImage());
                            entities.add(object);
                            map[i][j] = '5';
                        }
                        case '6' -> {
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            object = new Kondoria(j, i, Sprite.player_right.getFxImage());
                            entities.add(object);
                            map[i][j] = '6';
                        }
                        default -> {
                            object = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(object);
                            map[i][j] = 'g';
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update() {
        updateDamagedObjects();
        for (Entity entity : entities) {
            entity.update();
        }
        for (Entity flame : flames) {
            flame.update();
        }
        for (Entity stillObject : stillObjects) {
            stillObject.update();
        }
        for (Entity staticObject : staticObjects) {
            staticObject.update();
        }
    }

    public void render() {
        // Clears a portion of the canvas with a transparent color value.
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Entity stillObject : stillObjects) {
            stillObject.render(gc);
        }
        for (Entity g : staticObjects) {
            g.render(gc);
        }
        for (Entity entity : entities) {
            entity.render(gc);
        }
        for (Entity flame : flames) {
            flame.render(gc);
        }
    }

    public void updateDamagedObjects() {
        // update bricks and enemies
        for (Entity entity : entities) {
            checkForDamagedEntities(entity);
            for (Entity damagedEntity : damagedEntities) {
                updateStaticObjects(damagedEntity);
                updateObject(damagedEntity);
            }

        }
        // get the explosion done (remove the flames)
        for (int i = 0; i < flames.size(); i++) {
            if (flames.get(i) instanceof Flame) {
                if (((Flame) flames.get(i)).isDone()) {
                    flames.remove(flames.get(i));
                }
            }
        }
        if (bomberman instanceof Bomber) {
            Bomber bomber = (Bomber) bomberman;
            if (bomber.collision(entities)) {
                int life_left = bomber.getLive() - 1;
                bomber.setLive(life_left);
            }
        }

    }

    public void checkForDamagedEntities(Entity o) {
        // remove bomb from the entites
        if (o instanceof Bomb) {
            if (((Bomb) o).isExploded()) {

                // enable bomber go through the place used to be for the bomb
                int curY = o.getY() / Sprite.SCALED_SIZE;
                int curX = o.getX() / Sprite.SCALED_SIZE;
                map[curY][curX] = 'g';

                // check if the bomb damange any objects
                ((Bomb) o).handleFlameCollision(entities, staticObjects, damagedEntities);
                System.out.println(entities.remove(o));
            }
        }
    }



    public void updateStaticObjects(Entity br) {
        if (br instanceof Brick) {
            if (((Brick) br).isDone()) {

                Brick brick = (Brick) br;
                // replace the tile with the grass
                damagedEntities.remove(br);
                staticObjects.remove(br);
                Entity entity = new Grass(br.getX() / Sprite.SCALED_SIZE, br.getY() / Sprite.SCALED_SIZE, Sprite.grass.getFxImage());
                if (brick.getItem() != null) {
                    staticObjects.add(entity);
                } else {
                    stillObjects.add(entity);
                }
            } else {
                br.update();
            }
        }
    }

    //notice
    public void updateObject(Entity ey) {
        if (ey instanceof Enemy) {
            if (((Enemy) ey).isDamaged()) {
                entities.remove(ey);
                damagedEntities.remove(ey);
            }
        }
    }
}

