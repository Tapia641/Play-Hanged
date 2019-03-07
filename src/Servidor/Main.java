package Servidor;


import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main  {
    public static void main(String []args){
        IniciarServidor();
    }

    public static void IniciarServidor() {
        ServerSocket ss;
        System.out.print("Inicializando servidor... ");
        try {
            ss = new ServerSocket(1234);
            System.out.println("\t[OK]");
            int idSession = 1;

            try
            {
                String thisIp = InetAddress.getLocalHost().getHostAddress();
                int thisPort = ss.getLocalPort();
                System.out.println("IP:"+thisIp+ " Port: " + thisPort);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            while (true) {
                Socket socket;
                socket = ss.accept();
                System.out.println("Nueva conexión entrante: "+socket);
                ((Servidor) new Servidor(socket, idSession)).start();
                idSession++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
