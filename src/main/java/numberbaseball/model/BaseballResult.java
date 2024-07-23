package numberbaseball.model;

public class BaseballResult {

    private int strike;
    private int ball;

    public BaseballResult(int strike, int ball) {
        this.strike = strike;
        this.ball = ball;
    }

    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }
}
