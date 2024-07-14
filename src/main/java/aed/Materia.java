package aed;

import java.util.ArrayList;

//Nodos del trie Materias
public class Materia {

    public int[] docentes; //[PROFE, JTP, AY1, AY2]
    public int cupo;
    public ArrayList<String> estudiantes;
    public ArrayList<Carrera> carreras;
    public InfoMateria infoMateria;

    public Materia(InfoMateria infoMateria) {
        this.docentes = new int[4];
        this.cupo = 0;
        this.estudiantes = new ArrayList<String>();
        this.carreras = new ArrayList<>();
        this.infoMateria = infoMateria;
    }

    public void agregarDocente(SistemaSIU.CargoDocente docente){
        if (docente == SistemaSIU.CargoDocente.PROF){
            this.docentes[0] += 1;
            this.cupo += 250;
        }
        else if (docente == SistemaSIU.CargoDocente.JTP){
            this.docentes[1] += 1;
            this.cupo += 100;
        } else if (docente == SistemaSIU.CargoDocente.AY1){
            this.docentes[2] += 1;
            this.cupo += 20;
        } else {
            this.docentes[3] += 1;
            this.cupo += 30;
        }
    }

    public void inscribirEstudiante(String libreta){
        this.estudiantes.add(libreta);
    }
}
