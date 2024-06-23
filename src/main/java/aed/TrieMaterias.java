package aed;

import java.util.ArrayList;
import java.util.List;

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

    public void agregaDocente(String materia, String docente){
        NodoTrieMaterias nodo = ultimoNodoMateria(materia);
        if (docente.equals("PROFE")){
            nodo.docentes[1] += 1;
            nodo.cupo += 250;
        } else if (docente.equals("JTP")){
            nodo.docentes[2] += 1;
            nodo.cupo += 100;
        } else if (docente.equals("AY1")){
            nodo.docentes[3] += 1;
            nodo.cupo += 20;
        } else {
            nodo.docentes[4] += 1;
            nodo.cupo += 30;
        }
    }

    public List<String> extraerMaterias(NodoTrieMaterias nodo, StringBuilder materiaActual, List<String> materias) {
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
        return materias;
    }

}

