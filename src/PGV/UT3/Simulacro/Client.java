package PGV.UT3.Simulacro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.util.Scanner;

public class Client implements NetAddress {
    //TODO Limpiar y mejorar codigo + comentarios (todo funcional)
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

                            new Thread(new PrivateConnection(address)).start();

                            multicastSocket = new MulticastSocket(PORT);
                            multicastSocket.joinGroup(address);

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

    static class PrivateConnection implements Runnable {
        private InetAddress address;

        PrivateConnection(InetAddress address) {
            this.address = address;
        }

        @Override
        public void run() {
            try {
                Socket client = new Socket();
                client.connect(new InetSocketAddress(InetAddress.getLocalHost(),PRIVATE_PORT));

                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                OutputStream writer = client.getOutputStream();

                /* Recibe mensaje privado */
                System.out.println(reader.readLine());

                /* Envia respuesta al recibir */
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
