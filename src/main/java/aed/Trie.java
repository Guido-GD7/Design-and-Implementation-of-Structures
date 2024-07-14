package aed;

import java.util.ArrayList;
import java.util.List;

import aed.SistemaSIU.CargoDocente;

public class Trie<T> {

    public NodoTrie<T> raiz;
    public T informacion;

    public Trie() {
        this.raiz = new NodoTrie<>();
    }

    public void agregar(String palabra, T info) {
        NodoTrie<T> nodo = raiz;
        for (char c : palabra.toCharArray()) {
            int index = c;
            if (nodo.getSiguienteLetras()[index] == null) {
                NodoTrie<T> nuevoNodo = new NodoTrie<>();
                nuevoNodo.setLetraActual(c);
                nodo.setSiguienteLetra(index, nuevoNodo);
            }
            nodo = nodo.getSiguienteLetras()[index];
        }
        nodo.setFinPalabra(true);
        nodo.informacion = info;
    }

    public boolean existe(String palabra) {
        NodoTrie<T> actual = raiz;
        for (char c : palabra.toCharArray()) {
            NodoTrie<T> nodo = actual.siguienteLetras[c];
            if (nodo == null) {
                return false;
            }
            actual = nodo;
        }
        return actual.finPalabra;
    }

    public NodoTrie<T> ultimoNodo(String palabra) {
        NodoTrie<T> nodo = raiz;
        for (char c : palabra.toCharArray()) {
            int index = c;
            if (nodo.getSiguienteLetras()[index] != null) {
                nodo = nodo.getSiguienteLetras()[index];
            }
        }
        return nodo;
    }

    public String[] extraer() {
        List<String> carreras = new ArrayList<>();
        extraerPalabra(raiz, new StringBuilder(), carreras);
        return carreras.toArray(new String[0]);
    }

    private void extraerPalabra(NodoTrie nodo, StringBuilder carreraActual, List<String> carreras) {
        if (nodo.esFinPalabra()) {
            carreras.add(carreraActual.toString());
        }

        for (int i = 0; i < nodo.getSiguienteLetras().length; i++) {
            if (nodo.getSiguienteLetras()[i] != null) {
                carreraActual.append(nodo.getSiguienteLetras()[i].getLetraActual());
                extraerPalabra(nodo.getSiguienteLetras()[i], carreraActual, carreras);
                carreraActual.deleteCharAt(carreraActual.length() - 1);
            }
        }
    }

    public void eliminar(String word) {
        eliminarPalabra(raiz, word, 0);
    }

    private boolean eliminarPalabra(NodoTrie<T> actual, String palabra, int indice) {
        if (indice == palabra.length()) {
            if (!actual.finPalabra) {
                return false;
            }
            actual.finPalabra = false;
            return actual.siguienteLetras.length == 0;
        }
        char c = palabra.charAt(indice);
        NodoTrie<T> nodo = actual.siguienteLetras[c];
        if (nodo == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = eliminarPalabra(nodo, palabra, indice + 1);

        if (shouldDeleteCurrentNode) {
            actual.setSiguienteLetra(c, null);
            return !actual.finPalabra && sinHijos(actual);
        }
        return false;
    }

    private boolean sinHijos(NodoTrie<T> node) {
        for (NodoTrie<T> nodo : node.siguienteLetras) {
            if (nodo != null) {
                return false;
            }
        }
        return true;
    }

}