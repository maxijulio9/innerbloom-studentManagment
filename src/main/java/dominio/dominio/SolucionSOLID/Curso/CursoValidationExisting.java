package dominio.SolucionSOLID.Curso;

import dominio.Curso;

import java.util.ArrayList;

public class CursoValidationExisting {

    private CursoGetDefaultList listCursosDefault;

    //old noExisteCurso1 method
    public boolean existingCurso (Curso curso, ArrayList<Curso> cursosList) {
        //acá tmb seperate to another class to get the cursos
        ArrayList<Curso> listadoCurso= cursosList;
        System.out.println(cursosList);
        if (cursosList.isEmpty()){
            return false;
        }
        for (Curso c : listadoCurso) {
            if (c.getCodigo() == curso.getCodigo() || c.getNombre().trim().equalsIgnoreCase(curso.getNombre().trim())) {
                return true; // El curso ya existe en la lista
            }
        }
        return false;
    }
}
