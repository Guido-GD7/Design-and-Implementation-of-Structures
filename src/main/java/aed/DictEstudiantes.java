package aed;

import java.util.ArrayList;
import java.util.List;

public class DictEstudiantes {
    private int letras = 256;
    private NodoDict[] raiz;

    public DictEstudiantes() {
        this.raiz = new NodoDict[letras];
    }

    private class NodoDict {
        public NodoDict[] siguientes;
        public boolean finLibreta;
        public List<String> materias;

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
    public void inscribirMateria(String libreta, String materia) {
        NodoDict[] actual = raiz;
        for (char c : libreta.toCharArray()) {
            int index = getIndex(c);
            if (actual[index] == null) {
                actual[index] = new NodoDict();
            }
            actual = actual[index].siguientes;
        }
        // Marcar fin de libreta y agregar materia
        if (actual[letras - 1] == null) {
            actual[letras - 1] = new NodoDict();
            actual[letras - 1].materias = new ArrayList<>();
        }
        actual[letras - 1].finLibreta = true;
        actual[letras - 1].materias.add(materia);
    }

    public List<String> getMaterias(String libreta) {
        NodoDict[] actual = this.raiz;
        for (char c : libreta.toCharArray()) {
            int index = getIndex(c);
            if (actual[index] == null) {
                return null; // No se encontró la libreta
            }
            actual = actual[index].siguientes;
        }
        if (actual[letras - 1] != null && actual[letras - 1].finLibreta) {
            return actual[letras - 1].materias;
        } else {
            return null;
        }
    }
}