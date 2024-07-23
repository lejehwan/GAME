package numberbaseball.controller;

import numberbaseball.repository.BaseballRepositoryImpl;
import numberbaseball.repository.PlayerRepositoryImpl;
import numberbaseball.service.BaseballService;
import numberbaseball.service.BaseballServiceImpl;
import numberbaseball.service.PlayerService;
import numberbaseball.service.PlayerServiceImpl;

public class NumberBaseballGameController {

    private final PlayerService playerService;
    private final BaseballService baseballService;

    public NumberBaseballGameController(PlayerService playerService, BaseballService baseballService) {
        this.playerService = playerService;
        this.baseballService = baseballService;
    }

    public static void main(String[] args) {
        NumberBaseballGameController controller = new NumberBaseballGameController(
                new PlayerServiceImpl(new PlayerRepositoryImpl()),
                new BaseballServiceImpl(new BaseballRepositoryImpl(), new PlayerRepositoryImpl())
        );

        playerNameSetup(controller);
        playerNumberSetup(controller);
        int firstMove = firstMoveSetup(controller);

//        gameStart(controller, firstMove);
    }

    private static void playerNameSetup(NumberBaseballGameController controller) {
        System.out.println();
        System.out.println("************ ***************** ************");
        System.out.println("************ PLAYER NAME SETUP ************");
        System.out.println("************ ***************** ************");
        System.out.println();
        controller.playerService.playerNameSetUp();
    }

    private static void playerNumberSetup(NumberBaseballGameController controller) {
        System.out.println();
        System.out.println("************ ******************* ************");
        System.out.println("************ PLAYER NUMBER SETUP ************");
        System.out.println("************ ******************* ************");
        System.out.println();
        controller.playerService.playerNumberSetup();
    }

    private static int firstMoveSetup(NumberBaseballGameController controller) {
        System.out.println();
        System.out.println("************ ****************** ************");
        System.out.println("************ WHO WILL GO FIRST? ************");
        System.out.println("************ ****************** ************");
        System.out.println();
        return controller.playerService.firstMoveSetup();
    }

    private static void gameStart(NumberBaseballGameController controller, int firstMove) {
        System.out.println();
        System.out.println("************ ***************** ************");
        System.out.println("************     GAME START    ************");
        System.out.println("************ ***************** ************");
        System.out.println();
        controller.baseballService.guessNumber(firstMove);
    }

}
