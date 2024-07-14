package aed;

import java.util.ArrayList;

public class SistemaSIU {
    private InfoMateria[] infoMaterias;
    private Trie<Carrera> trieCarreras;
    private DictEstudiantes dictEstudiantes;
    private String[] libretasUniversitarias;

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){
        this.infoMaterias = infoMaterias;
        this.trieCarreras = new Trie<Carrera>();
        this.dictEstudiantes = new DictEstudiantes();
        this.libretasUniversitarias = libretasUniversitarias;

        for (InfoMateria materia : infoMaterias){                   //O(Mc * c)
            Materia nodoMateria = new Materia(materia);
            for (ParCarreraMateria parCarreraMateria : materia.getParesCarreraMateria()){   //O(Mc)
                if (! trieCarreras.existe(parCarreraMateria.carrera)){                      //O(c)
                    trieCarreras.agregar(parCarreraMateria.carrera, new Carrera(parCarreraMateria.carrera));
                }
                Carrera nodoCarrera = trieCarreras.getInformacion(parCarreraMateria.carrera);   //O(c)
                nodoCarrera.materias.agregar(parCarreraMateria.nombreMateria, nodoMateria);
                nodoMateria.carreras.add(nodoCarrera);
            }
        }
    }

    public void inscribir(String estudiante, String carrera, String materia){
        Materia nodoMateria = trieCarreras.getInformacion(carrera).materias.getInformacion(materia); //O(c + Mc)
        nodoMateria.inscribirEstudiante(estudiante);                                                 //O(1)
        dictEstudiantes.inscribirMateria(estudiante, nodoMateria);                                   //O(1)
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        Materia nodoMateria = trieCarreras.getInformacion(carrera).materias.getInformacion(materia); //O(c + Mc)
        nodoMateria.agregarDocente(cargo);                                                           //O(1)
    }

    public int[] plantelDocente(String materia, String carrera){
        Materia nodoMateria = trieCarreras.getInformacion(carrera).materias.getInformacion(materia); //O(c + Mc)
        return nodoMateria.docentes;                                                                 //O(1)
    }

    public void cerrarMateria(String materia, String carrera){
        Materia nodoMateria = trieCarreras.getInformacion(carrera).materias.getInformacion(materia); //O(c + Mc)
        ArrayList<Carrera> carreras = nodoMateria.carreras;
        InfoMateria infoMateria = nodoMateria.infoMateria;
        ArrayList<String> estudiantes = nodoMateria.estudiantes;

        for (ParCarreraMateria parCarreraMateria : infoMateria.getParesCarreraMateria()){            //O(N)
            for (Carrera nodoCarrera : carreras){
                if (nodoCarrera.nombre.equals(parCarreraMateria.carrera)){
                    nodoCarrera.materias.eliminar(parCarreraMateria.nombreMateria);
                }
            }
        }
        for (String libreta : nodoMateria.estudiantes){                                              //O(E)
            dictEstudiantes.eliminarMateria(libreta,nodoMateria);
        }
    }

    public int inscriptos(String materia, String carrera){
        Carrera nodoCarrera = trieCarreras.getInformacion(carrera);                     //O(c)
        return nodoCarrera.materias.getInformacion(materia).estudiantes.size();         //O(Mc)
    }

    public boolean excedeCupo(String materia, String carrera){
        Carrera nodoCarrera = trieCarreras.getInformacion(carrera);                     //O(c)
        int cupo = nodoCarrera.materias.getInformacion(materia).cupo;                   //O(Mc + 1) = O(Mc)
        return inscriptos(materia, carrera) > cupo;
    }

    public String[] carreras(){
        return trieCarreras.extraer();                                                  //O(sum(c))
    }

    public String[] materias(String carrera){
        return trieCarreras.getInformacion(carrera).materias.extraer();                 //O(c + sum(Mc))
    }

    public int materiasInscriptas(String estudiante){
        return dictEstudiantes.getEstudiante(estudiante).materias.size();               //O(E + Me)
    }
}