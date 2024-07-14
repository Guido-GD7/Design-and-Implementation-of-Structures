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

        for (InfoMateria materia : infoMaterias){
            Materia nodoMateria = new Materia(materia);
            for (ParCarreraMateria parCarreraMateria : materia.getParesCarreraMateria()){
                if (! trieCarreras.existe(parCarreraMateria.carrera)){
                    trieCarreras.agregar(parCarreraMateria.carrera, new Carrera(parCarreraMateria.carrera));
                }
                Carrera nodoCarrera = trieCarreras.getInformacion(parCarreraMateria.carrera);
                nodoCarrera.materias.agregar(parCarreraMateria.nombreMateria, nodoMateria);
                nodoMateria.carreras.add(nodoCarrera);
            }
        }
    }

    public void inscribir(String estudiante, String carrera, String materia){
        Materia nodoMateria = trieCarreras.getInformacion(carrera).materias.getInformacion(materia);
        nodoMateria.inscribirEstudiante(estudiante);
        dictEstudiantes.inscribirMateria(estudiante, nodoMateria);
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        Materia nodoMateria = trieCarreras.getInformacion(carrera).materias.getInformacion(materia);
        nodoMateria.agregarDocente(cargo);
    }

    public int[] plantelDocente(String materia, String carrera){
        Materia nodoMateria = trieCarreras.getInformacion(carrera).materias.getInformacion(materia);
        return nodoMateria.docentes;
    }

    public void cerrarMateria(String materia, String carrera){
        Materia nodoMateria = trieCarreras.getInformacion(carrera).materias.getInformacion(materia);
        ArrayList<Carrera> carreras = nodoMateria.carreras;
        InfoMateria infoMateria = nodoMateria.infoMateria;
        ArrayList<String> estudiantes = nodoMateria.estudiantes;

        for (ParCarreraMateria parCarreraMateria : infoMateria.getParesCarreraMateria()){
            for (Carrera nodoCarrera : carreras){
                if (nodoCarrera.nombre.equals(parCarreraMateria.carrera)){
                    nodoCarrera.materias.eliminar(parCarreraMateria.nombreMateria);
                }
            }
        }
        for (String libreta : nodoMateria.estudiantes){
            dictEstudiantes.eliminarMateria(libreta,nodoMateria);
        }
    }

    public int inscriptos(String materia, String carrera){
        Carrera nodoCarrera = trieCarreras.getInformacion(carrera);
        return nodoCarrera.materias.getInformacion(materia).estudiantes.size();
    }

    public boolean excedeCupo(String materia, String carrera){
        Carrera nodoCarrera = trieCarreras.getInformacion(carrera);
        int cupo = nodoCarrera.materias.getInformacion(materia).cupo;
        return inscriptos(materia, carrera) > cupo;
    }

    public String[] carreras(){
        return trieCarreras.extraer();
    }

    public String[] materias(String carrera){
        return trieCarreras.getInformacion(carrera).materias.extraer();
    }

    public int materiasInscriptas(String estudiante){
        return dictEstudiantes.getEstudiante(estudiante).materias.size();
    }
}