package dominio.SolucionSOLID.Curso;

import dominio.Alumno;
import dominio.Curso;
import dominio.SolucionSOLID.GenericInterface.ICreation;
import exceptions.PrincipalException;

import java.util.ArrayList;

public class CursoCreation implements ICreation <Curso> {

    private CursoValidationExisting cursoValidator;
    //add dependecies validaatorL
    public CursoCreation(){
        this.cursoValidator = new CursoValidationExisting();
    }

    @Override
    public boolean add(Curso curso, ArrayList<Curso> cursosList) {
        boolean existe =  cursoValidator.existingCurso(curso, cursosList);
        System.out.println("Existe o no? "+ existe);
        if (!existe){
            return cursosList.add(curso);
        }
        return false;
    }

}
