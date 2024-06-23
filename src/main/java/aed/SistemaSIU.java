package aed;

import java.util.ArrayList;

public class SistemaSIU {
    private InfoMateria[] infoMaterias;
    private TrieCarreras trieCarreras;
    private DictEstudiantes dictEstudiantes;

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
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int inscriptos(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
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
        return dictEstudiantes.getMaterias(estudiante).size();

    }
}

