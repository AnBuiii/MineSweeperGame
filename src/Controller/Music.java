package Controller;

import javax.sound.sampled.*;
import java.io.*;
import java.net.URL;


public class Music {
    public Clip startGameMusic() {

        Clip clip = null;
        AudioInputStream audioInputStream = null;
        try {

            URL url = this.getClass().getClassLoader().getResource("Music/StartGameMusic.wav");
           // File soundFile = new File("C:\\Users\\trant\\Downloads\\StartGameMusic.wav");
            audioInputStream = AudioSystem.getAudioInputStream(url);
//            AudioFormat format = audioInputStream.getFormat();
//            DataLine.Info info = new DataLine.Info(Clip.class, format);
//            clip = (Clip) AudioSystem.getLine(info);
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
//            AudioFormat format = audioInputStream.getFormat();
//            DataLine.Info info = new DataLine.Info(Clip.class, format);
//            clip = (Clip) AudioSystem.getLine(info);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        return clip;
    }

}
