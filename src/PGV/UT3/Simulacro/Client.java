package PGV.UT3.Simulacro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.util.Scanner;

/*
    Hilo principal -> Recibe datagramas del multicast
    Hilo PrivateConnection -> Gestiona la conexión privada
*/
public class Client implements NetAddress {
    private static final Scanner scanner = new Scanner(System.in);
    private static String username;

    public static void main(String[] args) {
        try {
            /* Se conecta al grupo con MulticastSocket (Actúa de 'Cliente') */
            MulticastSocket multicastSocket = new MulticastSocket(PORT);

            System.out.print("Introduzca un nombre de usuario: ");
            username = scanner.nextLine();

            InetAddress address = InetAddress.getByName(IP);
            multicastSocket.joinGroup(address);

            System.out.println("Conectado!");
            System.out.println("Esperando mensajes... ");

            /* Comienza a recibir mensajes del servidor */
            String msg;

            while (true) {
                byte[] msgBuffer = new byte[1024];

                DatagramPacket packet = new DatagramPacket(msgBuffer, msgBuffer.length);
                multicastSocket.receive(packet);

                msg = new String(packet.getData()).trim();

                /* Comprobación del mensaje recibido */
                if (!msg.equalsIgnoreCase("salir")) {
                    if (msg.contains("PRIVADO:")) {

                        /* Recupera el nombre de usuario enviado por el servidor */
                        String usr = msg.split(":")[1];

                        /* Si es nuestro usuario, empieza la conexión privada */
                        if (usr.equalsIgnoreCase(username)) {
                            new Thread(new PrivateConnection()).start();
                        }

                    } else {
                        System.out.println("Servidor: " + msg);
                    }
                } else {
                    break;
                }
            }

            /* Se sale del grupo y cierra el multicast */
            System.out.println("Saliendo...");
            multicastSocket.leaveGroup(address);
            multicastSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class PrivateConnection implements Runnable {

        PrivateConnection() {}

        @Override
        public void run() {
            try {
                /* Conexión por Socket Stream con el puerto privado */
                Socket client = new Socket();
                client.connect(new InetSocketAddress(InetAddress.getLocalHost(),PRIVATE_PORT));

                /* Abrimos el writer y reader */
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                OutputStream writer = client.getOutputStream();

                /* Recibe mensaje privado */
                System.out.println(reader.readLine());

                /* Envia respuesta al recibir ('\n' MUY IMPORTANTE) */
                String reply = username + ": OK, RECIBIDO\n";
                writer.write(reply.getBytes());

                /* Close */
                writer.close();
                reader.close();
                client.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
