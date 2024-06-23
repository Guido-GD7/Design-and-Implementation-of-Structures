package aed;

import java.util.ArrayList;

public class NodoTrieMaterias {

    public int[] docentes; //[PROFE, JTP, AY1, AY2]
    public int cupo;
    public ArrayList<String> estudiantes;
    public ArrayList<String> carreras;

    public boolean finPalabra;
    public NodoTrieMaterias[] siguienteLetras;
    private Character letraActual;

    public NodoTrieMaterias() {
        this.docentes = new int[4];
        this.cupo = 0;
        this.estudiantes = new ArrayList<String>();
        this.carreras = new ArrayList<>();

        this.finPalabra = false;
        this.letraActual = null;
        this.siguienteLetras = new NodoTrieMaterias[256];
    }

    // Getters and setters if needed
    public boolean isFinPalabra() {
        return finPalabra;
    }

    public void setFinPalabra(boolean finPalabra) {
        this.finPalabra = finPalabra;
    }

    public NodoTrieMaterias[] getSiguienteLetras() {
        return siguienteLetras;
    }

    public void setSiguienteLetra(int index, NodoTrieMaterias nodo) {
        this.siguienteLetras[index] = nodo;
    }

    public Character getLetraActual() {
        return letraActual;
    }

    public void setLetraActual(Character letraActual) {
        this.letraActual = letraActual;
    }

}
