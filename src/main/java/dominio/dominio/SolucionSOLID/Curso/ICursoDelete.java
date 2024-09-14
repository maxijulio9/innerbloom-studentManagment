package dominio.SolucionSOLID.Curso;

import exceptions.PrincipalException;

public interface ICursoDelete {
    boolean deleteCurso(String nameCurso) throws PrincipalException;
}
