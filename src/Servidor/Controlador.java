package Servidor;

import Clases.Palabras;
import javafx.fxml.Initializable;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controlador implements Initializable {

    private static Palabras ListaPalabras;

    public void initialize(URL location, ResourceBundle resources) {
        IniciarServidor();
    }

    public static void IniciarServidor() {

        ListaPalabras = new Palabras();

        /* SIEMPRE PONER EL SOCKET EN UN TRY-CATCH */
        try {
            /* PUERTO EN EL QUE ESCUCHA PETICIONES */
            ServerSocket socketServidor = new ServerSocket(1234);
            System.out.println("Esperando cliente...");

            while (true) {
                /* BLOQUEO HASTA QUE RECIBA ALGUNA PETICION DEL CLIENTE */
                Socket socketCliente = socketServidor.accept();
                System.out.println("Conexion establecida desde " + socketCliente.getInetAddress() + ":" + socketCliente.getPort());


                /* LEEMOS EL OBJETO DEL .OUT */
                FileInputStream miarchivo = new FileInputStream(new File("palabras.out").getAbsolutePath());
                ObjectInputStream LeerObjeto = new ObjectInputStream(miarchivo);
                ListaPalabras = (Palabras) LeerObjeto.readObject();
                LeerObjeto.close();

                /* ESTABLECEMOS EL CANAL DE ENTRADA */
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));

                /* RECIBIMOS INFORMACION DEL CLIENTE */
                String infoCliente = entrada.readLine();
                System.out.println("Recibimos un mensaje del cliente");
                System.out.println("Mensaje: " + infoCliente);

                /* OBTENEMOS EL CANAL DE SALIDA */
                PrintWriter salida = new PrintWriter(new OutputStreamWriter(socketCliente.getOutputStream()));

                /* ENVIAMOS INFORMACION AL CLIENTE */
                System.out.println("Enviando respuesta...");
                String resCliente = "Hola cliente, soy el servidor.";
                salida.println(resCliente);

                /* SE LIMPIA EL FLUJO EN ORDEN */
                salida.flush();
                salida.close();
                entrada.close();
                socketCliente.close();
                /* NOTA: EL SOCKET DEL SERVIDOR NUNCA SE CIERRA socketServidor.close(); */
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
