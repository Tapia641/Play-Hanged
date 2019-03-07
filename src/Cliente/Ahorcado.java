package Cliente;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Ahorcado {

    protected Socket SocketCliente;
    protected DataOutputStream CanalSalida;
    protected DataInputStream CanalEntrada;
    private int id;

    TextField campoPalabra;
    Label campoIntentos;
    Label campoErrores;
    Label largoPalabra;

    private boolean jugar = false;

    char[] palabra_secreta;
    private char[] palabra;

    int intentos = 0, limite = 4;
    boolean acerto = false;

    public Ahorcado() {
    }

    public Ahorcado(TextField campoPalabra, String palabra, Label errores, Label campoIntentos, Label largoPalabra) {

        this.campoIntentos = campoIntentos;
        this.campoPalabra = campoPalabra;
        this.campoErrores = errores;
        this.largoPalabra = largoPalabra;

        //guardamos la palabra secreta
        this.palabra_secreta = palabra.toCharArray();

        String linea = "";

        //colocamos lineas segun el tamaño de la palabra
        for (int i = 0; i <= palabra_secreta.length - 1; i++) {
            linea += "_";
        }

        //convertimos la linea en vector de char y guardamos en la variable palabra
        this.palabra = linea.toCharArray();

        //colocamos las lineas a adivinar en el JtextField
        this.campoPalabra.setText(linea);

        //colocamos el icono de los intentos restantes
//        this.campoIntentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intentos/0.jpg")));

        //colocamos la imagen del ahorcado
  //      this.campoErrores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ahorcado/0.jpg")));

        this.jugar = true;

        this.largoPalabra.setText("La palabra tiene " + this.palabra_secreta.length + " letras.");

    }

    public String conectarse(String direccion, int puerto) throws IOException {
        SocketCliente = new Socket(direccion, puerto);
        CanalSalida = new DataOutputStream(SocketCliente.getOutputStream());
        CanalEntrada = new DataInputStream(SocketCliente.getInputStream());
        System.out.println(id + " envía saludo");
        String palabra1 = "";
        palabra1 = CanalEntrada.readUTF();
        System.out.println(palabra1);
        CanalEntrada.close();
        CanalSalida.close();
        return palabra1;
    }

    public void validarPalabra(char letraIngresada) {
        if (jugar) {

            String letrasAcertadas = "";

            //validamos los intentos restantes
            if (this.intentos == limite) {

                //this.campoErrores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ahorcado/8.jpg")));

                String respuesta = "";

                for (int i = 0; i <= this.palabra_secreta.length - 1; i++) {
                    respuesta = respuesta + this.palabra_secreta[i];
                }

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lo lamento...");
                alert.setHeaderText(null);
                alert.setContentText("Perdiste, se te agotaron los intentos :'( ");
                alert.showAndWait();
                //JOptionPane.showMessageDialog(null, "Lo siento perdiste, la palabra era " + respuesta, "Perdiste!", JOptionPane.ERROR_MESSAGE);
            } else {
                //evaluamos si ha adivinado la palabra comparando cada letra
                for (int i = 0; i <= palabra_secreta.length - 1; i++) {

                    //validamos que la letra este en la palabra
                    if (this.palabra_secreta[i] == letraIngresada) {
                        this.palabra[i] = letraIngresada;
                        this.acerto = true;
                    }

                    letrasAcertadas += this.palabra[i]; //guardamos la palabra con las letras acertadas

                }
                //si no acerto, mostramos el intento fallido y el ahorcado

                if (this.acerto == false) {

                    intentos += 1; //Numero de errores

                    //campoIntentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intentos/" + this.intentos + ".jpg")));
                    //campoErrores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ahorcado/" + this.intentos + ".jpg")));

                    //Mostramos un mensaje avisando que erró la letra
                    if (intentos < limite) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("¡Fallaste!");
                        alert.setHeaderText(null);
                        alert.setContentText("Te quedan " + ((limite) - this.intentos) + " intentos.");

                        alert.showAndWait();
                        //JOptionPane.showMessageDialog(null, "Fallaste! \n\n\t Te quedan " + ((8) - this.intentos) + " intentos.");
                    }

                } else {
                    this.acerto = false;
                }

                this.campoPalabra.setText(letrasAcertadas);
                //comprobamos el estado del juego

                validarGano();
            }
        }
    }

    private void validarGano() {

        boolean gano = false;

        for (int i = 0; i <= this.palabra_secreta.length - 1; i++) {
            if (this.palabra[i] == this.palabra_secreta[i]) {
                gano = true;
            } else {
                gano = false;
                break;
            }
        }

        if (gano) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("¡Felicidades!");
            alert.setHeaderText(null);
            alert.setContentText("Ganaste :) ");
            alert.showAndWait();
            //JOptionPane.showMessageDialog(null, "!! Felicidades !! \n Adivinaste la palabra", "Ganaste", 0, new javax.swing.ImageIcon(getClass().getResource("/ahorcado/goals.png")));
        }
    }
}