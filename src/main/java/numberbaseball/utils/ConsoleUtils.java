package numberbaseball.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUtils {
    public static final String COLOR_DEFAULT = "\u001B[0m";
    public static final String COLOR_RED = "\u001B[31m";
    public static BufferedReader br;

    public static BufferedReader getSystemInObject() {
        if (br != null) return br;
        br = new BufferedReader(new InputStreamReader(System.in));
        return br;
    }

    public static void closeSystemInObject() {
        try {
            if (br == null) return;
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void clearConsole() {
        String os = System.getProperty("os.name");
        try {
            ProcessBuilder processBuilder;
            if (os.contains("Windows")) {
                processBuilder = new ProcessBuilder("cmd", "/c", "cls");
            } else {
                processBuilder = new ProcessBuilder("clear");
            }
            Process process = processBuilder.inheritIO().start();
            process.waitFor();
        } catch (Exception e) {
            printEmptyConsole();
        }
    }

    public static void printEmptyConsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

}
