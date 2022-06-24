package Interfaces;

public interface ISettingPanelListener {
    void back();
    void muteMusic(boolean check);
    void muteSoundEffect(boolean check);

    void changeVolumeMusic(float volume);

    void changeVolumeEffect(float volume);

}
