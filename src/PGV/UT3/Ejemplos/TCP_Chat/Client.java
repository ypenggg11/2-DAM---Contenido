package PGV.UT3.Ejemplos.TCP_Chat;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/*
* Cada cliente se ejecutará sobre un hilo, que se conectará al servidor y estará a la escucha
* constante de mensajes que lleguen del Server, además, creará una instancia de un InputHandler,
* para manejar la entrada de datos del usuario y enviarlo al Server, ejecutado en un hilo distinto.
* */
public class Client implements Runnable {

    //TODO Upgrade code
    private BufferedReader clientReader;
    private OutputStream clientWriter;

    @Override
    public void run() {
        try {

            //Dirección del servidor
            InetSocketAddress address = new InetSocketAddress("127.0.0.1", Server.PORT);
            //Socket del cliente
            Socket clientSocket = new Socket();

            //Nos conectamos al servidor con nuestro socket
            clientSocket.connect(address);

            //Creamos las instancias de entrada y salida.
            clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            clientWriter = clientSocket.getOutputStream();

            //Iniciamos un hilo diferente para la entrada de datos por consola hacia el servidor.
            new Thread(new InputHandler()).start();

            //Este hilo se quedará en escucha constante de llegada de datos del servidor.
             String msg;
             while ((msg = clientReader.readLine())!= null) {
                 System.out.println(msg);
             }

             //Una vez finalice, cierra el socket.
             clientSocket.close();

        } catch (IOException e) {
            //Si da error, nos salimos del programa.
            System.exit(0);
        }
    }

    //Clase interna del cliente para manejar la entrada de datos del cliente. (mediante consola)
    class InputHandler implements Runnable {

        @Override
        public void run() {

            try {
                //Utilizando System.in, hacemos que la entrada sea desde consola
                BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

                //Estará en constante lectura de datos del cliente
                while (true) {
                    String msg = inputReader.readLine();
                    clientWriter.write((msg+"\n").getBytes());
                    clientWriter.flush();
                }

            } catch (IOException ignored) {
            }
        }
    }

    //Iniciamos un nuevo hilo del cliente.
    public static void main(String[] args) {
        new Thread(new Client()).start();
    }
}
