package PGV.UT3.Ejemplos.UDP_Datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteDatagram {
	public static void main(String args[]) {

		/*Al enviar mensajes, se utilizará datagram packet, por lo que
		* es necesario conocer el destino del paquete, que sería la del servidor*/
		final String SERVER_ADRESS = "192.168.192.131";
		final int SERVER_PORT = 25556;

		try {
			System.out.println("Creando datagrama cliente");

			/*Similar al Socket cliente utilizado en TCP*/
			DatagramSocket datagramSocket = new DatagramSocket();

			InetAddress dirservidor = InetAddress.getByName(SERVER_ADRESS);

			String mensaje = new String("hora");

			DatagramPacket datagrama1 = new DatagramPacket(mensaje.getBytes(),
					mensaje.getBytes().length, dirservidor, SERVER_PORT);

			datagramSocket.send(datagrama1);

			System.out.println("Mensaje enviado a " + dirservidor);

			/*Lo mismo realizado en el servidor, pero en el cliente para recibir un paquete*/
			byte[] respuesta = new byte[100];

			DatagramPacket datagrama2 = new DatagramPacket(respuesta,
					respuesta.length);

			datagramSocket.receive(datagrama2);

			System.out.println("Mensaje recibido: " + new String(respuesta));
			datagramSocket.close();
			System.out.println("el cliente termin�");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
