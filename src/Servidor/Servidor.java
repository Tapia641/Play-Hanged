package Servidor;


import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends Thread {

    private Socket socketServidor;
    private DataOutputStream CanalSalida;
    private DataInputStream CanalEntrada;

    private String[] diccionario = {};

    private char[] palabra_secreta;

    public Servidor(Socket socket, int ContCliente) throws IOException, ClassNotFoundException {
        diccionario = CargarPalabras();

        this.socketServidor = socket;

        this.palabra_secreta = Random().toCharArray();

        try {
            CanalSalida = new DataOutputStream(socket.getOutputStream());
            CanalEntrada = new DataInputStream(socket.getInputStream());
            System.out.println(palabra_secreta);
            CanalSalida.writeUTF(String.valueOf(palabra_secreta));


        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {

    }

    private String Random() {
        int num = (int) (Math.random() * (diccionario.length));

        return diccionario[num];
    }

    public static void ExportarPalabras(String[] diccionario) throws IOException {
        /* LOS GUARDAMOS EN UN .OUT */
        ObjectOutputStream GuardarObjeto = new ObjectOutputStream(new FileOutputStream("Palabras.txt"));
        GuardarObjeto.writeObject(diccionario);
        GuardarObjeto.flush();
    }

    private String[] CargarPalabras() throws IOException, ClassNotFoundException {

        /* LEEMOS EL OBJETO DEL .OUT */
        FileInputStream miarchivo = new FileInputStream(new File("Palabras.txt").getAbsolutePath());
        ObjectInputStream LeerObjeto = new ObjectInputStream(miarchivo);
        diccionario = (String[]) LeerObjeto.readObject();
        LeerObjeto.close();

        return diccionario;
    }
}