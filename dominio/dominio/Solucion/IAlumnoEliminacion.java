package dominio.Solucion;

import exceptions.PrincipalException;

public interface IAlumnoEliminacion {
    boolean eliminarAlumno(String dni, GestorInstituto gestor)throws PrincipalException;
}
