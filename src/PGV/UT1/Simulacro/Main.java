package PGV.UT1.Simulacro;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static IOBuffered ioBuffered;
    private final static String dirAbsolutePath = "C:\\Users\\penga\\Documents\\Development\\IdeaProjects\\IdeaProjects2\\src\\PGV\\UT1\\Simulacro\\NuevaCarpeta";

    public static void main(String[] args) {
        ProcessBuilder processBuilder1 = new ProcessBuilder("cmd","/C","msconfig");
        ProcessBuilder processBuilder2 = new ProcessBuilder("java","-jar","./src/PGV/UT1/Simulacro/Proceso2.jar");
        ProcessBuilder processBuilder4 = new ProcessBuilder("cmd","/C","dir "+dirAbsolutePath);

        try {
            Process process1 = processBuilder1.start();
            Process process2 = processBuilder2.start();

            ioBuffered = new IOBuffered(process2);
            ioBuffered.writeLine(dirAbsolutePath);
            process2.waitFor();

            Process process4 = processBuilder4.start();

            ioBuffered = new IOBuffered(process4);
            for (String line: ioBuffered.readLines()) {
                System.out.println(line);
            }

            process1.destroy();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
