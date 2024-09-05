package dominio.Solucion;

import exceptions.PrincipalException;

public interface IAlumnoDelete {
    boolean deleteAlumno(String dni, GestorInstituto gestor)throws PrincipalException;
}
