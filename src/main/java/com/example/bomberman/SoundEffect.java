package com.example.bomberman;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffect {

    private static Clip play;
    //menuStart
    public static void menuStart(){
        try{
            AudioInputStream in = AudioSystem.getAudioInputStream(new File("/bomberman/src/main/resources/sound/gameStart.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(in);
            clip.loop (Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //an Start, Stop, Continue
    public static void StartButton(){
        try{
            AudioInputStream in = AudioSystem.getAudioInputStream(new File("/bomberman/src/main/resources/sound/button.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(in);
            clip.start();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //playGame
    public static void playGame(){
        try{
            AudioInputStream in = AudioSystem.getAudioInputStream(new File("/bomberman/src/main/resources/sound/backSound.wav"));
            play = AudioSystem.getClip();
            play.open(in);
            play.loop (Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //player move
    public static void playerMove(){
        try{
            AudioInputStream in = AudioSystem.getAudioInputStream(new File("/bomberman/src/main/resources/sound/running.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(in);
            clip.start();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //play eatItem
    public static void playerEatItem(){
        try{
            AudioInputStream in = AudioSystem.getAudioInputStream(new File("/bomberman/src/main/resources/sound/eatItem.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(in);
            clip.start();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //player place bomb
    public static void playerPlaceBomb(){
        try{
            AudioInputStream in = AudioSystem.getAudioInputStream(new File("/bomberman/src/main/resources/sound/placeBomb.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(in);
            clip.start();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //bomb exploded
    public static void bombExploded(){
        try{
            AudioInputStream in = AudioSystem.getAudioInputStream(new File("/bomberman/src/main/resources/sound/bombExplode.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(in);
            clip.start();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //player die
    public static void playerDie(){
        play.stop();
        try{
            AudioInputStream in = AudioSystem.getAudioInputStream(new File("/bomberman/src/main/resources/sound/die.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(in);
            clip.start();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //Game Over
    public static void gameOver(){
        try{
            AudioInputStream in = AudioSystem.getAudioInputStream(new File("/bomberman/src/main/resources/sound/gameOver.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(in);
            clip.loop (Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //player win
    public static void playWin(){
        try{
            AudioInputStream in = AudioSystem.getAudioInputStream(new File("/bomberman/src/main/resources/sound/win.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(in);
            clip.loop (Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
