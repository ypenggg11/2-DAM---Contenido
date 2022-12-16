package PGV.UT3.Ejemplos.Basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MuestraURL {

	public static void main(String[] args) {
		URL url;

		try {
			/*url = new URL("https://www.w3schools.com/sql/sql_injection.asp");
			 * https://www3.gobiernodecanarias.org/medusa/eforma/campus/course/view.php?id=27564
			 * */

			//url = new URL("https://www3.gobiernodecanarias.org/medusa/eforma/campus/course/view.php?id=27564");
			url = new URL("ftp://ftp.rediris.es");

			System.out.println("URL: " + url.toString());
			System.out.println("Host: " + url.getHost());
			System.out.println("Port: " + url.getDefaultPort());
			System.out.println("Info Usuario: " + url.getUserInfo());
			System.out.println("Protocolo: "+url.getProtocol());
			System.out.println("Query: " + url.getQuery());
			System.out.println("El PATH: "+url.getPath());


			System.out.println("Ejemplo de comunicaci�n con la URL (c�digo fuente p�gina):\n");
			InputStream is = url.openStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is));

			String linea;
			while ((linea=br.readLine()) != null) {
				System.out.println(linea);
			}
			br.close();
		} catch (MalformedURLException e) {
			System.out.println(e);

		} catch (IOException eio) {
			eio.printStackTrace();
		}

	}

}
