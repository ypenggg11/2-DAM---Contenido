package PGV.UT3.Ejemplos.Basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MuestraURLConec {

	public static void main(String[] args) {
		URL url=null;
		URLConnection urlCon=null;

		try {
			url = new URL("http://foro.elhacker.net/tutoriales_documentacion/tutorial_de_inyeccion_sql_sql_injection-t98448.0.html");
			urlCon=url.openConnection();

			InputStream is=urlCon.getInputStream();
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
