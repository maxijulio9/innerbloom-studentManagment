package dominio.Solucion.Alumno;

import dominio.Alumno;
import dominio.Solucion.GestorInstituto;
import exceptions.PrincipalException;

import java.util.ArrayList;
import java.util.Comparator;

public interface IAlumnoGetListSorted {
    ArrayList<Alumno> getListadoAlumnosOrdenado(Comparator<Alumno> comparatorAlumno, GestorInstituto gestor) throws PrincipalException;
}
