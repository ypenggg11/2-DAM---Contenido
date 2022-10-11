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

            ProcessBuilder processBuilder3 = new ProcessBuilder("cmd","/C","C:\\Users\\penga\\Documents\\Development\\IdeaProjects\\IdeaProjects2\\src\\PGV\\UT1\\Simulacro\\proceso3.bat");

            for (String miFileName: fileNames) {

                try {
                    Process process3 = processBuilder3.start();

                    iobytes = new IOBytes(process3);

                    iobytes.writeStream(dir+"\\"+miFileName);
                    process3.waitFor();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}