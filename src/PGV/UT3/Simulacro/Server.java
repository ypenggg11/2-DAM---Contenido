package PGV.UT3.Simulacro;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server implements NetAddress {

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

                    String broadcastMsg = (splitMsg[0] + ":" + splitMsg[1]).toUpperCase();
                    msg = "PRIVADO: " + splitMsg[2];

                    paquete = new DatagramPacket(broadcastMsg.getBytes(), broadcastMsg.length(), address, PORT);
                    multicastSocket.send(paquete);


                    multicastSocket.leaveGroup(address);
                    multicastSocket.close();

                    new Thread(new PrivateConnection(address,msg)).start();

                    multicastSocket = new MulticastSocket(PORT);
                    multicastSocket.joinGroup(address);

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

    static class PrivateConnection implements Runnable {
        private InetAddress address;
        private String msg;

        PrivateConnection(InetAddress address,String msg) {
            this.address = address;
            this.msg = msg;
        }

        @Override
        public void run() {
            try {
                ServerSocket server = new ServerSocket(PRIVATE_PORT);

                Socket client = server.accept();

                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                OutputStream writer = client.getOutputStream();

                /* Envía mensaje privado */
                writer.write((msg+"\n").getBytes());


                /* Recibe respuesta del cliente */
                System.out.println(reader.readLine());

                /* Close */
                writer.close();
                reader.close();
                client.close();
                server.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
