package Clases;

import java.io.Serializable;
import java.util.HashSet;

public class Palabras implements Serializable {
    private HashSet<String> ConjuntoPalabras;

    public Palabras() {

    }

    public Palabras(HashSet<String> conjuntoPalabras) {
        ConjuntoPalabras = conjuntoPalabras;
    }

    public HashSet<String> getConjuntoPalabras() {
        return ConjuntoPalabras;
    }

    public void setConjuntoPalabras(HashSet<String> conjuntoPalabras) {
        ConjuntoPalabras = conjuntoPalabras;
    }

    public void pushPalabra(String P){
        ConjuntoPalabras.add(P);
    }

}
