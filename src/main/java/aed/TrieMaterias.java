package aed;

import java.util.ArrayList;
import java.util.List;

import aed.SistemaSIU.CargoDocente;

public class TrieMaterias {
    public NodoTrieMaterias raiz;

    public TrieMaterias() {
        this.raiz = new NodoTrieMaterias();
    }

    public void agregarMateria(String materia) {
        NodoTrieMaterias nodo = raiz;
        for (char c : materia.toCharArray()) {
            int index = c;
            if (nodo.getSiguienteLetras()[index] == null) {
                NodoTrieMaterias nuevoNodo = new NodoTrieMaterias();
                nuevoNodo.setLetraActual(c);
                nodo.setSiguienteLetra(index, nuevoNodo);
            }
            nodo = nodo.getSiguienteLetras()[index];
        }
        nodo.setFinPalabra(true);
    }

    public NodoTrieMaterias ultimoNodoMateria(String materia) {
        NodoTrieMaterias nodo = raiz;
        for (char c : materia.toCharArray()) {
            int index = c;
            if (nodo.getSiguienteLetras()[index] != null) {
                nodo = nodo.getSiguienteLetras()[index];
            }
        }
        return nodo;
    }

    public void inscribirEstudiante (String materia, String estudiante){
        NodoTrieMaterias nodo = ultimoNodoMateria(materia);
        nodo.estudiantes.add(estudiante);
    }

    public void agregaDocente(String materia, CargoDocente docente){
        NodoTrieMaterias nodo = ultimoNodoMateria(materia);
        if (docente == CargoDocente.PROF){
            nodo.docentes[0] += 1;
            nodo.cupo += 250;
        }
        else if (docente == CargoDocente.JTP){
            nodo.docentes[1] += 1;
            nodo.cupo += 100;
        } else if (docente == CargoDocente.AY1){
            nodo.docentes[2] += 1;
            nodo.cupo += 20;
        } else {
            nodo.docentes[3] += 1;
            nodo.cupo += 30;
        }
    }

    public String[] extraerMaterias() {
        List<String> materia = new ArrayList<>();
        extraerMaterias(raiz, new StringBuilder(), materia);
        return materia.toArray(new String[0]);
    }

    private void extraerMaterias(NodoTrieMaterias nodo, StringBuilder materiaActual, List<String> materias) {
        if (nodo.isFinPalabra()) {
            materias.add(materiaActual.toString());
        }

        for (int i = 0; i < nodo.getSiguienteLetras().length; i++) {
            if (nodo.getSiguienteLetras()[i] != null) {
                materiaActual.append(nodo.getSiguienteLetras()[i].getLetraActual());
                extraerMaterias(nodo.getSiguienteLetras()[i], materiaActual, materias);
                materiaActual.deleteCharAt(materiaActual.length() - 1);
            }
        }
    }

    public void eliminarMateria(String materia) {
        eliminarMateria(raiz, materia, 0);
    }

    public void eliminarMateria(NodoTrieMaterias nodo, String materia, int indice) {
        if (indice == materia.length()) {
            if (nodo.finPalabra) {
                nodo.finPalabra = false;
            }
            return;
        }

        char c = materia.charAt(indice);
        int charIndice = c;
        NodoTrieMaterias node = nodo.siguienteLetras[charIndice];
        if (node == null) {
            return;
        }

        eliminarMateria(node, materia, indice + 1);

        // Check if the node can be deleted
        if (!node.finPalabra && noHaySiguiente(node)) {
            nodo.siguienteLetras[charIndice] = null;
        }
    }

    private boolean noHaySiguiente(NodoTrieMaterias nodo) {
        for (int i = 0; i < nodo.siguienteLetras.length; i++) {
            if (nodo.siguienteLetras[i] != null) {
                return false;
            }
        }
        return true;
    }
}
