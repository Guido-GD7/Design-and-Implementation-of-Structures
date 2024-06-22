package aed;
// crea un objeto para poder manejar mejor las carreras agregadas al trieCarreras y asi poder enlazarle los trie de las Materias


public class CarreraTrie {
    String carrera;
    NodoTrie raiz;

    public CarreraTrie(String carrera) {
        this.carrera = carrera;
        this.raiz = new NodoTrie();
    }

    public String getCarrera() {
        return this.carrera;
    }

    public NodoTrie getRaiz() {
        return this.raiz;
    }
}