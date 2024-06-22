package aed;

import java.util.ArrayList;
import java.util.List;

public class TrieCarreras {
    
    private NodoTrie raiz;

    public TrieCarreras() {
        this.raiz = new NodoTrie();
    }

    public void insertar(String palabra) {
        NodoTrie nodo = raiz;
        for (char c : palabra.toCharArray()) {
            int index = c;
            if (nodo.getSiguienteLetras()[index] == null) {
                NodoTrie nuevoNodo = new NodoTrie();
                nuevoNodo.setLetraActual(c);
                nodo.setSiguienteLetra(index, nuevoNodo);
            }
            nodo = nodo.getSiguienteLetras()[index];
        }
        nodo.setFinPalabra(true);
    }

    public List<String> extraerPalabras() {
        List<String> palabras = new ArrayList<>();
        extraerPalabras(raiz, new StringBuilder(), palabras);
        return palabras;
    }

    private void extraerPalabras(NodoTrie nodo, StringBuilder palabraActual, List<String> palabras) {
        if (nodo.isFinPalabra()) {
            palabras.add(palabraActual.toString());
        }

        for (int i = 0; i < nodo.getSiguienteLetras().length; i++) {
            if (nodo.getSiguienteLetras()[i] != null) {
                palabraActual.append(nodo.getSiguienteLetras()[i].getLetraActual());
                extraerPalabras(nodo.getSiguienteLetras()[i], palabraActual, palabras);
                palabraActual.deleteCharAt(palabraActual.length() - 1); 
            }
        }
    }

    public static void main(String[] args) {
        TrieCarreras trie = new TrieCarreras();
        trie.insertar("Quimica Organica 1");
        trie.insertar("Algoritmos2");
        trie.insertar("Matematica");
        trie.insertar("alamo");
        trie.insertar("articulo");

        List<String> palabras = trie.extraerPalabras();
        for (String palabra : palabras) {
            System.out.println(palabra);
        }
    }
}