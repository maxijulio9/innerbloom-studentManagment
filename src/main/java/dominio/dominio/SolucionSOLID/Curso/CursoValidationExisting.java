package dominio.SolucionSOLID.Curso;

import dominio.Curso;

import java.util.ArrayList;

public class CursoValidationExisting {

    private CursoGetDefaultList listCursosDefault;

    //old noExisteCurso1 method
    public boolean existingCurso (Curso curso, ArrayList<Curso> cursosList) {
        //acá tmb seperate to another class to get the cursos
        ArrayList<Curso> listadoCurso= cursosList;

        for (Curso c : listadoCurso) {
            if (c.getCodigo() == curso.getCodigo() || c.getNombre().trim().equalsIgnoreCase(curso.getNombre().trim())) {
                return false; // El curso ya existe en la lista
            }
        }
        return true; // El curso no existe en la lista
    }
}
