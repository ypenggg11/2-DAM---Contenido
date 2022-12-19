package PGV.UT3.Ejemplos.TCP_Threads;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String args[]) {

		Scanner scanner = new Scanner(System.in);

		try {
			Socket clientSocket = new Socket();

			InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6000);
			connect(scanner, clientSocket, address);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void connect(Scanner sc, Socket clientSocket, InetSocketAddress addr) throws IOException {
		clientSocket.connect(addr);

		OutputStream outputStream;
		String msg = "";

		do {
			msg = sc.nextLine();
			outputStream = clientSocket.getOutputStream();
			outputStream.write((msg+"\n").getBytes());
			outputStream.flush();

		} while (!msg.contains("Exit"));

		clientSocket.close();
	}
}
