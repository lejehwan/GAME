package numberbaseball.model;

public class Player {
    private String name;
    private String number;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void inputNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

}
