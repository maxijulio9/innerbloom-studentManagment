package dominio.Solucion.Alumno;

import dominio.Alumno;
import dominio.Solucion.GestorInstituto;

import java.util.ArrayList;
import java.util.function.Predicate;

public interface IAlumnoGetFiltered {
    ArrayList<Alumno> getListadoFiltradoAlumno(Predicate<Alumno> filtroAlumno, GestorInstituto gestor);
}
