package PGV.UT3.Ejemplos.TCP_Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteSocketStream {
	public static void main(String args[]) throws IOException {

		String[] vecmens = { "Hi 1", "Simulo el 2", "exit" };

		for (int i = 0; i < vecmens.length; i++) {
			System.out.println("Creando nuevo socket cliente");
			Socket clientSocket = new Socket();

			/*Establecemos la dirección del servidor*/
			System.out.println("Estableciendo la conexi�n");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);

			/*Nos conectamos al servidor con el cliente, que activará el
			* .accept(), del servidor*/
			clientSocket.connect(addr);


			InputStream is = clientSocket.getInputStream();
			OutputStream os = clientSocket.getOutputStream();

			System.out.println("Enviando mensaje " + i);

			os.write(vecmens[i].getBytes());

			System.out.println("Mensaje enviado");
			System.out.println("Cerrando socket");
			clientSocket.close();
		}
	}
}
