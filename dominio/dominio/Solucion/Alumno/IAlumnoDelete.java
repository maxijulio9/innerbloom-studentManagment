package dominio.Solucion.Alumno;

import dominio.Solucion.GestorInstituto;
import exceptions.PrincipalException;

public interface IAlumnoDelete {
    boolean deleteAlumno(String dni, GestorInstituto gestor)throws PrincipalException;
}
