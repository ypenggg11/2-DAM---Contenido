package PGV.UT1.Ejercicio2.MainHijos;
import java.io.IOException;
import java.util.Scanner;

public class Main1 {
    private static String path = "C:\\Users\\penga\\Documents\\Development\\IdeaProjects\\IdeaProjects2\\EscribeBytes\\src\\";

    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/C", path + "musicPlayer.bat");

        try {
            Process process = processBuilder.start();

            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();

            System.out.println(text.toUpperCase());
        } catch (IOException var5) {
            throw new RuntimeException(var5);
        }
    }
}
