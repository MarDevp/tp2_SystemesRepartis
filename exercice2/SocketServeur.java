import java.io.*;
import java.net.*;

public class SocketServeur {
    public static void main(String argv[]) {
        int port = 10000; 
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Serveur en attente sur le port " + port);
            Socket socket = serverSocket.accept();
            System.out.println("Connexion acceptée depuis " + socket.getInetAddress());

          
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            Voiture voiture = (Voiture) input.readObject();
            System.out.println("Voiture reçue : " + voiture.getType() + " " + voiture.getModel());

            
            voiture.setCarburant(50); // Par exemple, fixé à 50 litres

            
            output.writeObject(voiture);

          
            output.close();
            input.close();
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
