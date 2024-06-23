package aed;

import java.util.ArrayList;
import java.util.List;

import aed.SistemaSIU.CargoDocente;

public class TrieCarreras {

    public NodoTrie raiz;

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

    public void inscribirEstudiante(String carrera, String materia, String estudiante) {
        NodoTrie nodo = ultimoNodoCarrera(carrera);
        nodo.materias.inscribirEstudiante(materia, estudiante);
    }

    public void agregaDocente(String carrera, String materia, CargoDocente docente) {
        NodoTrie nodo = ultimoNodoCarrera(carrera);
        nodo.materias.agregaDocente(materia, docente);
    }

    public int[] plantelDocente(String carrera, String materia) {
        NodoTrie nodo = ultimoNodoCarrera(carrera);
        NodoTrieMaterias materia1 = nodo.materias.ultimoNodoMateria(materia);
        return materia1.docentes;
    }

    public int cantEstudiantes(String carrera, String materia) {
        NodoTrie nodo = ultimoNodoCarrera(carrera);
        NodoTrieMaterias materia1 = nodo.materias.ultimoNodoMateria(materia);
        return materia1.estudiantes.size();
    }

    public int cupo(String carrera, String materia) {
        NodoTrie nodo = ultimoNodoCarrera(carrera);
        NodoTrieMaterias materia1 = nodo.materias.ultimoNodoMateria(materia);
        return materia1.cupo;
    }

    public String[] extraerCarreras() {
        List<String> carreras = new ArrayList<>();
        extraerCarreras(raiz, new StringBuilder(), carreras);
        return carreras.toArray(new String[0]);
    }

    private void extraerCarreras(NodoTrie nodo, StringBuilder carreraActual, List<String> carreras) {
        if (nodo.isFinPalabra()) {
            carreras.add(carreraActual.toString());
        }

        for (int i = 0; i < nodo.getSiguienteLetras().length; i++) {
            if (nodo.getSiguienteLetras()[i] != null) {
                carreraActual.append(nodo.getSiguienteLetras()[i].getLetraActual());
                extraerCarreras(nodo.getSiguienteLetras()[i], carreraActual, carreras);
                carreraActual.deleteCharAt(carreraActual.length() - 1);
            }
        }
    }

    public String[] listaMaterias (String carrera) {
        NodoTrie nodo = ultimoNodoCarrera(carrera);
        return nodo.materias.extraerMaterias();
    }
}