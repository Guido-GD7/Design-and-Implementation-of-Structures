package aed;

import java.util.ArrayList;

public class Carrera {
    public String nombre;
    public Trie<Materia> materias;

    public Carrera(String nombre) {
        this.nombre = nombre;
        this.materias = new Trie<>();
    }

    public void agregarMateria(String nombre, Materia materia) {
        this.materias.agregar(nombre, materia);
    }

    public void inscribirEstudiante(String materia, String libreta) {
        NodoTrie<Materia> nodo = this.materias.ultimoNodo(materia);
        nodo.informacion.inscribirEstudiante(libreta);
    }

    public void eliminarMateria(String materia) {
        this.materias.eliminar(materia);
    }

    public void agregarDocente(String materia, SistemaSIU.CargoDocente docente) {
        NodoTrie<Materia> nodo = this.materias.ultimoNodo(materia);
        nodo.informacion.agregarDocente(docente);
    }

    public int getCupo(String materia) {
        return this.materias.ultimoNodo(materia).informacion.cupo;
    }

}
