package Controller;

import javax.sound.sampled.*;
import java.io.*;
import java.net.URL;


public class Music implements Serializable { // implements Serializable để lưu được cái này vào để save game được
    @Serial
    private static final long serialVersionUID = -6500665823330706018L;

    public Clip startGameMusic = startGameMusic();
    public Clip inGameMusic = InGameMusic();
    public Clip soundClickCell = SoundClickCell();
    public Clip soundSocketFlag = SoundSocketFlag();
    public Clip soundHoverButton = SoundHoverButton();
    public Clip soundClickButton = SoundClickButton();
    public Clip soundWinGame = SoundWinGame();
    public Clip soundLoseGame = SoundLoseGame();
    private float curMusicVolume = -12;
    private float curSoundEffectVolume = -12;

    private FloatControl fcStartGameMusic;
    private FloatControl fcInGameMusic;

    private FloatControl fcSoundHoverButton;
    private FloatControl fcSoundClickButton;
    private boolean isMuteMusic = false;
    private boolean isMuteSoundEffect = false;
    private Clip startGameMusic() {

        Clip clip;
        AudioInputStream audioInputStream = null;
        try {

            URL urlSound = this.getClass().getClassLoader().getResource("Music/StartGameMusic.wav");
           // File soundFile = new File("C:\\Users\\trant\\Downloads\\StartGameMusic.wav");
           if(urlSound != null){
               audioInputStream = AudioSystem.getAudioInputStream(urlSound);
           }

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            fcStartGameMusic = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        return clip;
    }
    private Clip InGameMusic(){
        Clip clip;
        AudioInputStream audioInputStream = null;
        try {

            URL urlSound = this.getClass().getClassLoader().getResource("Music/InGameMusic.wav");
            // File soundFile = new File("C:\\Users\\trant\\Downloads\\StartGameMusic.wav");
            if(urlSound != null) audioInputStream = AudioSystem.getAudioInputStream(urlSound);

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            fcInGameMusic = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        return clip;
    }
    private Clip SoundClickCell(){
        Clip clip;
        AudioInputStream audioInputStream = null;
        try {

            URL urlSound = this.getClass().getClassLoader().getResource("Music/SoundClickCell.wav");
            // File soundFile = new File("C:\\Users\\trant\\Downloads\\StartGameMusic.wav");
            if(urlSound != null) audioInputStream = AudioSystem.getAudioInputStream(urlSound);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        return clip;
    }
    private Clip SoundSocketFlag(){
        Clip clip;
        AudioInputStream audioInputStream = null;
        try {

            URL urlSound = this.getClass().getClassLoader().getResource("Music/SoundSocketFlag.wav");
            // File soundFile = new File("C:\\Users\\trant\\Downloads\\StartGameMusic.wav");
            if(urlSound != null) audioInputStream = AudioSystem.getAudioInputStream(urlSound);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        return clip;
    }
    private Clip SoundLoseGame(){
        Clip clip;
        AudioInputStream audioInputStream = null;
        try {

            URL urlSound = this.getClass().getClassLoader().getResource("Music/SoundLoseGame.wav");
            // File soundFile = new File("C:\\Users\\trant\\Downloads\\StartGameMusic.wav");
            if(urlSound != null) audioInputStream = AudioSystem.getAudioInputStream(urlSound);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        return clip;
    }
    private Clip SoundWinGame(){
        Clip clip;
        AudioInputStream audioInputStream = null;
        try {

            URL urlSound = this.getClass().getClassLoader().getResource("Music/SoundWinGame.wav");
            // File soundFile = new File("C:\\Users\\trant\\Downloads\\StartGameMusic.wav");
            if(urlSound != null) audioInputStream = AudioSystem.getAudioInputStream(urlSound);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        return clip;
    }
    private Clip SoundHoverButton(){
        Clip clip;
        AudioInputStream audioInputStream = null;
        try {

            URL urlSound = this.getClass().getClassLoader().getResource("Music/SoundHoverButton.wav");
            // File soundFile = new File("C:\\Users\\trant\\Downloads\\StartGameMusic.wav");
            if(urlSound != null) audioInputStream = AudioSystem.getAudioInputStream(urlSound);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            fcSoundHoverButton = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        return clip;
    }

    private Clip SoundClickButton(){
        Clip clip;
        AudioInputStream audioInputStream = null;
        try {

            URL urlSound = this.getClass().getClassLoader().getResource("Music/SoundClickButton.wav");
            // File soundFile = new File("C:\\Users\\trant\\Downloads\\StartGameMusic.wav");
            if(urlSound != null) audioInputStream = AudioSystem.getAudioInputStream(urlSound);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            fcSoundClickButton = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        return clip;
    }

    public void startMusicGame(Clip music){
        if(!isMuteMusic){
            FloatControl fc = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
            fc.setValue(curMusicVolume);
            music.loop( Clip.LOOP_CONTINUOUSLY);
            restart(music);
        }
    }

    public void startSoundEffect(Clip sound){
        if(!isMuteSoundEffect){
            FloatControl fc = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
            fc.setValue(curSoundEffectVolume);
            restart(sound);
        }
    }

    public  boolean isMuteMusic(){
        return isMuteMusic;
    }

    public  boolean isMuteSoundEffect(){
        return isMuteSoundEffect;
    }

    public float getCurMusicVolume(){
        return curMusicVolume;
    }

    public float getCurSoundEffectVolume(){
        return curSoundEffectVolume;
    }

    private void restart(Clip sound)
    {
        sound.setMicrosecondPosition(0);
        sound.start();
    }

    public void pauseMusic(boolean check){
        isMuteMusic = check;
        if(isMuteMusic){
            startGameMusic.stop();
        }else {
            restart(startGameMusic);
        }

    }
    public void pauseSoundEffect(boolean check){
        isMuteSoundEffect = check;
        if(isMuteSoundEffect){
            soundClickCell.stop();
            soundSocketFlag.stop();
            soundHoverButton.stop();
            soundClickButton.stop();
        }

    }

    public void volumeMusicChanged(float volume){
        curMusicVolume = volume;
        if(curMusicVolume > 6.0f){
            curMusicVolume = 6.0f;
        }
        else if(curMusicVolume <= -30.0f){
            curMusicVolume = -80.0f;

        }
        fcStartGameMusic.setValue(curMusicVolume);
        fcInGameMusic.setValue(curMusicVolume);
    }

    public void volumeSoundEffectChanged(float volume){
        curSoundEffectVolume = volume;

        if(curSoundEffectVolume > 6.0f){
            curSoundEffectVolume = 6.0f;
        }
        else if(curSoundEffectVolume <= -30.0f){
            curSoundEffectVolume = -80.0f;

        }
        fcSoundHoverButton.setValue(curSoundEffectVolume);
        fcSoundClickButton.setValue(curSoundEffectVolume);
    }

    public void closeMusic(){
       if(startGameMusic.isOpen()){
           startGameMusic.stop();
           startGameMusic.close();
           startGameMusic = null;
       }
        if(inGameMusic.isOpen()){
            inGameMusic.stop();
            inGameMusic.close();
            startGameMusic = null;
        }
        if(soundClickCell .isOpen()){
            soundClickCell .stop();
            soundClickCell .close();
            soundClickCell  = null;
        }
        if( soundSocketFlag  .isOpen()){
            soundSocketFlag  .stop();
            soundSocketFlag  .close();
            soundSocketFlag   = null;
        }
        if( soundClickButton  .isOpen()){
            soundClickButton  .stop();
            soundClickButton  .close();
            soundClickButton   = null;
        }
        if( soundWinGame  .isOpen()){
            soundWinGame .stop();
            soundWinGame.close();
            soundWinGame = null;
        }

        if( soundLoseGame .isOpen()){
            soundLoseGame .stop();
            soundLoseGame .close();
            soundLoseGame  = null;
        }
    }



}
