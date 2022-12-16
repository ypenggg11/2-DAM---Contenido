package PGV.UT3.Ejemplos.TCP_Socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;
/*
* En TCP, Socket sera un cliente, y ServerSocket, será el servidor
* */
public class ServidorSocketStream {
    public static void main(String args[]) throws IOException {

        System.out.println("Creando socket del servidor");
        ServerSocket serverSocket = new ServerSocket();

        System.out.println("Realizando el bind");
        InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
        serverSocket.bind(addr);

        boolean seguir = true;
        while (seguir) {
            System.out.println("Acepta conexiones");
			/*Aunque sea un bucle infinito, con .accept(), se quedará a la espera
			* hasta que llegue un socket del cliente, y seguirá ejecutando su código*/
            Socket newSocket = serverSocket.accept();

            System.out.println("Conexi�n recibida");

			/*Para la lectura/escritura de datos, podremos utilizar cualquier clase
			* visto en ficheros*/
            InputStream is = newSocket.getInputStream();

            byte[] mensaje = new byte[25];
            is.read(mensaje);

            String smens = (new String(mensaje)).trim();

            System.out.println("Mensaje recibido: " + smens);
            System.out.println("Cerramos el socket para escuchar al cliente");

			//Cerramos el socket del cliente, no del servidor
            newSocket.close();

            if (smens.equals("exit")) seguir = false;
        }

        System.out.println("Y cerrando el socket del servidor");
        serverSocket.close();
    }
}
