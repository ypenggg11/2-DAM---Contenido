package PGV.UT1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MiProceso {

    public static void main(String[] args) {

        //Or RunTime class to creating an instance of a existing process
        ProcessBuilder processBuilder1 = new ProcessBuilder("explorer.exe");

        ProcessBuilder processBuilder2 = new ProcessBuilder().command("notepad.exe");

        //With the commented section, you can run the cmd console
        ProcessBuilder processBuilder3 = new ProcessBuilder(/*"cmd","/C","start",*/"./PGV/src/UT1/ejemploLote.bat");

        //To avoid the cmd '.' error, use an absolute path
        ProcessBuilder processBuilder4 = new ProcessBuilder("cmd","/C","start","C:\\Users\\penga\\IdeaProjects\\IdeaProjects2\\PGV\\src\\UT1\\ejemploLote2.bat");

        //Creates a process
        Process process;

        try {
            //Executes the last process instance
            process = processBuilder2.start();

            //Waits to execute the next process until the user closes the first window
            // (funciona cuando quiere)
            process.waitFor();

            //Returns the PID of the process
            System.out.println(process.pid());

            //Destroys the current process
            process.destroy();

            //Verifies if the process is still alive
            System.out.println(process.isAlive());

            processBuilder1.start();

            processBuilder3.start();

            processBuilder4.start();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
