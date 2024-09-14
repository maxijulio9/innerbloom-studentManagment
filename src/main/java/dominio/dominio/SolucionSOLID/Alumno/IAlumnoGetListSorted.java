package dominio.SolucionSOLID.Alumno;

import dominio.Alumno;
import dominio.SolucionSOLID.GestorInstituto;
import exceptions.PrincipalException;

import java.util.ArrayList;
import java.util.Comparator;

public interface IAlumnoGetListSorted {
    ArrayList<Alumno> getListadoAlumnosOrdenado(Comparator<Alumno> comparatorAlumno) throws PrincipalException;
}
