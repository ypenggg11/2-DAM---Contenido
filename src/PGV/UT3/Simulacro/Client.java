package PGV.UT3.Simulacro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.util.Scanner;

public class Client implements NetAddress {
    private static final Scanner scanner = new Scanner(System.in);
    private static String username;

    public static void main(String[] args) {
        /* Solo recibe mensajes, no  escribe = solo hace falta hilo principal */

        /* Se conecta al grupo con MulticastSocket (Actúa de 'Cliente') */
        try {
            MulticastSocket multicastSocket = new MulticastSocket(PORT);

            System.out.print("Introduzca un nombre de usuario: ");
            username = scanner.nextLine();

            InetAddress address = InetAddress.getByName(IP);
            multicastSocket.joinGroup(address);

            System.out.println("Conectado!");
            System.out.println("Esperando mensajes... ");

            /* Recibe mensaje del servidor */
            String msg;

            while (true) {
                byte[] buffer = new byte[1024];

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                multicastSocket.receive(packet);

                msg = new String(packet.getData()).trim();

                if (!msg.equalsIgnoreCase("salir")) {
                    if (msg.contains("PRIVADO:")) {
                        String usr = msg.split(":")[1];
                        if (usr.equalsIgnoreCase(username)) {
                            try {
                                multicastSocket.leaveGroup(address);

                                //TODO Probar conexión privada con TCP (socket stream)

                                multicastSocket.joinGroup(address);

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } else {
                        System.out.println("Servidor: " + msg);
                    }
                } else {
                    break;
                }
            }

            System.out.println("Saliendo...");
            multicastSocket.leaveGroup(address);
            multicastSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
