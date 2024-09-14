package dominio.SolucionSOLID.Curso;

import dominio.Curso;
import exceptions.PrincipalException;

public class CursoCreation implements ICursoCreation {

    private CursoValidationExisting cursoValidator;
    //add dependecies validaatorL
    public CursoCreation(CursoValidationExisting cursoValidator){
        this.cursoValidator = cursoValidator;
    }

    @Override
    public boolean addCurso(Curso curso) throws PrincipalException {
        return cursoValidator.existingCurso(curso);
    }
}
