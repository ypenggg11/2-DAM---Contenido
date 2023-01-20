package PGV.UT3.Simulacro;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/*
    Hilo principal -> Envía datagramas al multicast
    Hilo PrivateConnection -> Gestiona la conexión privada
*/
public class Server implements NetAddress {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            /* Se conecta al grupo con MulticastSocket (Actúa de 'servidor') */
            MulticastSocket multicastSocket = new MulticastSocket(PORT);

            InetAddress address = InetAddress.getByName(IP);
            multicastSocket.joinGroup(address);

            System.out.println("Conectado!");
            System.out.println("Escriba algun mensaje...");

            /* Comienza a enviar mensajes al resto */
            String msg = "";
            DatagramPacket paquete;

            while (!msg.equalsIgnoreCase("salir")) {
                msg = scanner.nextLine();

                /* Si es un comando PRIVADO, se separará el mensaje */
                if (msg.toUpperCase().contains("PRIVADO:")) {
                    /* [1] = username, [2] = mensaje*/
                    String[] splitMsg = msg.split(":");

                    if (splitMsg.length > 2) {
                        /* Mensaje con solo 'PRIVADO:username' */
                        String broadcastMsg = (splitMsg[0] + ":" + splitMsg[1]).toUpperCase();

                        /* Mensaje a enviar por el canal privado */
                        msg = "PRIVADO: " + splitMsg[2];

                    /*
                       Enviamos el mensaje con 'PRIVADO:username', y el cliente con ese username
                       se conectará al canal privado (no se muestra para los otros)
                    */
                        paquete = new DatagramPacket(broadcastMsg.getBytes(), broadcastMsg.length(), address, PORT);
                        multicastSocket.send(paquete);

                        /* Iniciamos la conexión privada como servidor */
                        new Thread(new PrivateConnection(msg)).start();
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

    static class PrivateConnection implements Runnable {
        private String msg;

        PrivateConnection(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            try {
                /* Nos conectamos al puerto privado con dirección localhost */
                ServerSocket server = new ServerSocket(PRIVATE_PORT);

                /* Aceptamos el usuario que notificamos a conectarse */
                Socket client = server.accept();

                /* Abrimos el writer y reader */
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                OutputStream writer = client.getOutputStream();

                /* Envía mensaje privado ('\n' MUY IMPORTANTE) */
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
