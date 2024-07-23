package numberbaseball.service;

public interface PlayerService {

    void playerNameSetUp();
    void playerNumberSetup();
    int firstMoveSetup();
    boolean playerNameCheck(String playerName);
    boolean playerNumCheck(String playerNum);
    boolean firstMoveCheck(String firstMove);
}
