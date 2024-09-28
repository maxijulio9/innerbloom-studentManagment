package dominio.SolucionSOLID.Curso;

import dominio.Curso;
import dominio.SolucionSOLID.GenericInterface.IGetDefaultList;
import dominio.SolucionSOLID.GestorInstituto;
import persistencia.PersistenciaDBCurso;

import java.util.ArrayList;

public class CursoGetDefaultList implements IGetDefaultList<Curso> {

    @Override
    public ArrayList<Curso> getDefaultList() {

        return GestorInstituto.getInstance(null, null)
                                .getCursosDefaultList();
        //return PersistenciaDBCurso.getCursos();
    }

}
