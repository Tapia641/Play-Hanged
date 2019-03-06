package Clases;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Jugador implements Externalizable {
    private String Nombre;
    private Integer Edad;
    private Integer Dificultad;
    private String Propuesta;
    private Integer Nuevo;

    public Jugador(String nombre, Integer edad, String propuesta, Integer dificultad) {
        Nombre = nombre;
        Edad = edad;
        Propuesta = propuesta;
        Dificultad = dificultad;
    }

    public Integer getNuevo() {
        return Nuevo;
    }

    public void setNuevo(Integer nuevo) {
        Nuevo = nuevo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Integer getEdad() {
        return Edad;
    }

    public void setEdad(Integer edad) {
        Edad = edad;
    }

    public Integer getDificultad() {
        return Dificultad;
    }

    public void setDificultad(Integer dificultad) {
        Dificultad = dificultad;
    }

    public String getPropuesta() {
        return Propuesta;
    }

    public void setPropuesta(String propuesta) {
        Propuesta = propuesta;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        // INDICAMOS CUALES ATRIBUTOS SE VAN A ENVIAR
        out.writeObject(Nombre);
        out.writeObject(Edad);
        out.writeObject(Propuesta);
        out.writeObject(Dificultad);
        out.writeObject(Nuevo);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // INDICAMOS CUALES ATRIBUTOS SE RECUPERAN
        Nombre = (String) in.readObject();
        Edad = (Integer) in.readObject();
        Propuesta = (String) in.readObject();
        Dificultad = (Integer) in.readObject();
        Nuevo = (Integer) in.readObject();

    }
}
