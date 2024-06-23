package aed;

import java.util.ArrayList;
import java.util.List;

public class backup {



    private CarreraTrie buscarCarrera(String carrera) {
        for (CarreraTrie ct : carreras) {
            if (ct.getCarrera().equals(carrera)) {
                return ct;
            }
        }
        return null;
    }

    public void insertarMateriaACarrera(ParCarreraMateria parCarreraMateria) {
        CarreraTrie carreraRelacionada = buscarCarrera(parCarreraMateria.getCarrera());
        if (carreraRelacionada == null) {
            carreraRelacionada = new CarreraTrie(parCarreraMateria.getCarrera());
            carreras.add(carreraRelacionada);
        }

        NodoTrie nodo = carreraRelacionada.getRaiz();
        for (char c : parCarreraMateria.getNombreMateria().toCharArray()) {
            int indice = c;
            if (nodo.getSiguienteLetras()[indice] == null) {
                NodoTrie nuevoNodo = new NodoTrie();
                nuevoNodo.setLetraActual(c);
                nodo.setSiguienteLetra(indice, nuevoNodo);
            }
            nodo = nodo.getSiguienteLetras()[indice];
        }
        nodo.setFinPalabra(true);
    }

    public List<String> obtenerMaterias(String carrera) {
        List<String> materias = new ArrayList<>();
        CarreraTrie carreraTrie = buscarCarrera(carrera);
        if (carreraTrie != null) {
            extraerMaterias(carreraTrie.getRaiz(), new StringBuilder(), materias);
        }
        return materias;
    }}


    }


public static void main(String[] args) {
    TrieMaterias trieMaterias = new TrieMaterias();

    trieMaterias.insertarMateriaACarrera(new ParCarreraMateria("Datos", "Orga1"));
    trieMaterias.insertarMateriaACarrera(new ParCarreraMateria("Datos", "Algebra 1"));
    trieMaterias.insertarMateriaACarrera(new ParCarreraMateria("Datos", "Matematica"));

    trieMaterias.insertarMateriaACarrera(new ParCarreraMateria("Compu", "Matematica"));
    trieMaterias.insertarMateriaACarrera(new ParCarreraMateria("Compu", "Algoritmos 2"));

    List<String> materiasDatos = trieMaterias.obtenerMaterias("Datos");
    System.out.println("Materias de Datos:");
    for (String materia : materiasDatos) {
        System.out.println(materia);
    }

    List<String> materiasCompu = trieMaterias.obtenerMaterias("Compu");
    System.out.println("Materias de Compu:");
    for (String materia : materiasCompu) {
        System.out.println(materia);
    }
}
}
