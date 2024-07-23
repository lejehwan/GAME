package numberbaseball.service;

import numberbaseball.model.BaseballResult;
import numberbaseball.model.Player;
import numberbaseball.repository.BaseballRepository;
import numberbaseball.repository.PlayerRepository;

import java.io.BufferedReader;
import java.io.IOException;

import static numberbaseball.utils.BaseballUtils.*;
import static numberbaseball.utils.ConsoleUtils.getSystemInObject;

public class BaseballServiceImpl implements BaseballService {

    private final BaseballRepository baseBallRepository;
    private final PlayerRepository playerRepository;
    private final BufferedReader br;

    public BaseballServiceImpl(BaseballRepository baseBallRepository, PlayerRepository playerRepository) {
        br = getSystemInObject();
        this.baseBallRepository = baseBallRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public String guessNumber(int firstMove) {
        int rotate = checkToTarget(firstMove);

        while (true) {
            Player player = targetPlayer(rotate);
            String givenNumber = "";
            try {
                givenNumber = inputGuessNumber(player);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            BaseballResult baseballResult = evaluateGuess(givenNumber, player.getNumber());
            printBaseballResult(baseballResult);
            rotate ++;
        }
    }

    private int checkToTarget(int firstMove) {
        return firstMove == PLAYER_NUMBER1 ? PLAYER_NUMBER2 : PLAYER_NUMBER1;
    }

    private Player targetPlayer(int rotate) {
        return rotate % 2 == 0 ? playerRepository.getPlayer2() : playerRepository.getPlayer1();
    }

    private void printBaseballResult(BaseballResult baseballResult) {
        System.out.printf("result [%s STRIKE, %s BALL]", baseballResult.getStrike(), baseballResult.getBall());
    }

    private String inputGuessNumber(Player player) throws IOException {
        System.out.printf("Try to guess the number of Player %s: ", player.getName());
        return br.readLine();
    }

    public BaseballResult evaluateGuess(String givenNumber, String playerNumber) {
        int strike = 0;
        int ball = 0;
        for (int i = 0; i < NUMBER_SIZE; i++) {
            if (playerNumber.charAt(i) == givenNumber.charAt(i)) strike ++;
            else if (playerNumber.contains(String.valueOf(givenNumber.charAt(i)))) ball ++;
        }

        return new BaseballResult(strike, ball);
    }
}
