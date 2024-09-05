package dominio.Solucion;

import dominio.Alumno;
import exceptions.PrincipalException;

import java.util.ArrayList;
import java.util.Comparator;

public interface IAlumnoGetSorted {
    ArrayList<Alumno> getListadoAlumnosOrdenado(Comparator<Alumno> comparatorAlumno, GestorInstituto gestor) throws PrincipalException;
}
