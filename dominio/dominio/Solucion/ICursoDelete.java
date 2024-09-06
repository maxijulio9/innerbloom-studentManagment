package dominio.Solucion;

import exceptions.PrincipalException;

public interface ICursoDelete {
    boolean deleteCurso(String nameCurso) throws PrincipalException;
}
