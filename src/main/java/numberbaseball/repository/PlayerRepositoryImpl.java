package numberbaseball.repository;

import numberbaseball.model.Player;

public class PlayerRepositoryImpl implements PlayerRepository {

    private Player player1;
    private Player player2;

    @Override
    public void saveNameOfPlayer1(String name) {
        player1 = new Player(name);
    }

    @Override
    public void saveNameOfPlayer2(String name) {
        player2 = new Player(name);
    }

    @Override
    public void saveNumberOfPlayer1(String number) {
        player1.inputNumber(number);
    }

    @Override
    public void saveNumberOfPlayer2(String number) {
        player2.inputNumber(number);
    }

    @Override
    public Player getPlayer1() {
        return player1;
    }

    @Override
    public Player getPlayer2() {
        return player2;
    }

    @Override
    public String getNumberOfPlayer1() {
        return player1.getNumber();
    }

    @Override
    public String getNumberOfPlayer2() {
        return player2.getNumber();
    }
}
