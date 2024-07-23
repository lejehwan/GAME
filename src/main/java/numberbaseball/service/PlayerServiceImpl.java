package numberbaseball.service;

import numberbaseball.repository.PlayerRepository;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static numberbaseball.utils.BaseballUtils.*;
import static numberbaseball.utils.ConsoleUtils.*;

public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final BufferedReader br;
    private final Set<String> playerNames = new HashSet<>();

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.br = getSystemInObject();
        this.playerRepository = playerRepository;
    }

    @Override
    public void playerNameSetUp() {
        for (int i = 1; i <= PLAYER_COUNT; i++) {
            String playerName;
            try {
                playerName = inputPlayerName(i);
            } catch (IOException e) {
                throw new RuntimeException("error");
            }
            savePlayerName(i, playerName);
        }
        printPlayerNames();
    }

    @Override
    public void playerNumberSetup() {
        for (int i = 1; i <= PLAYER_COUNT; i++) {
            String playerNumber;
            try {
                playerNumber = inputPlayerNumber(i);
            } catch (IOException e) {
                throw new RuntimeException("error");
            }
            savePlayerNumber(i, playerNumber);
            printEmptyConsole();
        }
    }

    @Override
    public int firstMoveSetup() {
        int firstMove;
        try {
            firstMove = inputFirstMove();
        } catch (IOException e) {
            throw new RuntimeException("error");
        }
        return firstMove;
    }

    private String inputPlayerName(int playerCount) throws IOException {
        while (true) {
            System.out.printf("Please input the player %d's name : ", playerCount);
            String playerName = br.readLine();

            if (playerNameCheck(playerName)) {
                if (!playerNames.contains(playerName)) {
                    playerNames.add(playerName);
                    return playerName;
                } else {
                    System.out.println(COLOR_RED + "!!! The player's name is duplicated. !!!" + COLOR_DEFAULT);
                }
            } else {
                System.out.println(COLOR_RED + "!!! The player's name is invalid. !!!" + COLOR_DEFAULT);
            }
        }
    }

    private String inputPlayerNumber(int playerCount) throws IOException {
        while (true) {
            System.out.printf("Please input the player %d's number [%d Length]: ", playerCount, NUMBER_SIZE);
            String playerNumber = br.readLine();

            if (playerNumCheck(playerNumber)) {
                return playerNumber;
            } else {
                System.out.println(COLOR_RED + "!!! The player's number is invalid. !!!" + COLOR_DEFAULT);
            }
        }
    }

    private int inputFirstMove() throws IOException {
        while (true) {
            System.out.println("Please enter the first move [Choose 1 or 2]");
            String firstMove = br.readLine();

            if (firstMoveCheck(firstMove)) {
                return Integer.parseInt(firstMove);
            } else {
                System.out.println(COLOR_RED + "!!! Select either 1 or 2. !!!" + COLOR_DEFAULT);
            }
        }
    }

    public boolean playerNameCheck(String playerName) {
        return StringUtils.isNotBlank(playerName);
    }

    public boolean playerNumCheck(String playerNumber) {
        if (StringUtils.isBlank(playerNumber)) return false;
        playerNumber = playerNumber.replaceAll(" ", "");
        if (playerNumber.length() != NUMBER_SIZE) return false;
        for (char c : playerNumber.toCharArray()) {
            if (!Character.isDigit(c) || c > '9') return false;
        }
        return true;
    }

    @Override
    public boolean firstMoveCheck(String firstMove) {
        if (StringUtils.isBlank(firstMove)) return false;
        return firstMove.equals(String.valueOf(PLAYER_NUMBER1)) || firstMove.equals(String.valueOf(PLAYER_NUMBER2));
    }

    private void savePlayerName(int priority, String playerName) {
        if (priority == PLAYER_NUMBER1) playerRepository.saveNameOfPlayer1(playerName);
        else if (priority == PLAYER_NUMBER2) playerRepository.saveNameOfPlayer2(playerName);
    }

    private void savePlayerNumber(int priority, String playerNumber) {
        if (priority == PLAYER_NUMBER1) playerRepository.saveNumberOfPlayer1(playerNumber);
        else if (priority == PLAYER_NUMBER2) playerRepository.saveNumberOfPlayer2(playerNumber);
    }

    private void printPlayerNames() {
        System.out.printf("Player 1: %s", playerRepository.getPlayer1().getName()).println();
        System.out.printf("Player 2: %s", playerRepository.getPlayer2().getName()).println();
    }

}
