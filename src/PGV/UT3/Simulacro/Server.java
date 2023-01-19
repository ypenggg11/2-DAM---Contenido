package PGV.UT3.Simulacro;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server implements NetAddress{

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        /* No se atiende a varios clientes al mismo tiempo = NO hace falta multihilo */

        /* Se conecta al grupo con MulticastSocket (Actúa de 'servidor') */
        try {
            MulticastSocket multicastSocket = new MulticastSocket(PORT);

            InetAddress address = InetAddress.getByName(IP);
            multicastSocket.joinGroup(address);

            System.out.println("Conectado!");
            System.out.println("Escriba algun mensaje...");

            /* Envía mensaje al resto */
            String msg = "";
            DatagramPacket paquete;

            while (!msg.equalsIgnoreCase("salir")) {
                msg = scanner.nextLine();

                if (msg.toUpperCase().contains("PRIVADO:")) {
                    /* 1 = username, 2 = mensaje*/
                    String[] splitMsg = msg.split(":");

                    String broadcastMsg = (splitMsg[0]+":"+splitMsg[1]).toUpperCase();
                    msg = "PRIVADO: "+splitMsg[2];

                    paquete = new DatagramPacket(broadcastMsg.getBytes(), broadcastMsg.length(), address, PORT);
                    multicastSocket.send(paquete);

                    try {
                        multicastSocket.leaveGroup(address);

                        //TODO Probar conexión privada con TCP (socket stream)

                        multicastSocket.joinGroup(address);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                } else {
                    paquete = new DatagramPacket(msg.getBytes(), msg.length(), address, PORT);
                    multicastSocket.send(paquete);
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
