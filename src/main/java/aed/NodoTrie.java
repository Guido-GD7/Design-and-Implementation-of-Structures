package aed;

import java.util.ArrayList;

//Nodos del trie Carreras
public class NodoTrie<T> {

    public boolean finPalabra;
    public NodoTrie<T>[] siguienteLetras;
    private Character letraActual;
    public T informacion;

    public NodoTrie() {

        this.finPalabra = false;
        this.letraActual = null; 
        this.siguienteLetras = (NodoTrie<T>[]) new NodoTrie[256];
        this.informacion = null;
    }

    public boolean esFinPalabra() {
        return finPalabra;
    }

    public void setFinPalabra(boolean finPalabra) {
        this.finPalabra = finPalabra;
    }

    public NodoTrie<T>[] getSiguienteLetras() {
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
