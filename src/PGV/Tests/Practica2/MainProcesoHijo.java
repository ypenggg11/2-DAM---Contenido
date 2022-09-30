package PGV.Tests.Practica2;

import java.io.*;

public class MainProcesoHijo {

    //Comunicar 2 procesos -> el actual con otro que creamos anteriormente (.jar)
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        try{
            //Ejecución del .jar en el cmd
            Process process = runtime.exec("java -jar ./src/PGV/Tests/Practica2/ProcesoHijo.jar");

            //Como en .jar, contiene en la primera linea del main, un Scanner que espera la entrada
            //de datos del usuario, se lo pasaremos con OutputStream
            OutputStream outputStream = process.getOutputStream();

            //Entrada de datos para el Scanner -> \n equivaldrá al ENTER
            outputStream.write("Peng\n".getBytes());
            outputStream.flush();

            outputStream.close();

            //El .jar, tras eso, imprimirá por consola "Hola, 'nombre'" con 'sout', por lo que,
            //lo leeremos y imprimiremos en nuestra consola del IDE
            InputStream inputStream = process.getInputStream();

            int c;
            while ((c = inputStream.read())!=-1) {
                System.out.print((char)c);
            }

            //Ambos procesos, se podrían haber utilizado también la extensión Buffered

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
