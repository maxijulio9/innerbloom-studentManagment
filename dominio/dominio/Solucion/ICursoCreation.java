package dominio.Solucion;

import dominio.Curso;
import exceptions.PrincipalException;

public interface ICursoCreation {
    boolean addCurso(Curso curso) throws PrincipalException;
}
