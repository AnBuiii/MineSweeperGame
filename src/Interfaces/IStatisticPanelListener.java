package Interfaces;

import Models.Player;

public interface IStatisticPanelListener{
    void back();
    void delete(int mode);

    Player getPlayer();
}
