import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class UDPServer {
    public static void main(String[] args) {
        DatagramSocket serverSocket = null;

        try {
           
            serverSocket = new DatagramSocket(1250);

            System.out.println("Serveur UDP démarré et en attente de datagrammes");

            while (true) {
                byte[] receiveData = new byte[1024];
                byte[] sendData;

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                String dateHeure = new Date().toString();
                sendData = dateHeure.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }
}
