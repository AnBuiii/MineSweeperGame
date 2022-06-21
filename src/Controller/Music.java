package Controller;

import javax.sound.sampled.*;
import java.io.*;
import java.net.URL;


public class Music implements Serializable { // implements Serializable để lưu được cái này vào để save game được
    @Serial
    private static final long serialVersionUID = -6500665823330706018L;
    public Clip startGameMusic() {

        Clip clip = null;
        AudioInputStream audioInputStream = null;
        try {

            URL url = this.getClass().getClassLoader().getResource("Music/StartGameMusic.wav");
           // File soundFile = new File("C:\\Users\\trant\\Downloads\\StartGameMusic.wav");
            audioInputStream = AudioSystem.getAudioInputStream(url);

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        return clip;
    }
    public Clip InGameMusic(){
        Clip clip = null;
        AudioInputStream audioInputStream = null;
        try {

            URL url = this.getClass().getClassLoader().getResource("Music/InGameMusic.wav");
            // File soundFile = new File("C:\\Users\\trant\\Downloads\\StartGameMusic.wav");
            audioInputStream = AudioSystem.getAudioInputStream(url);

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        return clip;
    }
    public Clip SoundClickCell(){
        Clip clip = null;
        AudioInputStream audioInputStream = null;
        try {

            URL url = this.getClass().getClassLoader().getResource("Music/SoundClickCell.wav");
            // File soundFile = new File("C:\\Users\\trant\\Downloads\\StartGameMusic.wav");
            audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        return clip;
    }
    public Clip SoundSocketFlag(){
        Clip clip = null;
        AudioInputStream audioInputStream = null;
        try {

            URL url = this.getClass().getClassLoader().getResource("Music/SoundSocketFlag.wav");
            // File soundFile = new File("C:\\Users\\trant\\Downloads\\StartGameMusic.wav");
            audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        return clip;
    }


}
