package aed;

import java.util.ArrayList;

public class SistemaSIU {
    private InfoMateria[] infoMaterias;
    private TrieCarreras trieCarreras;
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
        this.trieCarreras = new TrieCarreras();
        this.dictEstudiantes = new DictEstudiantes();
        this.libretasUniversitarias = libretasUniversitarias;

        // Insertar materias en TrieMaterias
        for (InfoMateria infoMateria : infoMaterias) {
            for (ParCarreraMateria par : infoMateria.getParesCarreraMateria()) {
                trieCarreras.agregarCarrera(par.getCarrera());
                trieCarreras.agregaMateria(par.getCarrera(),par.getNombreMateria());
            }
        }
    }

    public void inscribir(String estudiante, String carrera, String materia){
        //carrera.materias.inscribirEstudiante
        trieCarreras.inscribirEstudiante(carrera,materia,estudiante);
        dictEstudiantes.inscribirMateria(estudiante,materia);
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        trieCarreras.agregaDocente(carrera,materia,cargo);
    }

    public int[] plantelDocente(String materia, String carrera){
        return trieCarreras.plantelDocente(carrera, materia);
    }

    public void cerrarMateria(String materia, String carrera){
        ArrayList<String> estudiantes = trieCarreras.ultimoNodoCarrera(carrera).materias.ultimoNodoMateria(materia).estudiantes;
        trieCarreras.eliminarMateria(materia,carrera);
        //Eliminamos la materia del dict estudiantes
        for (String estudiante : estudiantes){
            dictEstudiantes.eliminarMateria(estudiante,materia);
        }
    }

    public int inscriptos(String materia, String carrera){
        return trieCarreras.cantEstudiantes(carrera,materia);
    }

    public boolean excedeCupo(String materia, String carrera){
        return trieCarreras.cupo(carrera,materia) < trieCarreras.cantEstudiantes(carrera, materia);
    }

    public String[] carreras(){
        return trieCarreras.extraerCarreras();
    }

    public String[] materias(String carrera){
        return trieCarreras.listaMaterias(carrera);
    }

    public int materiasInscriptas(String estudiante){
        return dictEstudiantes.getEstudiante(estudiante).materias.size();
    }
}