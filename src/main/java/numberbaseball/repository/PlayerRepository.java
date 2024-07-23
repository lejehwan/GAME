package numberbaseball.repository;

import numberbaseball.model.Player;

public interface PlayerRepository {
    void saveNameOfPlayer1(String name);
    void saveNameOfPlayer2(String name);
    void saveNumberOfPlayer1(String number);
    void saveNumberOfPlayer2(String number);
    Player getPlayer1();
    Player getPlayer2();
    String getNumberOfPlayer1();
    String getNumberOfPlayer2();
}
