package Servidor;


import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        IniciarServidor();
    }

    public static void IniciarServidor() {
        ServerSocket ss;
        String thisIp = "";
        int thisPort = 0;
        System.out.print("Inicializando servidor... ");
        try {
            ss = new ServerSocket(1234);
            System.out.println("\t[OK]");
            int idSession = 1;

            try {
                thisIp = InetAddress.getLocalHost().getHostAddress();
                thisPort = ss.getLocalPort();
            } catch (Exception e) {
                e.printStackTrace();
            }

            MostrarInformacion(thisIp,thisPort);

            while (true) {
                Socket socket;
                socket = ss.accept();
                System.out.println("Nueva conexión entrante: " + socket.getInetAddress() + " con puerto " + socket.getPort());
                ((Servidor) new Servidor(socket, idSession)).start();
                idSession++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static void MostrarInformacion(String dir, int port){
        System.out.println("IP:" + dir + " Port: " + port);

    }
}
