package PGV.UT3.Ejemplos.UDP_Datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

/*
* Utilizando UDP (no orientado a conexón = no se conoce quién se conecta al servidor)
* */
public class ServidorDatagram {
	public static void main(String args[]) {

		System.out.println("Arranca el servidor");
		/* Al utilizar Datagram, no existe el datagram server socket */
		DatagramSocket datagramSocket = null;
		try {
			//Definimos un puerto para nuestro servidor
			datagramSocket = new DatagramSocket(25556);
		} catch (SocketException e) {
			e.printStackTrace();
		}

		while (datagramSocket != null) {
			try {
				System.out.println("Esperando mensajes.......");

				/*Al trabajar con datagram, se enviarán paquetes (datagram packet),
				y le tendremos q pasar un array de bytes y su longitud*/
				byte[] entrada = new byte[4];

				DatagramPacket datagrama1 = new DatagramPacket(entrada, 4);

				/*El datagram socket, al llamar al método receive se quedan a la
				espera de la llegada de un datagram packet enviado con .send()
				(solo ejecutará el código debajo de este hasta q llegue un paquete)*/
				datagramSocket.receive(datagrama1);

				/*Recuperaremos la IP y el puerto del cliente, apartir del paquete
				* que ha enviado*/
				InetAddress dircliente = datagrama1.getAddress();
				int puertocliente = datagrama1.getPort();


				System.out.println("Mensaje recibido desde: " + dircliente
						+ ", puerto " + puertocliente);

				/*Para obtener una string del paquete, llamaremos a .getData()*/
				String mensaje = new String(datagrama1.getData());

				System.out.println("Mensaje: " + mensaje);

				if (mensaje.equals("hora")) {
					System.out.println("Enviando respuesta");

					Date d = new Date(System.currentTimeMillis());

					/*Como trabajamos con datagram packet, se enviará un array de bytes*/
					byte[] salida = d.toString().getBytes();

					/*Al enviar un dato mediante datagram packet, se tendrá
					 * que añador además el lugar a donde se enviará (ip y puerto)*/
					DatagramPacket datagrama2 = new DatagramPacket(salida,
							salida.length, dircliente, puertocliente);

					datagramSocket.send(datagrama2);

					System.out.println("Mensaje enviado");
				} else {
					System.out.println("Esa petici�n no la conocemos ;-)");
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Fin");
	}
}
