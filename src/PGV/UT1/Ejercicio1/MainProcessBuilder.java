package PGV.UT1.Ejercicio1;

import java.io.IOException;

public class MainProcessBuilder {

    public static void main(String[] args) {

        //Por comandos del Sistema Operativo
        comandosSistemaOperativo();

        //Por comandos almacenados en un fichero por lotes (.bat)
        //Para evitar el error '.' del cmd, se utiliza la ruta absoluta
        comandosEnFicheroPorLotes();

        ProcessBuilder processBuilder3 = new ProcessBuilder("cmd","/C","start","java -jar Calculadora.jar");

        Process process3;

        try {
            process3 = processBuilder3.start();
            process3.waitFor();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(process3.info());
    }

    private static void comandosEnFicheroPorLotes() {
        ProcessBuilder processBuilder2 = new ProcessBuilder("cmd","/C","start","C:\\Users\\penga\\Documents\\Development\\IdeaProjects\\IdeaProjects2\\src\\PGV\\UT1\\Ejercicio1\\ficheroPorLotes.bat");

        Process process2;

        try {
            process2 = processBuilder2.start();
            process2.waitFor();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(process2.info());
    }

    private static void comandosSistemaOperativo() {

        ProcessBuilder processBuilder = new ProcessBuilder("notepad.exe");

        Process process;

        try {
            process = processBuilder.start();
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(process.info());
    }
}
