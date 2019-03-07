package Cliente;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControladorCiente implements Initializable {

    @FXML
    TextField Palabra_oculta;
    @FXML
    Label ahorcado;
    @FXML
    Label errores;
    @FXML
    Label largoPalabra;
    @FXML
    JFXTextField Campo;
    @FXML
    Button Enviar;

    @FXML
    Button Conectar;
    @FXML
    TextField Direccion;
    @FXML
    TextField Puerto;
    @FXML
    Ahorcado juego;
    String palabra;

    public void initialize(URL location, ResourceBundle resources) {
    }

    public void nuevoJuego() {

        this.juego = new Ahorcado();
        juego = new Ahorcado(Palabra_oculta, palabra, ahorcado, errores, largoPalabra);
    }

    @FXML
    public void ClickEnviar(javafx.scene.input.MouseEvent event) {
        //OBTENEMOS EL PRIMER CARACTER DE LA ENTRADA
        char C = Campo.getCharacters().charAt(0);

        //HACEMOS UN UPPERCASE A ESE CARACTER SI LLEGARA A SER MINUSCULA
        if (C >= 'a' && C <='z'){
            C = (char) (C + ('A' - 'a'));
        }
        System.out.println(C);

        //VALIDAMOS
        this.juego.validarPalabra(C);

        //LIMPIAMOS
        Campo.setText("");
    }

    public void ClickConectarse(javafx.scene.input.MouseEvent event) {
        try {
            this.juego = new Ahorcado();
            this.palabra = juego.conectarse(Direccion.getCharacters().toString(), Integer.parseInt(Puerto.getCharacters().toString()));
            juego = new Ahorcado(Palabra_oculta, palabra, ahorcado, errores, largoPalabra);

            PedirDatosCliente();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void PedirDatosCliente() {

    }


}
