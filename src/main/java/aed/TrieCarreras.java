package aed;

import java.util.ArrayList;
import java.util.List;

public class TrieCarreras {

    private NodoTrie raiz;

    public TrieCarreras() {
        this.raiz = new NodoTrie();
    }

    public void agregarCarrera(String carrera) {
        NodoTrie nodo = raiz;
        for (char c : carrera.toCharArray()) {
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

    public NodoTrie ultimoNodoCarrera(String carrera) {
        NodoTrie nodo = raiz;
        for (char c : carrera.toCharArray()) {
            int index = c;
            if (nodo.getSiguienteLetras()[index] != null) {
                nodo = nodo.getSiguienteLetras()[index];
            }
        }
        return nodo;
    }

    public void agregaMateria(String carrera, String materia) {
        NodoTrie nodo = ultimoNodoCarrera(carrera);
        nodo.materias.agregarMateria(materia);
    }

    public void insribirEstudiante(String carrera, String materia, String estudiante) {
        NodoTrie nodo = ultimoNodoCarrera(carrera);
        nodo.materias.inscribirEstudiante(materia, estudiante);
    }

    public List<String> extrarCarreras(NodoTrie nodo, StringBuilder carreraActual, List<String> carreras) {
        if (nodo.isFinPalabra()) {
            carreras.add(carreraActual.toString());
        }

        for (int i = 0; i < nodo.getSiguienteLetras().length; i++) {
            if (nodo.getSiguienteLetras()[i] != null) {
                carreraActual.append(nodo.getSiguienteLetras()[i].getLetraActual());
                extrarCarreras(nodo.getSiguienteLetras()[i], carreraActual, carreras);
                carreraActual.deleteCharAt(carreraActual.length() - 1);
            }
        }
        return carreras;
    }

    public List<String> listaMaterias (String carrera) {
        NodoTrie nodo = ultimoNodoCarrera(carrera);
        return nodo.materias.extraerMaterias(nodo.materias.raiz,new StringBuilder(),new ArrayList<>());
    }
}