package dominio.SolucionSOLID.Alumno;

import dominio.SolucionSOLID.GestorInstituto;
import exceptions.PrincipalException;

public interface IAlumnoDelete {
    boolean deleteAlumno(String dni, GestorInstituto gestor)throws PrincipalException;
}
