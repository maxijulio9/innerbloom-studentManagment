package dominio.Solucion.Curso;

import dominio.Curso;
import persistencia.PersistenciaDBCurso;

import java.util.ArrayList;

public class CursoGetDefaultList implements ICursoGetDefaultList {

    @Override
    public ArrayList<Curso> getListCursos() {
        return PersistenciaDBCurso.getCursos();
    }
}
