package aed;

import java.util.ArrayList;
import java.util.List;

public class DictEstudiantes {
    private int letras = 256;
    private NodoDict[] raiz;

    public DictEstudiantes() {
        this.raiz = new NodoDict[letras];
    }

    public class NodoDict {
        public NodoDict[] siguientes;
        public boolean finLibreta;
        public List<Materia> materias;

        public NodoDict() {
            this.siguientes = new NodoDict[letras];
            this.finLibreta = false;
            this.materias = new ArrayList<>();
        }
    }

    private int getIndex(char c) {
        return c;
    }

    //agrega materia a la lista de materias de la libreta
    public void inscribirMateria(String libreta, Materia materia) {
        NodoDict nodo = getEstudiante(libreta);
        nodo.materias.add(materia);
    }

    public void eliminarMateria(String libreta, Materia materia) {
        NodoDict nodo = getEstudiante(libreta);
        nodo.materias.remove(materia);
    }

    public NodoDict getEstudiante(String libreta) {
        NodoDict[] nodo = raiz;
        for (char c : libreta.toCharArray()) {
            int index = getIndex(c);
            if (nodo[index] == null) {
                nodo[index] = new NodoDict();
            }
            nodo = nodo[index].siguientes;
        }
        // Marcar fin de libreta y agregar materia
        if (nodo[letras - 1] == null) {
            nodo[letras - 1] = new NodoDict();
            nodo[letras - 1].materias = new ArrayList<>();
        }
        nodo[letras - 1].finLibreta = true;
        return nodo[letras - 1];
    }
}