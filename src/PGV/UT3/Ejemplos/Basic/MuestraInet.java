package PGV.UT3.Ejemplos.Basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MuestraInet {
	public static void main(String[] args) {
		InetAddress dir = null, dir2 = null;

		try {

			dir = InetAddress.getLocalHost();
			System.out.println("IP local: " + dir);

			dir2 = InetAddress.getByName("www.elpais.es");
			System.out.println("de google: " + dir2);

			String sdir = dir2.getHostAddress();
			System.out.println("IP: " + sdir);

			InetAddress[] direcciones = InetAddress.getAllByName(dir2
					.getHostName());
			for (int i = 0; i < direcciones.length; i++) {
				System.out.println(direcciones[i].toString());
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}
}
