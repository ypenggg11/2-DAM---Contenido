package PGV.UT1.Simulacro.Proceso2JAR.src;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static IOBytes iobytes;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String dir = scanner.nextLine();

        File file = new File(dir);

        if (file.isDirectory()) {
            String[] fileNames = file.list();

            for (String fileName: fileNames) {
                Runtime runtime = Runtime.getRuntime();
                try {
                    Process process3 = runtime.exec("cmd /C C:\\Users\\penga\\Documents\\Development\\IdeaProjects\\IdeaProjects2\\Proceso2\\src\\proceso3.bat");

                    iobytes = new IOBytes(process3);

                    iobytes.writeStream(dir+"\\"+fileName);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}