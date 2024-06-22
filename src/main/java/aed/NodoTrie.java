package aed;

import java.util.ArrayList;

public class NodoTrie {

    public boolean finPalabra;
    public NodoTrie[] siguienteLetras;
    private Character letraActual; 

    public NodoTrie() {
        this.finPalabra = false;
        this.letraActual = null; 
        this.siguienteLetras = new NodoTrie[256]; 
    }

    // Getters and setters if needed
    public boolean isFinPalabra() {
        return finPalabra;
    }

    public void setFinPalabra(boolean finPalabra) {
        this.finPalabra = finPalabra;
    }

    public NodoTrie[] getSiguienteLetras() {
        return siguienteLetras;
    }

    public void setSiguienteLetra(int index, NodoTrie nodo) {
        this.siguienteLetras[index] = nodo;
    }

    public Character getLetraActual() {
        return letraActual;
    }

    public void setLetraActual(Character letraActual) {
        this.letraActual = letraActual;
    }
}
