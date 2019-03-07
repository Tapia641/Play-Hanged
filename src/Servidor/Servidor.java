package Servidor;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends Thread {

    private Socket socketServidor;
    private DataOutputStream CanalSalida;
    private DataInputStream CanalEntrada;

    private String[] diccionario = {"ABEJA", "AEROPUERTO", "COMPUTADOR", "OSO",
            "JAVA", "NEVERA", "PROGRAMA", "INFORMATICA", "COMPUTACION", "COMPUTADOR", "CORAZON", "BANANO", "PLATANO",
            "AUTOMOVIL", "PERRO", "COLOMBIA", "LONDRES", "CEPILLO", "BRAZO", "CABEZA", "CUERPO", "DEPORTE", "SALUD",
            "ANONYMOUS", "CUADERNO", "PANTALLA", "UBUNTU", "SEMAFORO", "LINUX", "LOBO", "AMOR", "MOSCA", "ZANAHORIA",
            "PINGUINO", "HACKER", "SISTEMA", "ELEFANTE", "CASCADA", "JUEGOS", "LARGO", "BONITO", "IMPOSIBLE", "UNIDOS", "ZOMBIE",
            "MATEMATICAS", "CALCULO", "ALGEBRA", "DICCIONARIO", "BIBLIOTECA", "COCINA", "PELICULA", "COMERCIAL", "GRANDE", "PEQUEÑO",
            "GATO", "SAPO", "JIRAFA", "FERROCARRIL", "FACEBOOK", "PERSONA", "BICICLETA", "CONTROL", "PANTALON", "AEROSOL",
            "ERROR", "COPA", "COPA", "PROGRAMADOR", "LICENCIA", "NUEVE", "PROCESADOR", "GARAJE", "MODERNO", "TABLA", "DISCOTECA",
            "LENGUAJE", "PROGRAMACION", "HERRAMIENTAS", "INTERNET", "EJECUTAR", "PROYECTO", "PERIODICO", "COCODRILO", "TORTUGA", "CABALLO",
            "APLICACION", "PERA", "SOFTWARE", "ADMINISTRACION", "VENTANA", "MANTENIMIENTO", "INFORMACION", "PRESIDENTE", "PERSONA", "GENTE",
            "NARANJA", "PRUEBA", "MANZANA", "JARRA", "CELULAR", "TELEFONO", "CONTAMINACION", "COLOR", "ROMANO", "ADIVINAR", "MARCADOR",
            "INSTRUCCION", "CUADERNO", "CASA", "PALA", "ARBOL", "PUENTE", "PAPEL", "HOJA", "HELICOPTERO", "BARCO", "GOLF", "CARRERA",
            "TUBERIA", "PLOMERO", "FUTBOL", "BALONCESTO", "ESTADIO", "JEAN", "FUENTE", "LEOPARDO", "REGLA", "PRIMERO", "SEGUNDO",
            "BLUSA", "CAMISA", "AGUA", "FUEGO", "INDUSTRIA", "AIRE", "TIERRA", "NATURALEZA", "MIERCOLES", "FOTOGRAFIA", "LEON",
            "TIGRE"};

    private char[] palabra_secreta;

    public Servidor(Socket socket, int ContCliente) {

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
}