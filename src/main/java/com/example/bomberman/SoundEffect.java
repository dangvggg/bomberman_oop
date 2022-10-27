package com.example.bomberman;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffect {

    private static Clip play;

    //playGame
    public static void playGame(){
        AudioInputStream in = null;
        try{
            in = AudioSystem.getAudioInputStream(new File("/bomberman_oop2/src/main/resources/sound/backSound.wav"));
            play = AudioSystem.getClip();
            play.open(in);
            play.loop (Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception e){
            try {
                assert in != null;
                in.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }

    //player move
    public static void playerMove(){
        AudioInputStream in = null;
        Clip clip = null;
        try{
            in = AudioSystem.getAudioInputStream(new File("/bomberman_oop2/src/main/resources/sound/running.wav"));
            clip = AudioSystem.getClip();
            clip.open(in);
            clip.start();
        }
        catch(Exception e){
            try {
                assert in != null;
                in.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            assert clip != null;
            clip.close();
            e.printStackTrace();
        }
    }

    //play eatItem
    public static void playerEatItem(){
        AudioInputStream in = null;
        Clip clip = null;
        try{
            in = AudioSystem.getAudioInputStream(new File("/bomberman_oop2/src/main/resources/sound/eatItem.wav"));
            clip = AudioSystem.getClip();
            clip.open(in);
            clip.start();
        }
        catch(Exception e){
            try {
                assert in != null;
                in.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            assert clip != null;
            clip.close();
            e.printStackTrace();
        }
    }

    //player place bomb
    public static void playerPlaceBomb(){
        AudioInputStream in = null;
        Clip clip = null;
        try{
            in = AudioSystem.getAudioInputStream(new File("/bomberman_oop2/src/main/resources/sound/placeBomb.wav"));
            clip = AudioSystem.getClip();
            clip.open(in);
            clip.start();
        }
        catch(Exception e){
            try {
                assert in != null;
                in.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            assert clip != null;
            clip.close();
            e.printStackTrace();
        }
    }

    //bomb exploded
    public static void bombExploded(){
        AudioInputStream in = null;
        Clip clip = null;
        try{
            in = AudioSystem.getAudioInputStream(new File("/bomberman_oop2/src/main/resources/sound/bombExplode.wav"));
            clip = AudioSystem.getClip();
            clip.open(in);
            clip.start();
        }
        catch(Exception e){
            try {
                assert in != null;
                in.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            assert clip != null;
            clip.close();
            e.printStackTrace();
        }
    }

    //player die
    public static void playerDie(){
        AudioInputStream in = null;
        Clip clip = null;
        play.stop();
        try{
            in = AudioSystem.getAudioInputStream(new File("/bomberman_oop2/src/main/resources/sound/die.wav"));
            clip = AudioSystem.getClip();
            clip.open(in);
            clip.start();
        }
        catch(Exception e){
            try {
                assert in != null;
                in.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            assert clip != null;
            clip.close();
            e.printStackTrace();
        }
    }

    //Game Over
    public static void gameOver(){
        AudioInputStream in = null;
        Clip clip = null;
        try{
            in = AudioSystem.getAudioInputStream(new File("/bomberman_oop2/src/main/resources/sound/gameOver.wav"));
            clip = AudioSystem.getClip();
            clip.open(in);
            clip.loop (Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception e){
            try {
                assert in != null;
                in.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            assert clip != null;
            clip.close();
            e.printStackTrace();
        }
    }

    //player win
    public static void playWin(){
        AudioInputStream in = null;
        Clip clip = null;
        try{
            in = AudioSystem.getAudioInputStream(new File("/bomberman_oop2/src/main/resources/sound/win.wav"));
            clip = AudioSystem.getClip();
            clip.open(in);
            clip.loop (Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception e){
            try {
                assert in != null;
                in.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            assert clip != null;
            clip.close();
            e.printStackTrace();
        }
    }

}
