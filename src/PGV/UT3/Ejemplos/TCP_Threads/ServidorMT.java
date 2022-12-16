package PGV.UT3.Ejemplos.TCP_Threads;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Calendar;

public class ServidorMT extends Thread {
	Socket s = null;

	public ServidorMT(Socket socket) {
		this.s = socket;
	}

	public static void main(String args[]) throws IOException,
			InterruptedException {

		System.out.println("Creando socket del servidor");
		ServerSocket serverSocket = new ServerSocket(5050);
		while (true) {
			try {
				System.out.println("Acepta conexiones");
				Socket newSocket = serverSocket.accept();

				new ServidorMT(newSocket).start();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void run() {
		boolean seguir = true;

		try {
			InetAddress origen = s.getInetAddress();
			System.out.println("Conexi�n recibida desde la IP " + origen
					+ " con el hilo: " + Thread.currentThread().getName());

			InputStream is = s.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			/*Se quedará a la espera hasta que el cliente envíe algún dato
			 * mediante el .write() de alguna clase de ficheros. */
			String mensaje = br.readLine();

			while (seguir) {

				switch (mensaje.substring(0, 3)) {
				/*case "Nom":
					System.out.println("Hola " +mensaje.substring(4, mensaje.length()));
					break;
				case "Eco":
					System.out.println("Linea OK: " + mensaje.substring(4, mensaje.length()));
					break;*/
				case "Fin":
					System.out.println("El cliente "+Thread.currentThread().getName()+" se desconecta, "
							+ Calendar.getInstance().getTime());
					seguir = false;
					break;
				default:
					System.out.println(Thread.currentThread().getName()+" dijo: "+mensaje);
					break;
				}
				mensaje = br.readLine();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
