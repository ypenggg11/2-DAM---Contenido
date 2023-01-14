package PGV.UT3.ChatMultiCast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

// Modelo de grupos UDP
public class Chat implements Runnable {

    //TODO Evitar error al salir (exit) -> Origen = cierre de MulticastSocket
    public static final String host = "225.1.1.1";
    public static final int port = 6999;
    public String username;

    @Override
    public void run() {
        try {
            // En este caso, cada cliente que se conecte, será un multicastsocket
            MulticastSocket multicastSocket = new MulticastSocket(port);

            InetAddress group = InetAddress.getByName(host);

            // El cliente se une al grupo con la dirección establecida
            multicastSocket.joinGroup(group);

            System.out.print("SYS: Introduzca un nombre de usuario -> ");
            username = new Scanner(System.in).nextLine();

            // Cada cliente conectado, tendrá su propia Conexión en un hilo separado,
            // donde se encargará de leer la entrada por teclado de ese cliente
            new Thread(new Connection(multicastSocket, group)).start();

            String[] msg;

            // Una vez en el grupo, este hilo pasará a encargarse de escuchar constantemente
            // mensajes que puedan llegar de otros clientes
            while (true){
                byte[] buffer = new byte[1024];

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                multicastSocket.receive(packet);

                String packetData = new String(packet.getData()).trim();
                msg = packetData.split(":");

                if (msg[1].trim().equalsIgnoreCase("exit")) {
                    System.out.println(msg[0]+" se ha desconectado");
                    break;
                }

                if (!(msg[0].equals(username))) {
                    System.out.println(packetData);
                }
            }

            // Una vez finalice el bucle, se sale del grupo
            multicastSocket.leaveGroup(group);

            if(!multicastSocket.isConnected()){
                multicastSocket.close();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    class Connection implements Runnable {
        private BufferedReader bufferedReader;
        private MulticastSocket multicastSocket;
        private InetAddress inetAddress;

        Connection(MulticastSocket multicastSocket, InetAddress inetAddress) {
            this.multicastSocket = multicastSocket;
            this.inetAddress = inetAddress;
        }

        @Override
        public void run() {
            try {
                sendInitMsg();

                // Hilo encargado de la lectura de datos del cliente por consola
                bufferedReader = new BufferedReader(new InputStreamReader(System.in));

                String msg;

                do {
                    msg = username + ": "+bufferedReader.readLine();
                    DatagramPacket paquete = new DatagramPacket(msg.getBytes(), msg.length(), inetAddress, port);

                    // Envía el paquete a todos los clientes conectados al grupo
                    multicastSocket.send(paquete);
                }while (!(msg.split(":")[1].trim().equalsIgnoreCase("exit")));
            } catch (IOException ignored) {}
        }

        private void sendInitMsg() throws IOException {
            String initialMsg = "SYS: "+username + " se ha conectado";
            DatagramPacket connectedMsg = new DatagramPacket(
                    initialMsg.getBytes(),
                    initialMsg.length(),
                    inetAddress,
                    port
            );

            multicastSocket.send(connectedMsg);
        }
    }

    public static void main(String[] args) {
        new Thread(new Chat()).start();
    }
}
