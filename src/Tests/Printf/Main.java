package Tests.Printf;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();

        for (int i=1;i<=20;i++) {
            print(i, String.valueOf(random.nextInt(0,99999999)));
        }
    }

    private static void print(int pos, String numeroGanador) {
        System.out.printf("PREMIO %-10s %s\n", pos, numeroGanador);

    }
}
