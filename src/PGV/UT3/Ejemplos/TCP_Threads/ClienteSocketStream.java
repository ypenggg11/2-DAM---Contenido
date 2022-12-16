package PGV.UT3.Ejemplos.TCP_Threads;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClienteSocketStream {

	public static void main(String args[]) {

		/*
		 * String m[] = { "Nom:Catalina\n", "Eco: esto es una prueba\n", "Fin:\n",
		 * "KO.:\n" };
		 */

		Scanner sc = new Scanner(System.in);

		try {
			System.out.println("Creando socket cliente");
			Socket clientSocket = new Socket();

			System.out.println("Estableciendo la conexi�n");
			InetSocketAddress addr = new InetSocketAddress("192.168.192.131", 5050);
			clientSocket.connect(addr);

			OutputStream os;
			String mensuser = "";

			do {
				System.out.print("Dime: ");
				mensuser = sc.nextLine();
				os = clientSocket.getOutputStream();
				os.write((mensuser+"\n").getBytes());
				os.flush();
					//Thread.sleep(5000);

			} while (!mensuser.contains("Fin"));
			System.out.println("Bye");
			clientSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
