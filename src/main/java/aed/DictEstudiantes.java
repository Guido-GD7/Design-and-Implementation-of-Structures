package aed;

import java.util.ArrayList;
import java.util.List;

//Esta clase me permite armar las tuplas <libreta, infoEstudiante> a implementar
//en el dictEstudiantes

class TuplaLibreta<L, dict> {
    private L libreta;
    private dict infoEstudiante;

    public TuplaLibreta(L libreta, V infoEstudiante) {
        this.libreta = libreta;
        this.infoEstudiante = infoEstudiante;
    }

    public L getLibreta() {
        return libreta;
    }

    public V getValue() {
        return infoEstudiante;
    }

    public void setValue(V infoEstudiante) {
        this.infoEstudiante = infoEstudiante;
    }
}
