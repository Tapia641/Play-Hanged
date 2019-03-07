package Cliente;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
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
    Label LabelBienvenido;


    Ahorcado juego;
    String palabra;
    String dificultad = "";

    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void ClickEnviar(javafx.scene.input.MouseEvent event) {
        if (!Campo.getText().equals("")) {
            //OBTENEMOS EL PRIMER CARACTER DE LA ENTRADA
            char C = Campo.getCharacters().charAt(0);

            //HACEMOS UN UPPERCASE A ESE CARACTER SI LLEGARA A SER MINUSCULA
            if (C >= 'a' && C <= 'z') {
                C = (char) (C + ('A' - 'a'));
            }
            System.out.println(C);

            //VALIDAMOS
            this.juego.validarPalabra(C);

            //LIMPIAMOS
            Campo.setText("");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("¡Advertencia!");
            alert.setHeaderText(null);
            alert.setContentText("Ingresa un carácter para continuar.");
            alert.showAndWait();
        }

    }

    public void ClickConectarse(javafx.scene.input.MouseEvent event) {
        PedirDatosCliente();

        try {
            this.juego = new Ahorcado();
            if (dificultad.equals("facil")) {
                this.palabra = juego.conectarse(Direccion.getCharacters().toString(), Integer.parseInt(Puerto.getCharacters().toString()));
                while (this.palabra.length() >= 5) {
                    this.palabra = juego.conectarse(Direccion.getCharacters().toString(), Integer.parseInt(Puerto.getCharacters().toString()));
                }

            } else {
                this.palabra = juego.conectarse(Direccion.getCharacters().toString(), Integer.parseInt(Puerto.getCharacters().toString()));
                while (this.palabra.length() < 5) {
                    this.palabra = juego.conectarse(Direccion.getCharacters().toString(), Integer.parseInt(Puerto.getCharacters().toString()));
                }
            }
            juego = new Ahorcado(Palabra_oculta, palabra, ahorcado, errores, largoPalabra);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void ClickNuevoJuego(javafx.scene.input.MouseEvent event) throws IOException {
        this.juego = new Ahorcado();
        if (dificultad.equals("facil")) {
            this.palabra = juego.conectarse(Direccion.getCharacters().toString(), Integer.parseInt(Puerto.getCharacters().toString()));
            while (this.palabra.length() >= 5) {
                this.palabra = juego.conectarse(Direccion.getCharacters().toString(), Integer.parseInt(Puerto.getCharacters().toString()));
            }

        } else {
            this.palabra = juego.conectarse(Direccion.getCharacters().toString(), Integer.parseInt(Puerto.getCharacters().toString()));
            while (this.palabra.length() < 5) {
                this.palabra = juego.conectarse(Direccion.getCharacters().toString(), Integer.parseInt(Puerto.getCharacters().toString()));
            }
        }
        juego = new Ahorcado(Palabra_oculta, palabra, ahorcado, errores, largoPalabra);
    }

    public void PedirDatosCliente() {

        String jugador = "";
        int edadjugador = 0;
        this.dificultad = "";

        jugador = PedirNombre();
        edadjugador = PedirEdad();
        this.dificultad = ElegirDificultad();

        LabelBienvenido.setText("Bienvenido al juego del ahorcado:\n" + jugador + "\nEdad: " + edadjugador + "\nDificultad: " + this.dificultad);
    }

    public String ElegirDificultad() {
        String dificultad;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Dificultad");
        alert.setHeaderText("¿Qué tan bueno eres para jugar?");
        alert.setContentText("Elige tu nivel.");

        ButtonType buttonTypeOne = new ButtonType("Facil");
        ButtonType buttonTypeTwo = new ButtonType("Dificl");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

        Optional<ButtonType> result3 = alert.showAndWait();
        if (result3.get() == buttonTypeOne) {
            dificultad = "facil";
            return dificultad;
        } else if (result3.get() == buttonTypeTwo) {
            dificultad = "dificil";
            return dificultad;
        } else {
            return ElegirDificultad();
        }
    }


    public Integer PedirEdad(){
        TextInputDialog edad = new TextInputDialog("0");
        edad.setTitle("Datos");
        edad.setHeaderText("Ingresa tu edad");
        edad.setContentText("Nombre:");
        Optional<String> result2 = edad.showAndWait();

        if (result2.get().isEmpty()){
            return PedirEdad();
        }else {
            try {
                return Integer.parseInt(result2.get());
            }catch (Exception e){
                return PedirEdad();
            }
        }
    }

    public String PedirNombre(){

        TextInputDialog nombre = new TextInputDialog("Jugador");

        nombre.setTitle("Datos");
        nombre.setHeaderText("Ingresa tu nombre de jugador");
        nombre.setContentText("Nombre:");
        Optional<String> result = nombre.showAndWait();

        if (result.get().isEmpty()){
            return PedirNombre();
        }else {
            try {
                return result.get();
            }catch (Exception e){
                return PedirNombre();
            }
        }
    }

}
